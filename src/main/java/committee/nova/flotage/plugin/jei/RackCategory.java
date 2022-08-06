package committee.nova.flotage.plugin.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.init.FloBlocks;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.util.rack.RackMode;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class RackCategory implements IRecipeCategory<RackRecipe> {
    protected static final ResourceLocation BACKGROUND_LOC = FlotageUtil.modRL("textures/gui/jei/rack.png");
    private final int regularProcessTime;
    private final IDrawable background;
    private final IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public RackCategory(IGuiHelper guiHelper) {
        this.regularProcessTime = 200;
        this.background = guiHelper.createDrawable(BACKGROUND_LOC, 0, 0, 160, 65);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(FloBlocks.OAK_RACK.get()));
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25L).build(new CacheLoader<Integer, IDrawableAnimated>() {
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

    protected void drawDryTime(RackRecipe recipe, MatrixStack matrixStack, int y) {
        int time = recipe.getProcessTime();
        if (time > 0) {
            int dryTimeSeconds = time / 20;
            TranslationTextComponent timeString = new TranslationTextComponent("gui.jei.category.smelting.time.seconds", dryTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(matrixStack, timeString, (float)(this.background.getWidth() - stringWidth), (float)y, -8355712);
        }
    }

    protected void drawMode(RackRecipe recipe, MatrixStack matrixStack, int y) {
        RackMode mode = recipe.getMode();
        if (mode != RackMode.EMPTY) {
            TextComponent modeText = new TranslationTextComponent("gui.flotage.rack.mode."+mode.getType());
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(modeText);
            fontRenderer.draw(matrixStack, modeText, (float)(this.background.getWidth() - stringWidth), (float)y, -8355712);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return FlotageUtil.modRL("rack");
    }

    @Override
    public Class<? extends RackRecipe> getRecipeClass() {
        return RackRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("block.flotage.rack").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(RackRecipe dryingRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(dryingRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM, dryingRecipe.getResultItem());
    }

    @Override
    public void draw(RackRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(matrixStack, 67, 18);
        this.drawDryTime(recipe, matrixStack, 45);
        this.drawMode(recipe, matrixStack, 35);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RackRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 43, 18);
        guiItemStacks.init(1, false, 97, 18);
        guiItemStacks.set(ingredients);
    }
}
