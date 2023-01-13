package committee.nova.flotage.tile;

import committee.nova.flotage.init.FloBlockEntities;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTags;
import committee.nova.flotage.util.StockUtil;
import committee.nova.flotage.recipe.RackRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RackBlockEntity extends BlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    protected final RecipeType<RackRecipe> recipeType = FloRecipeTypes.RACK.get();
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private static final int[] SLOTS = new int[]{0};
    private final ItemStackHandler STACKS = new ItemStackHandler();
    private Direction itemDirection = Direction.UP;
    private int totalTime;
    private int processTime;

    public RackBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RackBlockEntity(BlockPos pos, BlockState state) {
        this(FloBlockEntities.RACK_TILE.get(), pos, state);
    }

    protected void read(CompoundTag tag) {
        this.STACKS.setStackInSlot(0, ItemStack.of((CompoundTag) Objects.requireNonNull(tag.get("RecipeItem"))));
        this.totalTime = tag.getInt("TotalTime");
        this.processTime = tag.getInt("ProcessTime");
        this.itemDirection = Direction.valueOf(tag.getString("ItemDirection").toUpperCase());

        CompoundTag recipesUsed = tag.getCompound("RecipesUsed");
        for (String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.read(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.read(tag);
    }

    public CompoundTag write(CompoundTag tag) {
        ItemStack stack = this.STACKS.getStackInSlot(0);
        tag.put("RecipeItem", stack.serializeNBT());
        tag.putInt("TotalTime", this.totalTime);
        tag.putInt("ProcessTime", this.processTime);
        tag.putString("ItemDirection", String.valueOf(this.itemDirection));

        CompoundTag tag1 = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> tag1.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", tag1);
        return tag;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        return write(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        write(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, (blockEntity -> getUpdateTag()));
    }

    public int getProcessTime() {
        return processTime;
    }

    public int getTotalTime() {
        return totalTime;
    }


    public Direction getItemDirection() {
        return itemDirection;
    }

    public void setItemDirection(Direction itemDirection) {
        this.itemDirection = itemDirection;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int id, ItemStack stack, @Nullable Direction direction) {
        if (stack.is(FloTags.UNSTACKABLE)) {
            return this.STACKS.getStackInSlot(0).getCount() < 1;
        }
        if (stack.getCount() + this.STACKS.getStackInSlot(0).getCount() > StockUtil.defLimitAmount)
            return false;
        return (direction != Direction.DOWN && id == 0);
    }

    @Override
    public boolean canTakeItemThroughFace(int id, ItemStack stack, Direction direction) {
        return (this.processTime == 0 && this.totalTime != 0 && id == 0);
    }

    @Override
    public int getContainerSize() {
        return this.STACKS.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return this.STACKS.getStackInSlot(0).isEmpty();
    }

    protected List<ItemStack> shiftStack() {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.set(0, getItem(0));
        return stacks;
    }

    @Override
    public ItemStack getItem(int id) {
        return this.STACKS.getStackInSlot(id);
    }

    @Override
    public ItemStack removeItem(int id, int count) {
        ItemStack itemstack = ContainerHelper.removeItem(shiftStack(), id, count);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }
        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        ItemStack itemstack = getItem(id);
        if (itemstack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            setItem(id, itemstack);
            return itemstack;
        }
    }

    @Override
    public void setItem(int id, ItemStack stack) {
        this.STACKS.setStackInSlot(id, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        setItem(0, ItemStack.EMPTY);
        this.setChanged();
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void fillStackedContents(StackedContents contents) {
        contents.accountStack(getItem(0));
    }

    private void process(Level world, RackRecipe iRecipe) {
        ItemStack itemstack = getItem(0);
        ItemStack itemstack1 = iRecipe.assemble(this);
        if (itemstack1 != itemstack) {
            if (!world.isClientSide) {
                int i = Math.min(itemstack.getCount(), StockUtil.defLimitAmount);
                itemstack1.setCount(i);
                setItem(0, itemstack1);
                this.recipesUsed.clear();
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RackBlockEntity tile) {
        assert level != null;
        if (!level.isClientSide) {
            ItemStack itemstack = tile.getItem(0);
            if (!itemstack.isEmpty()) {
                Optional<RackRecipe> iRecipe = level.getRecipeManager().getRecipeFor(tile.recipeType, tile, level);
                tile.setChanged();
                if (iRecipe.isPresent()) {
                    RackRecipe recipe = iRecipe.get();
                    tile.totalTime = recipe.getProcessTime();
                    if (recipe.isRecipeConditionMet(level, pos)) {
                        tile.processTime++;
                        tile.setRecipeUsed(recipe);
                        if (tile.processTime >= tile.totalTime) {
                            tile.processTime = 0;
                            tile.process(tile.level, recipe);
                        }
                    }
                }else {
                    tile.processTime = 0;
                    tile.recipesUsed.clear();
                }
            }
        }
    }

    @Override
    public void setChanged() {
        assert this.level != null;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
        this.getBlockState().updateNeighbourShapes(this.level, this.getBlockPos(), 3);
        super.setChanged();
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlers[0].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }
}
