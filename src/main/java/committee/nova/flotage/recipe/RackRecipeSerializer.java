package committee.nova.flotage.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class RackRecipeSerializer implements RecipeSerializer<RackRecipe> {

    @Override
    public RackRecipe fromJson(ResourceLocation id, JsonObject object) {
        JsonElement jsonelement = GsonHelper.isArrayNode(object, "ingredient") ? GsonHelper.getAsJsonArray(object, "ingredient") : GsonHelper.getAsJsonObject(object, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        if (!object.has("result")) throw new JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (object.get("result").isJsonObject()) itemstack = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(object, "result")).getDefaultInstance();
        else {
            String s1 = GsonHelper.getAsString(object, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getHolder(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        int i = GsonHelper.getAsInt(object, "processtime", 200);
        String s = GsonHelper.getAsString(object, "mode", "empty");
        return new RackRecipe(id, ingredient, itemstack, i, s);
    }

    @Nullable
    @Override
    public RackRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        int i = buffer.readVarInt();
        String m = buffer.readUtf(32767);
        return new RackRecipe(id, ingredient, itemstack, i, m);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, RackRecipe recipe) {
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeVarInt(recipe.processTime);
        buffer.writeUtf(String.valueOf(recipe.mode));
    }
}
