package committee.nova.flotage.plugin.kjs;

import dev.latvian.kubejs.KubeJSPlugin;
import dev.latvian.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.util.ResourceLocation;

public class FloKJS extends KubeJSPlugin {
    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation("flotage:rack"), RackJS::new);
    }
}
