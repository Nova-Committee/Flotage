package committee.nova.flotage.tiles;

import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.recipe.DryingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Optional;

public class DryingRackTileEntity extends TileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    private static final int[] SLOTS = new int[]{0};
    private NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private int dryingProgress;
    private int dryingTotalTime;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final IRecipeType<DryingRecipe> recipeType = FloRecipeTypes.DRYING_RECIPE_TYPE;

    public DryingRackTileEntity() {
        super(FloTileEntities.DRYING_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        ItemStackHelper.loadAllItems(tag, this.stacks);
        this.dryingProgress = tag.getInt("DryTime");
        this.dryingTotalTime = tag.getInt("DryTotalTime");
        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");

        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        tag.putInt("DryTime", this.dryingProgress);
        tag.putInt("DryTotalTime", this.dryingTotalTime);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return tag;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        assert this.level != null;
        handleUpdateTag(this.level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.dryingProgress = tag.getInt("DryTime");
        this.dryingTotalTime = tag.getInt("DryTotalTime");
        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");

        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putInt("DryTime", this.dryingProgress);
        tag.putInt("DryTotalTime", this.dryingTotalTime);
        ItemStackHelper.saveAllItems(tag, this.stacks);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return super.save(tag);
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
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return (direction == Direction.UP && index == 0);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == 0;
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.stacks) {
            helper.accountStack(itemstack);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> iRecipe) {
        if (iRecipe != null) {
            ResourceLocation resourcelocation = iRecipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void tick() {
        assert this.level != null;
        if (!this.level.isClientSide) {
            ItemStack itemstack = this.stacks.get(0);
            if (!itemstack.isEmpty() ) {
                Optional<?> iRecipe = this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level);
                if (iRecipe.isPresent()) {
                    if (!itemstack.isEmpty()) {
                        this.dryingTotalTime = this.getTotalDryTime();
                    }
                    ++this.dryingProgress;
                    if (this.dryingProgress == this.dryingTotalTime) {
                        this.dryingProgress = 0;
                        this.dry((IRecipe<?>)iRecipe.get());
                    }
                }
            }else {
                this.dryingProgress = 0;
            }
        }
    }

    private void dry(@Nullable IRecipe<?> iRecipe) {
        assert this.level != null;
        assert iRecipe != null;
        ItemStack itemstack = this.stacks.get(0);
        ItemStack itemstack1 = ((IRecipe<ISidedInventory>) iRecipe).assemble(this);
        if (itemstack1 != itemstack) {
            if (!this.level.isClientSide) {
                this.setRecipeUsed(iRecipe);
                itemstack1.setCount(itemstack.getCount());
                this.stacks.set(0, itemstack1);
            }
        }
    }

    protected int getTotalDryTime() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(DryingRecipe::getCookingTime).orElse(200);
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP);

    @Override
    public <T> LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }
}
