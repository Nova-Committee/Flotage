package committee.nova.flotage.item;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.init.FloItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class Tabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab(Flotage.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return FloItems.OAK_LOG_RAFT.get().getDefaultInstance();
        }
    };
}
