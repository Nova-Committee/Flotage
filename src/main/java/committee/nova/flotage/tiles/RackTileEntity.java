package committee.nova.flotage.tiles;

import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.tiles.rack.RackMode;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class RackTileEntity extends AbstractRackTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    private static final int[] SLOTS = new int[]{0};
    private Inventory stack = new Inventory(1);
    private int processTime;
    private int totalTime;
    private RackMode mode = RackMode.EMPTY;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final IRecipeType<RackRecipe> recipeType = FloRecipeTypes.RACK_RECIPE_TYPE;

    public RackTileEntity() {
        super(FloTileEntities.RACK_TILE.get());
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("RecipeItem", this.stack.getItem(0).serializeNBT());
        tag.putInt("ProcessTime", this.processTime);
        tag.putInt("TotalTime", this.totalTime);
        tag.putString("Mode", String.valueOf(this.mode).toLowerCase());
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return tag;
    }

    @Override
    public void read(CompoundNBT tag) {
        assert this.level != null;
        this.stack.setItem(0, ItemStack.of((CompoundNBT) Objects.requireNonNull(tag.get("RecipeItem"))));
        this.processTime = tag.getInt("ProcessTime");
        this.totalTime = tag.getInt("TotalTime");
        this.mode = RackMode.switchingMode(this.level, this.worldPosition);
        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");

        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    public IInventory getInventory() {
        return stack;
    }

    @Override
    public int getContainerSize() {
        return stack.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public ItemStack getItem(int id) {
        return stack.getItem(id);
    }

    @Override
    public ItemStack removeItem(int id, int i) {
        return stack.removeItem(id, i);
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        return stack.removeItemNoUpdate(id);
    }

    @Override
    public void setItem(int id, ItemStack stack) {
        this.stack.setItem(id, stack);
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
        this.stack.clearContent();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        stack.fillStackedContents(helper);
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
        if (!this.level.isClientSide) {
            ItemStack itemstack = this.stack.getItem(0);
            if (!itemstack.isEmpty() ) {
                Optional<?> iRecipe = this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level);
                if (iRecipe.isPresent()) {
                    if (!itemstack.isEmpty()) {
                        this.totalTime = this.getTotalTime();
                    }
                    ++this.processTime;
                    this.setRecipeUsed((IRecipe<?>) iRecipe.get());
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
        ItemStack itemstack = this.stack.getItem(0);
        ItemStack itemstack1 = ((IRecipe<ISidedInventory>) iRecipe).assemble(this);
        if (itemstack1 != itemstack) {
            if (!this.level.isClientSide) {
                itemstack1.setCount(itemstack.getCount());
                this.stack.setItem(0, itemstack1);
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
                this.setChanged();
            }
        }
    }

    protected int getTotalTime() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(RackRecipe::getProcessTime).orElse(200);
    }
}
