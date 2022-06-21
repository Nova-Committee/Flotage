package committee.nova.flotage.plugin;

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
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;

@JeiPlugin
public class FloJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return FlotageUtil.modRL("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new RackCategory(guiHelper));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new RackRecipeTransfer());
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

    void addCatalyst(IRecipeCatalystRegistration registration, RegistryObject<Item> block) {
        registration.addRecipeCatalyst(new ItemStack(block.get()), FlotageUtil.modRL("rack"));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        List<RackRecipe> dryingRecipes = manager.getAllRecipesFor(FloRecipeTypes.RACK_RECIPE_TYPE);
        registration.addRecipes(dryingRecipes, FlotageUtil.modRL("rack"));
    }
}
