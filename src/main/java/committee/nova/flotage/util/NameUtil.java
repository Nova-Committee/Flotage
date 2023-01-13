package committee.nova.flotage.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;

public class NameUtil {
    public static String name(ForgeRegistryEntry<?> entry) {
        return Objects.requireNonNull(entry.getRegistryName()).getPath();
    }

    public static ResourceLocation texture(Block block) {
        String name = name(block);
        if (name.startsWith("acacia")) {
            return new ResourceLocation("block/acacia_log");
        } else if (name.startsWith("birch")) {
            return new ResourceLocation("block/birch_log");
        } else if (name.startsWith("crimson")) {
            return new ResourceLocation("block/crimson_stem");
        } else if (name.startsWith("dark_oak")) {
            return new ResourceLocation("block/dark_oak_log");
        } else if (name.startsWith("jungle")) {
            return new ResourceLocation("block/jungle_log");
        } else if (name.startsWith("spruce")) {
            return new ResourceLocation("block/spruce_log");
        } else if(name.startsWith("bamboo")) {
            return new ResourceLocation("block/bamboo_stalk");
        } else if (name.startsWith("warped")) {
            return new ResourceLocation("block/warped_stem");
        } else {
            return new ResourceLocation("block/oak_log");
        }
    }

    public static ResourceLocation textureTop(Block block) {
        ResourceLocation rl0 = texture(block);
        return new ResourceLocation(rl0.getNamespace(), rl0.getPath()+"_top");
    }
}
