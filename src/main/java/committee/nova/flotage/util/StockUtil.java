package committee.nova.flotage.util;

import committee.nova.flotage.FlotageConfig;
import committee.nova.flotage.init.FloTags;
import committee.nova.flotage.tile.RackBlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Objects;

public class StockUtil {
    public static final int defLimitAmount = FlotageConfig.RACK_MAX_SIZE.get();

    public static InteractionResult use(Player player, InteractionHand playerHand, RackBlockEntity blockEntity) {
        ItemStack handStack = player.getItemInHand(playerHand);
        ItemStack inStack = blockEntity.getItem(0);
        int totalAmount = handStack.getCount() + inStack.getCount();
        boolean b = handStack.is(FloTags.UNSTACKABLE);
        int limitAmount = b ? 1 : Math.min(defLimitAmount, handStack.getMaxStackSize());
        if (handStack.isEmpty() && inStack.isEmpty()) {
            return InteractionResult.PASS;
        }else if (!handStack.isEmpty() && inStack.isEmpty()) {
            int amount = Math.min(totalAmount, limitAmount);
            blockEntity.setItem(0, countStack(handStack.copy(), amount));
            handStack.shrink(amount);
        }else if (handStack.isEmpty() && !inStack.isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(player, inStack);
            blockEntity.setItem(0, ItemStack.EMPTY);
        }else if (!handStack.isEmpty() && !inStack.isEmpty()) {
            if (handStack.getItem() == inStack.getItem()) {
                int amount = limitAmount - inStack.getCount();
                if (amount <= 0) {
                    return InteractionResult.PASS;
                }
                blockEntity.setItem(0, countStack(handStack.copy(), limitAmount));
                handStack.shrink(amount);
            }else {
                int amount = Math.min(handStack.getCount(), limitAmount);
                ItemHandlerHelper.giveItemToPlayer(player, inStack);
                blockEntity.setItem(0, countStack(handStack.copy(), amount));
                handStack.shrink(amount);
            }
        }
        blockEntity.getBlockState().updateNeighbourShapes(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos(), 3);
        return InteractionResult.SUCCESS;
    }

    public static ItemStack countStack(ItemStack stack, int count) {
        int setCount = Math.min(stack.getMaxStackSize(), count);
        stack.setCount(setCount);
        return stack;
    }
}
