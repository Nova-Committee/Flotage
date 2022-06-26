package committee.nova.flotage.tiles.container;

import committee.nova.flotage.init.FloContainerTypes;
import committee.nova.flotage.tiles.RackTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IntArray;

public class RackContainer extends Container {
    public RackContainer(int id, PlayerInventory playerInventory){
        this(id, playerInventory, new RackTileEntity());
    }

    public RackContainer(int id, PlayerInventory inventory, RackTileEntity tile) {
        super(FloContainerTypes.DRY_RACK_CONTAINER.get(), id);
        addDataSlots(new IntArray(0));
        assert tile != null;
        this.addSlot(new Slot(tile.getInventory(), 0, 80, 32));
        layoutPlayerInventorySlots(inventory, 8, 84);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }
}
