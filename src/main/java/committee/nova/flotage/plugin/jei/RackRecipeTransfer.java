package committee.nova.flotage.plugin.jei;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.tile.container.RackContainer;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class RackRecipeTransfer implements IRecipeTransferInfo<RackContainer, RackRecipe> {

    @Override
    public Class<RackContainer> getContainerClass() {
        return RackContainer.class;
    }

    @Override
    public boolean canHandle(RackContainer container, RackRecipe recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(RackContainer container, RackRecipe recipe) {
        return container.slots;
    }

    @Override
    public List<Slot> getInventorySlots(RackContainer container, RackRecipe recipe) {
        return container.slots;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<RackRecipe> getRecipeClass() {
        return RackRecipe.class;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getRecipeCategoryUid() {
        return Flotage.asRes("rack");
    }
}
