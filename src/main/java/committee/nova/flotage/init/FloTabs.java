package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class FloTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab(Flotage.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return FloItems.OAK_LOG_RAFT.get().getDefaultInstance();
        }
    };
}
