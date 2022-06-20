package committee.nova.flotage.plugin;

import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.tiles.container.RackContainer;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RackRecipeTransfer implements IRecipeTransferInfo<RackContainer> {

    @Override
    public Class<RackContainer> getContainerClass() {
        return RackContainer.class;
    }

    @Override
    public ResourceLocation getRecipeCategoryUid() {
        return FlotageUtil.modRL("rack");
    }

    @Override
    public boolean canHandle(RackContainer dryRackContainer) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(RackContainer dryRackContainer) {
        return dryRackContainer.slots;
    }

    @Override
    public List<Slot> getInventorySlots(RackContainer dryRackContainer) {
        return dryRackContainer.slots;
    }
}
