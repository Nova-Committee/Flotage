package committee.nova.flotage.tile.container;

import committee.nova.flotage.init.FloContainerTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class RackContainer extends AbstractContainerMenu {
    public RackContainer(int id) {
        super(FloContainerTypes.RACK_CONTAINER.get(), id);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
