package committee.nova.flotage.recipe;

import committee.nova.flotage.FlotageConfig;
import committee.nova.flotage.init.FloRecipeSerializers;
import committee.nova.flotage.init.FloRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class RackRecipe implements Recipe<Container> {
    protected final RecipeType<?> type;
    protected final ResourceLocation id;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final int processTime;
    protected final RackMode mode;

    public RackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processTime, String mode) {
        this.type = FloRecipeTypes.RACK.get();
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processTime = processTime;
        this.mode = RackMode.match(mode);
    }

    public RackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processTime, RackMode mode) {
        this.type = FloRecipeTypes.RACK.get();
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processTime = processTime;
        this.mode = mode;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess p_267165_) {
        ItemStack itemstack = this.result.copy();
        CompoundTag tag = container.getItem(0).getTag();
        if (tag != null) {
            itemstack.setTag(tag.copy());
        }

        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return this.result;
    }

    public boolean isRecipeConditionMet(Level world, BlockPos pos) {
        if (!FlotageConfig.RACK_RECIPE_CONDITIONS.get()) {
            return true;
        }
        return (this.getMode() == RackMode.switchingMode(world, pos) || this.getMode() == RackMode.EMPTY);
    }

    public RackMode getMode() {
        return mode;
    }

    public int getProcessTime() {
        return processTime;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FloRecipeSerializers.RACK_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
