package committee.nova.flotage.plugin.jei;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.init.FloItems;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.recipe.RackRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@JeiPlugin
public class FloJEI implements IModPlugin {
    public static mezz.jei.api.recipe.RecipeType<RackRecipe> RACK = mezz.jei.api.recipe.RecipeType.create(Flotage.MODID, "rack", RackRecipe.class);
    public static RackCategory RACK_CATEGORY;

    @Override
    public ResourceLocation getPluginUid() {
        return FlotageUtil.asRes("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        RACK_CATEGORY = new RackCategory(guiHelper);
        registration.addRecipeCategories(RACK_CATEGORY);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        List<RackRecipe> dryingRecipes = manager.getAllRecipesFor(FloRecipeTypes.RACK.get());
        registration.addRecipes(RACK, dryingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        addCatalyst(registration, FloItems.OAK_RACK);
        addCatalyst(registration, FloItems.SPRUCE_RACK);
        addCatalyst(registration, FloItems.BIRCH_RACK);
        addCatalyst(registration, FloItems.JUNGLE_RACK);
        addCatalyst(registration, FloItems.ACACIA_RACK);
        addCatalyst(registration, FloItems.DARK_OAK_RACK);
        addCatalyst(registration, FloItems.CRIMSON_RACK);
        addCatalyst(registration, FloItems.WARPED_RACK);
    }

    private void addCatalyst(IRecipeCatalystRegistration registration, RegistryObject<Item> block) {
        registration.addRecipeCatalyst(new ItemStack(block.get()), RACK);
    }

}
