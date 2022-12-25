package committee.nova.flotage.plugin.kjs;

import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.ListJS;

public class RackJS extends RecipeJS {
    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));
        outputItems.add(parseResultItem(args.get(1)));
        processTime(((Number)args.get(2)).intValue());
        if (args.size() > 3)
            mode((String) args.get(3));
        else
            mode("empty");
    }

    @Override
    public void deserialize() {
        inputItems.add(parseIngredientItem(json.get("ingredient")));
        outputItems.add(parseResultItem(json.get("result")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            json.add("result", outputItems.get(0).toResultJson());
        }

        if (this.serializeInputs) {
            json.add("ingredient", inputItems.get(0).toJson());
        }
    }

    public void processTime(int time) {
        json.addProperty("processtime", Math.max(0, time));
    }

    public RecipeJS mode(String mode) {
        json.addProperty("mode", mode);
        return this;
    }
}
