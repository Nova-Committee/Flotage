package committee.nova.flotage;

import net.minecraftforge.common.ForgeConfigSpec;

public class FlotageConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue RACK_MAX_SIZE;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Rack settings").push("rack");
        RACK_MAX_SIZE = COMMON_BUILDER.comment("Max stack size").defineInRange("size", 1, 1, 16);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
