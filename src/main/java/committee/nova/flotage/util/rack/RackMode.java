package committee.nova.flotage.util.rack;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;

public enum RackMode {
    EMPTY("empty"),
    SUN("sun"),
    NIGHT("night"),
    RAIN("rain"),
    SNOW("snow"),
    RAIN_AT("rain_at"),
    SNOW_AT("snow_at");
    private final String type;

    RackMode(String type) {
        this.type = type;
    }

    public static RackMode switchingMode(World world, BlockPos pos) {
        if (!world.isClientSide) {
            if (world.isRaining()) {
                Biome biome = world.getBiome(pos);
                if (biome.getPrecipitation() == Biome.RainType.SNOW && biome.getTemperature(pos) < 0.15F) {
                    if (world.canSeeSky(pos) && !(world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, pos).getY() > pos.getY())) {
                        return SNOW_AT;
                    }
                    return SNOW;
                } else if (world.isRainingAt(pos.above())) {
                    return RAIN_AT;
                }
                return RAIN;
            }else {
                if (world.isDay()) {
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
