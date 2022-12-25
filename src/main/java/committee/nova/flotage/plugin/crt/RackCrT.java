package committee.nova.flotage.plugin.crt;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutputInput;
import committee.nova.flotage.init.FloRecipeTypes;
import committee.nova.flotage.recipe.RackRecipe;
import committee.nova.flotage.util.rack.RackMode;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.flotage.RackRecipe")
public class RackCrT implements IRecipeManager {
    public static final RackCrT INSTANCE = new RackCrT();

    @ZenCodeType.Method
    public void addRecipe(String id, IItemStack input, IItemStack output, int time, String mode) {
        ResourceLocation res = new ResourceLocation("crafttweaker", id);

        RackRecipe recipe = new RackRecipe(res, Ingredient.of(input.getInternal()), output.getInternal(), time, mode);

        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, "(mode:"+mode+")"));
    }

    @ZenCodeType.Method
    public void addRecipe(String id, IItemStack input, IItemStack output, int time) {
        ResourceLocation res = new ResourceLocation("crafttweaker", id);

        RackRecipe recipe = new RackRecipe(res, Ingredient.of(input.getInternal()), output.getInternal(), time, RackMode.EMPTY);

        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, "(mode:"+RackMode.EMPTY.getType()+")"));
    }

    @ZenCodeType.Method
    public void removeRecipe(IIngredient input, IItemStack output) {
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutputInput(this, output, input));
    }

    @Override
    public IRecipeType<RackRecipe> getRecipeType() {
        return FloRecipeTypes.RACK_RECIPE_TYPE;
    }
}
