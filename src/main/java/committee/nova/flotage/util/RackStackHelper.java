package committee.nova.flotage.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.items.ItemHandlerHelper;

public class RackStackHelper {
    public static final int defLimitAmount = 1;

    public static ActionResultType use(PlayerEntity player, Hand playerHand, ISidedInventory tile) {
        ItemStack handStack = player.getItemInHand(playerHand);
        ItemStack inStack = tile.getItem(0);
        int totalAmount = handStack.getCount() + inStack.getCount();
        int limitAmount = Math.min(defLimitAmount, handStack.getMaxStackSize());
        if (handStack.isEmpty() && inStack.isEmpty()) {
            return ActionResultType.PASS;
        }else if (!handStack.isEmpty() && inStack.isEmpty()) {
            int amount = Math.min(totalAmount, limitAmount);
            tile.setItem(0, new ItemStack(handStack.getItem(), amount));
            handStack.shrink(amount);
        }else if (handStack.isEmpty() && !inStack.isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(player, inStack);
            tile.setItem(0, ItemStack.EMPTY);
        }else if (!handStack.isEmpty() && !inStack.isEmpty()) {
            if (handStack.getItem() == inStack.getItem()) {
                int amount = limitAmount - inStack.getCount();
                if (amount <= 0) {
                    return ActionResultType.PASS;
                }
                tile.setItem(0, new ItemStack(handStack.getItem(), limitAmount));
                handStack.shrink(amount);
            }else {
                Item item = handStack.getItem();
                int amount = Math.min(handStack.getCount(), limitAmount);
                handStack.shrink(amount);
                ItemHandlerHelper.giveItemToPlayer(player, inStack);
                tile.setItem(0, new ItemStack(item, amount));
            }
        }
        return ActionResultType.SUCCESS;
    }
}
