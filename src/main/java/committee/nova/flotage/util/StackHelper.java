package committee.nova.flotage.util;

import committee.nova.flotage.FlotageConfig;
import committee.nova.flotage.FlotageUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Objects;

public class StackHelper {
    public static final int defLimitAmount = FlotageConfig.RACK_MAX_SIZE.get();

    public static ActionResultType use(PlayerEntity player, Hand playerHand, ISidedInventory tile) {
        ItemStack handStack = player.getItemInHand(playerHand);
        ItemStack inStack = tile.getItem(0);
        int totalAmount = handStack.getCount() + inStack.getCount();
        boolean b = Objects.requireNonNull(ItemTags.getAllTags().getTag(FlotageUtil.modRL("unstackable"))).contains(handStack.getItem());
        int limitAmount = b ? 1 : Math.min(defLimitAmount, handStack.getMaxStackSize());
        if (handStack.isEmpty() && inStack.isEmpty()) {
            return ActionResultType.PASS;
        }else if (!handStack.isEmpty() && inStack.isEmpty()) {
            int amount = Math.min(totalAmount, limitAmount);
            tile.setItem(0, countStack(handStack.copy(), amount));
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
                tile.setItem(0, countStack(handStack.copy(), limitAmount));
                handStack.shrink(amount);
            }else {
                int amount = Math.min(handStack.getCount(), limitAmount);
                ItemHandlerHelper.giveItemToPlayer(player, inStack);
                tile.setItem(0, countStack(handStack.copy(), amount));
                handStack.shrink(amount);
            }
        }
        return ActionResultType.SUCCESS;
    }

    public static ItemStack countStack(ItemStack stack, int count) {
        int setCount = Math.min(stack.getMaxStackSize(), count);
        stack.setCount(setCount);
        return stack;
    }
}
