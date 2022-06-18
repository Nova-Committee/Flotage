package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.recipe.DryingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Flotage.MODID);

    public static final RegistryObject<IRecipeSerializer<DryingRecipe>> DRYING_RECIPE = SERIALIZERS.register("drying",
            DryingRecipe.Serializer::new);

}
