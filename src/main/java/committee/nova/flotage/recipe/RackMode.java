package committee.nova.flotage.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.levelgen.Heightmap;

public enum RackMode {
    EMPTY("empty"),
    SUN("sun"),
    NIGHT("night"),
    RAIN("rain"),
    SNOW("snow"),
    SMOKE("smoke"),
    RAIN_AT("rain_at"),
    SNOW_AT("snow_at");
    private final String type;

    RackMode(String type) {
        this.type = type;
    }

    public static RackMode switchingMode(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            if (level.isRaining()) {
                Biome biome = level.getBiome(pos).value();
                if (biome.getPrecipitationAt(pos) == Biome.Precipitation.SNOW && biome.coldEnoughToSnow(pos)) {
                    if (level.canSeeSky(pos) && !(level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos).getY() > pos.getY())) {
                        return SNOW_AT;
                    }
                    return SNOW;
                } else if (level.isRainingAt(pos.above())) {
                    return RAIN_AT;
                }
                if (level.getBlockState(pos.below()).is(Blocks.CAMPFIRE)) {
                    if (level.getBlockState(pos.below()).getValue(CampfireBlock.LIT)) {
                        return SMOKE;
                    }
                }
                return RAIN;
            }else {
                if (level.getBlockState(pos.below()).is(Blocks.CAMPFIRE)) {
                    if (level.getBlockState(pos.below()).getValue(CampfireBlock.LIT)) {
                        return SMOKE;
                    }
                }
                if (level.isDay()) {
                    return SUN;
                }
                return NIGHT;
            }
        }
        return EMPTY;
    }

    public String getType() {
        return type;
    }

    public static RackMode match(String s) {
        for (RackMode mode : values()) {
            if (mode.getType().equals(s))
                return mode;
        }
        return RackMode.EMPTY;
    }
}
