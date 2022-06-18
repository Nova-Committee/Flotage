package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.recipe.DryingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class FloRecipeTypes {
    public static IRecipeType<DryingRecipe> DRYING_RECIPE_TYPE;

    public static void register() {
        DRYING_RECIPE_TYPE = register("drying");
    }

    static <T extends IRecipe<?>> IRecipeType<T> register(final String id) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Flotage.MODID, id), new IRecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }
}
