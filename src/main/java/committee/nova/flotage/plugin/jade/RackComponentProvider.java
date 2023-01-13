package committee.nova.flotage.plugin.jade;

import committee.nova.flotage.recipe.RackMode;
import committee.nova.flotage.tile.RackBlockEntity;
import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RackComponentProvider implements IComponentProvider, IServerDataProvider<BlockEntity> {

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (flag(accessor)) {
            tooltip.add(new TranslatableComponent("gui.flotage.rack.mode."+ accessor.getServerData().getString("Mode")));
        }
    }

    private static boolean flag(BlockAccessor accessor) {
        return accessor.getServerData().contains("Mode") && accessor.getServerData().contains("TotalTime") && accessor.getServerData().contains("ProcessTime");
    }

    @Override
    public void appendServerData(CompoundTag tag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean b) {
        RackBlockEntity tile = (RackBlockEntity) blockEntity;
        tag.putString("Mode", RackMode.switchingMode(level, blockEntity.getBlockPos()).getType());
        tag.putInt("TotalTime", tile.getTotalTime());
        tag.putInt("ProcessTime", tile.getProcessTime());
    }
}
