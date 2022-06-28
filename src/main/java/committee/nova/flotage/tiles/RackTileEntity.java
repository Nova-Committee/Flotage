package committee.nova.flotage.tiles;

import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.util.RackStackHelper;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class RackTileEntity extends AbstractRackTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    private static final int[] SLOTS = new int[]{0};
    private Inventory stack = new Inventory(1);
    private int totalTime;
    private int processTime;
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
        tag.putInt("TotalTime", this.totalTime);
        tag.putInt("ProcessTime", this.processTime);

        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundnbt.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundnbt);
        return tag;
    }

    @Override
    public void read(CompoundNBT tag) {
        assert this.level != null;
        this.stack.setItem(0, ItemStack.of((CompoundNBT) Objects.requireNonNull(tag.get("RecipeItem"))));
        this.totalTime = tag.getInt("TotalTime");
        this.processTime = tag.getInt("ProcessTime");

        CompoundNBT recipesUsed = tag.getCompound("RecipesUsed");
        for(String s : recipesUsed.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), recipesUsed.getInt(s));
        }
    }

    public int getProcessTime() {
        return processTime;
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
        if (stack.getCount() + this.stack.getItem(0).getCount() > RackStackHelper.defLimitAmount) {
            return false;
        }
        return (direction != Direction.DOWN && index == 0);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return false;
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
            if (!itemstack.isEmpty()) {
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
                if (hasRecipe(this.level)) {
                    RackRecipe recipe = getNowRecipe(this.level);
                    this.totalTime = recipe.getProcessTime();
                    if (recipe.isRecipeConditionMet(this.level, this.worldPosition)) {
                        ++this.processTime;
                        this.setRecipeUsed(recipe);
                        if (this.processTime == this.totalTime) {
                            this.processTime = 0;
                            this.process(this.level, recipe);
                        }
                    }
                }
            }else {
                this.processTime = 0;
                this.recipesUsed.clear();
            }
        }
    }

    public boolean hasRecipe(World world) {
        Optional<RackRecipe> iRecipe = world.getRecipeManager().getRecipeFor(this.recipeType, this, world);
        return iRecipe.isPresent();
    }

    public RackRecipe getNowRecipe(World world) {
        Optional<RackRecipe> iRecipe = world.getRecipeManager().getRecipeFor(this.recipeType, this, world);
        return iRecipe.orElse(null);
    }

    private void process(World world, RackRecipe iRecipe) {
        ItemStack itemstack = this.stack.getItem(0);
        ItemStack itemstack1 = iRecipe.assemble(this);
        if (itemstack1 != itemstack) {
            if (!world.isClientSide) {
                itemstack1.setCount(itemstack.getCount());
                this.stack.setItem(0, itemstack1);
                this.setChanged();
                this.recipesUsed.clear();
            }
        }
    }
}
