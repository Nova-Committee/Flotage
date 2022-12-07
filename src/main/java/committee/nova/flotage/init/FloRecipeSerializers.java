package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.recipe.RackRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FloRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SECS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Flotage.MODID);

    public static final RegistryObject<RecipeSerializer<RackRecipe>> RACK_RECIPE = SECS.register("rack",
            RackRecipeSerializer::new);
}
