package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.recipe.RackRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FloRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), Flotage.MODID);

    public static final RegistryObject<RecipeType<RackRecipe>> RACK = RECIPE_TYPES.register("rack", () -> registerRecipeType("rack"));

    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>()
        {
            public String toString() {
                return Flotage.MODID + ":" + identifier;
            }
        };
    }
}
