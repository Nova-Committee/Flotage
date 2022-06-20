package committee.nova.flotage.plugin;

import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.init.FloBlocks;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.recipe.RackRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
        addCatalyst(registration, FloBlocks.OAK_RACK);
        addCatalyst(registration, FloBlocks.SPRUCE_RACK);
        addCatalyst(registration, FloBlocks.BIRCH_RACK);
        addCatalyst(registration, FloBlocks.JUNGLE_RACK);
        addCatalyst(registration, FloBlocks.ACACIA_RACK);
        addCatalyst(registration, FloBlocks.DARK_OAK_RACK);
        addCatalyst(registration, FloBlocks.CRIMSON_RACK);
        addCatalyst(registration, FloBlocks.WARPED_RACK);
    }

    void addCatalyst(IRecipeCatalystRegistration registration, RegistryObject<Block> block) {
        registration.addRecipeCatalyst(block.get(), FlotageUtil.modRL("rack"));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        List<RackRecipe> dryingRecipes = manager.getAllRecipesFor(FloRecipeTypes.RACK_RECIPE_TYPE);
        registration.addRecipes(dryingRecipes, FlotageUtil.modRL("rack"));
    }
}
