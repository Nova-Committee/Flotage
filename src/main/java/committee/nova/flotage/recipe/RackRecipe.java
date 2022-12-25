package committee.nova.flotage.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import committee.nova.flotage.FlotageConfig;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloRecipes;
import committee.nova.flotage.util.rack.RackMode;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class RackRecipe implements IRecipe<IInventory> {
    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final int processTime;
    protected final RackMode mode;

    public RackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processTime, String mode) {
        this.type = FloRecipeTypes.RACK_RECIPE_TYPE;
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processTime = processTime;
        this.mode = RackMode.match(mode);
    }

    public RackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processTime, RackMode mode) {
        this.type = FloRecipeTypes.RACK_RECIPE_TYPE;
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processTime = processTime;
        this.mode = mode;
    }

    public int getProcessTime() {
        return processTime;
    }

    public RackMode getMode() {
        return this.mode;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        ItemStack itemstack = this.result.copy();
        CompoundNBT compoundnbt = inventory.getItem(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        return itemstack;
    }

    public boolean isRecipeConditionMet(World world, BlockPos pos) {
        if (!FlotageConfig.RACK_RECIPE_CONDITIONS.get()) {
            return true;
        }
        return (this.getMode() == RackMode.switchingMode(world, pos) || this.getMode() == RackMode.EMPTY);
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return FloRecipes.RACK_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return this.type;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RackRecipe> {
        @Override
        public RackRecipe fromJson(ResourceLocation id, JsonObject object) {
            JsonElement jsonelement = JSONUtils.isArrayNode(object, "ingredient") ? JSONUtils.getAsJsonArray(object, "ingredient") : JSONUtils.getAsJsonObject(object, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            if (!object.has("result")) throw new JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (object.get("result").isJsonObject()) itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(object, "result"));
            else {
                String s1 = JSONUtils.getAsString(object, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
            }
            int i = JSONUtils.getAsInt(object, "processtime", 200);
            String s = JSONUtils.getAsString(object, "mode", "empty");
            return new RackRecipe(id, ingredient, itemstack, i, s);
        }

        @Nullable
        @Override
        public RackRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            int i = buffer.readVarInt();
            String m = buffer.readUtf(32767);
            return new RackRecipe(id, ingredient, itemstack, i, m);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, RackRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.processTime);
            buffer.writeUtf(String.valueOf(recipe.mode));
        }
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
