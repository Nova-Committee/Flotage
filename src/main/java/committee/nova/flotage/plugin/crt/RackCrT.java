package committee.nova.flotage.plugin.crt;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByOutputInput;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.misc.RackMode;
import committee.nova.flotage.recipe.RackRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.flotage.RackRecipe")
public class RackCrT implements IRecipeManager<RackRecipe> {
    public static final RackCrT INSTANCE = new RackCrT();

    @ZenCodeType.Method
    public void addRecipe(String id, IItemStack input, IItemStack output, int time, String mode) {
        ResourceLocation res = new ResourceLocation("crafttweaker", id);

        RackRecipe recipe = new RackRecipe(res, Ingredient.of(input.getInternal()), output.getInternal(), time, mode);

        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe, "(mode:" + mode + ")"));
    }

    @ZenCodeType.Method
    public void addRecipe(String id, IItemStack input, IItemStack output, int time) {
        ResourceLocation res = new ResourceLocation("crafttweaker", id);

        RackRecipe recipe = new RackRecipe(res, Ingredient.of(input.getInternal()), output.getInternal(), time, RackMode.EMPTY);

        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe, "(mode:" + RackMode.EMPTY.getType() + ")"));
    }

    @ZenCodeType.Method
    public void removeRecipe(IIngredient input, IItemStack output) {
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutputInput<>(this, output, input));
    }

    @Override
    public RecipeType<RackRecipe> getRecipeType() {
        return FloRecipeTypes.RACK.get();
    }
}
