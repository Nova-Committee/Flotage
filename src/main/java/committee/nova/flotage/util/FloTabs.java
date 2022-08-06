package committee.nova.flotage.util;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.init.FloItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class FloTabs {
    public static final ItemGroup MAIN = new ItemGroup(Flotage.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return FloItems.OAK_LOG_RAFT.get().getDefaultInstance();
        }
    };

    public static Item common(){
        return new Item(new Item.Properties().tab(MAIN));
    }
}
