package committee.nova.flotage.tiles;

import committee.nova.flotage.tiles.container.RackContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public abstract class AbstractRackTileEntity extends BaseTileEntity implements INamedContainerProvider, IRecipeHolder {
    public AbstractRackTileEntity(TileEntityType<? extends ISidedInventory> type) {
        super(type);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.flotage.rack");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        assert this.level != null;
        return new RackContainer(sycID, inventory, this.worldPosition, this.level, new IntArray(0));
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create((ISidedInventory) this, Direction.UP, Direction.DOWN);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP || facing == Direction.DOWN)
                return handlers[0].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }
}
