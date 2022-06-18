package committee.nova.flotage.tiles;

import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.recipe.DryingRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class DryingRackTileEntity extends TileEntity implements ISidedInventory {
    private NonNullList<ItemStack> stacks;
    private int progress;
    private DryingRecipe recipe;

    public DryingRackTileEntity() {
        super(FloTileEntities.DRYING_TILE.get());
        this.stacks = NonNullList.withSize(1, ItemStack.EMPTY);
        this.progress = 0;
    }

    public void tick(World world, BlockPos pos, BlockState state, DryingRackTileEntity tile) {
        ItemStack itemStack = tile.stacks.get(0);
        if (tile.recipe == null || !tile.recipe.matches(itemStack, world)) {
            tile.recipe = world.getRecipeManager()
                    .getAllRecipesFor(FloRecipeTypes.DRYING_RECIPE_TYPE).get(0);
        }else {
            if (!world.isClientSide()) {
                if (!itemStack.isEmpty()) {
                    tile.progress++;
                    if (tile.progress == tile.recipe.getCookingTime()) {
                        ItemStack result = tile.recipe.assemble(itemStack).copy();
                        result.setCount(itemStack.getCount());
                        tile.progress = 0;
                        setItem(0, result);
                    }
                }
            }
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        this.progress = tag.getInt("Progress");
        ItemStackHelper.loadAllItems(tag, this.stacks);
        super.load(state, tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.putInt("Progress", this.progress);
        return compoundNBT;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.progress = tag.getInt("Progress");
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putInt("Progress", this.progress);
        ItemStackHelper.saveAllItems(tag, this.stacks);
        return super.save(tag);
    }

    public DryingRecipe getActiveRecipe() {
        return this.recipe;
    }

    public int getSizeInventory() {
        return stacks.size();
    }

    public int getContainerSize() {
        return stacks.size();
    }

    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    public ItemStack getItem(int id) {
        return stacks.get(id);
    }

    public ItemStack removeItem(int id, int i) {
        return ItemStackHelper.takeItem(this.stacks, 0);
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        return ItemStackHelper.takeItem(this.stacks, 0);
    }

    public void setItem(int i, ItemStack stack) {
        stacks.set(0, stack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return IntStream.range(0, this.getSizeInventory()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
    }
}
