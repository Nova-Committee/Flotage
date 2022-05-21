package committee.nova.flotage.item;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.init.FloItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class Tabs {
    public static final ItemGroup MAIN = new ItemGroup(Flotage.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return FloItems.OAK_LOG_RAFT.get().getDefaultInstance();
        }
    };
}
