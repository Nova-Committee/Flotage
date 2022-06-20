package committee.nova.flotage.tiles.rack;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public enum RackMode {
    EMPTY,
    SUN,
    NIGHT,
    RAIN,
    SNOW,
    RAIN_AT;

    public static RackMode switchingMode(World world, BlockPos pos) {
        if (!world.isClientSide) {
            if (world.isRaining()) {
                Biome biome = world.getBiome(pos);
                if (biome.getPrecipitation() == Biome.RainType.SNOW && biome.getTemperature(pos) < 0.15F) {
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
}
