package committee.nova.flotage.init;

import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.recipe.RackRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;

public class FloRecipeTypes {
    public static IRecipeType<RackRecipe> RACK_RECIPE_TYPE;

    public static void register() {
        RACK_RECIPE_TYPE = register("rack");
    }

    static <T extends IRecipe<?>> IRecipeType<T> register(final String id) {
        return Registry.register(Registry.RECIPE_TYPE, FlotageUtil.modRL(id), new IRecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }
}
