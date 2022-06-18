package committee.nova.flotage.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import committee.nova.flotage.init.FloItems;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.init.FloRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DryingRecipe extends AbstractCookingRecipe {

    public DryingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int time) {
        super(FloRecipeTypes.DRYING_RECIPE_TYPE, id, group, ingredient, result, experience, time);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(FloItems.OAK_DRYING_RACK.get());
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

    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    public boolean matches(ItemStack itemStack, World world) {
        return this.ingredient.test(itemStack);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return FloRecipes.DRYING_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return FloRecipeTypes.DRYING_RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DryingRecipe> {
        @Override
        public DryingRecipe fromJson(ResourceLocation id, JsonObject object) {
            String s = JSONUtils.getAsString(object, "group", "");
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
            float f = JSONUtils.getAsFloat(object, "experience", 0.0F);
            int i = JSONUtils.getAsInt(object, "dryingtime", 200);
            return new DryingRecipe(id, s, ingredient, itemstack, f, i);
        }

        @Nullable
        @Override
        public DryingRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
            String s = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            float f = buffer.readFloat();
            int i = buffer.readVarInt();
            return new DryingRecipe(id, s, ingredient, itemstack, f, i);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, DryingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookingTime);
        }
    }
}
