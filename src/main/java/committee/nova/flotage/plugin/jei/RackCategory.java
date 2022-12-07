package committee.nova.flotage.plugin.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;
import committee.nova.flotage.Flotage;
import committee.nova.flotage.init.FloBlocks;
import committee.nova.flotage.misc.RackMode;
import committee.nova.flotage.recipe.RackRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static mezz.jei.api.recipe.RecipeIngredientRole.INPUT;
import static mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT;

public class RackCategory implements IRecipeCategory<RackRecipe> {
    protected static final ResourceLocation BACKGROUND_LOC = Flotage.asRes("textures/gui/jei/rack.png");
    private final int regularProcessTime;
    private final IDrawable background;
    private final IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public RackCategory(IGuiHelper guiHelper) {
        this.regularProcessTime = 200;
        this.background = guiHelper.createDrawable(BACKGROUND_LOC, 0, 0, 160, 65);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(FloBlocks.OAK_RACK.get()));
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25L).build(new CacheLoader<>() {
            public IDrawableAnimated load(Integer processTime) {
                return guiHelper.drawableBuilder(BACKGROUND_LOC, 160, 0, 24, 17).buildAnimated(processTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    protected IDrawableAnimated getArrow(RackRecipe recipe) {
        int time = recipe.getProcessTime();
        if (time <= 0) {
            time = this.regularProcessTime;
        }

        return this.cachedArrows.getUnchecked(time);
    }

    protected void drawDryTime(RackRecipe recipe, PoseStack poseStack, int y) {
        int time = recipe.getProcessTime();
        if (time > 0) {
            int dryTimeSeconds = time / 20;
            TranslatableComponent timeString = new TranslatableComponent("gui.jei.category.smelting.time.seconds", dryTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(poseStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    protected void drawMode(RackRecipe recipe, PoseStack poseStack, int y) {
        RackMode mode = recipe.getMode();
        if (mode != RackMode.EMPTY) {
            TranslatableComponent modeText = new TranslatableComponent("gui.flotage.rack.mode."+ mode.getType());
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(modeText);
            fontRenderer.draw(poseStack, modeText, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RackRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(INPUT, 44, 19)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(OUTPUT, 98, 19)
                .addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(RackRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(stack, 67, 18);
        this.drawDryTime(recipe, stack, 47);
        this.drawMode(recipe, stack, 37);
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("block.flotage.rack");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return Flotage.asRes("rack");
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends RackRecipe> getRecipeClass() {
        return RackRecipe.class;
    }

    @Override
    public RecipeType<RackRecipe> getRecipeType() {
        return FloJEI.RACK;
    }
}
