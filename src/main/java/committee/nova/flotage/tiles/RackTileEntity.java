package committee.nova.flotage.tiles;

import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.tiles.rack.RackMode;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Optional;

public class RackTileEntity extends AbstractRackTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    private static final int[] SLOTS = new int[]{0};
    private Inventory inventory = new Inventory(1);
    private int processTime;
    private int totalTime;
    private RackMode mode = RackMode.EMPTY;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final IRecipeType<RackRecipe> recipeType = FloRecipeTypes.RACK_RECIPE_TYPE;

    public RackTileEntity() {
        super(FloTileEntities.RACK_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.inventory.setItem(0, ItemStack.of(tag.getCompound("RecipeItem")));
        this.processTime = tag.getInt("ProcessTime");
        this.totalTime = tag.getInt("TotalTime");
        this.mode = RackMode.valueOf(tag.getString("Mode"));
        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");

        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 1, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        ItemStack stack = this.inventory.getItem(0).copy();
        tag.put("RecipeItem", stack.serializeNBT());
        tag.putInt("ProcessTime", this.processTime);
        tag.putInt("TotalTime", this.totalTime);
        tag.putString("Mode", String.valueOf(this.mode).toLowerCase());
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return tag;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        assert this.level != null;
        handleUpdateTag(this.level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.inventory.setItem(0, ItemStack.of(tag.getCompound("RecipeItem")));
        this.processTime = tag.getInt("ProcessTime");
        this.totalTime = tag.getInt("TotalTime");
        this.mode = RackMode.valueOf(tag.getString("Mode"));
        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");

        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        ItemStack stack = this.inventory.getItem(0).copy();
        tag.put("RecipeItem", stack.serializeNBT());
        tag.putInt("ProcessTime", this.processTime);
        tag.putInt("TotalTime", this.totalTime);
        tag.putString("Mode", String.valueOf(this.mode).toLowerCase());
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return tag;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getContainerSize() {
        return inventory.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int id) {
        return inventory.getItem(id);
    }

    @Override
    public ItemStack removeItem(int id, int i) {
        return inventory.removeItem(id, i);
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        return inventory.removeItemNoUpdate(id);
    }

    @Override
    public void setItem(int id, ItemStack stack) {
        inventory.setItem(id, stack);
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
        return (index == 0 && processTime == 0 && direction == Direction.DOWN);
    }

    @Override
    public void clearContent() {
        this.inventory.clearContent();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        inventory.fillStackedContents(helper);
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> iRecipe) {
        if (iRecipe != null) {
            ResourceLocation resourcelocation = iRecipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Override
    public void tick() {
        assert this.level != null;
        this.mode = RackMode.switchingMode(this.level, this.worldPosition);
        if (!this.level.isClientSide) {
            ItemStack itemstack = this.inventory.getItem(0);
            if (!itemstack.isEmpty() ) {
                Optional<?> iRecipe = this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level);
                if (iRecipe.isPresent()) {
                    if (!itemstack.isEmpty()) {
                        this.totalTime = this.getTotalTime();
                    }
                    ++this.processTime;
                    if (this.processTime == this.totalTime) {
                        this.processTime = 0;
                        this.process((IRecipe<?>)iRecipe.get());
                    }
                }
            }else {
                this.processTime = 0;
            }
        }
    }

    private void process(@Nullable IRecipe<?> iRecipe) {
        assert this.level != null;
        assert iRecipe != null;
        ItemStack itemstack = this.inventory.getItem(0);
        ItemStack itemstack1 = ((IRecipe<ISidedInventory>) iRecipe).assemble(this);
        if (itemstack1 != itemstack) {
            if (!this.level.isClientSide) {
                this.setRecipeUsed(iRecipe);
                itemstack1.setCount(itemstack.getCount());
                this.inventory.setItem(0, itemstack1);
            }
        }
    }

    protected int getTotalTime() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(RackRecipe::getProcessTime).orElse(200);
    }
}
