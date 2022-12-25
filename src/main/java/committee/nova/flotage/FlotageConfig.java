package committee.nova.flotage;

import net.minecraftforge.common.ForgeConfigSpec;

public class FlotageConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue RACK_MAX_SIZE;
    public static ForgeConfigSpec.BooleanValue RACK_RECIPE_CONDITIONS;

    public static ForgeConfigSpec.DoubleValue RAFT_DAMAGE_PROBABILITY;
    public static ForgeConfigSpec.DoubleValue RAFT_FIX_COST_CHANCE;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Rack settings").push("rack");
        RACK_MAX_SIZE = COMMON_BUILDER.comment("Max stack size").defineInRange("size", 1, 1, 16);
        RACK_RECIPE_CONDITIONS = COMMON_BUILDER.comment("Enable formula conditions").define("value", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Raft settings").push("raft");
        RAFT_DAMAGE_PROBABILITY = COMMON_BUILDER.comment("Probability of random damage in rainy days").defineInRange("break_chance", 1f, 0f, 1f);
        RAFT_FIX_COST_CHANCE = COMMON_BUILDER.comment("Probability of material consumption in repairing Raft").defineInRange("fix_cost_chance", 0.3f, 0f, 1f);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
