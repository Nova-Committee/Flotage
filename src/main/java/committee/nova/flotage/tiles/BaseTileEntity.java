package committee.nova.flotage.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public abstract class BaseTileEntity  extends TileEntity {
    public BaseTileEntity(TileEntityType<?> entityType) {
        super(entityType);
    }

    public abstract CompoundNBT write(CompoundNBT tag);
    public abstract void read(CompoundNBT tag);

    @Deprecated
    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        read(tag);
    }

    @Deprecated
    @Override
    public CompoundNBT save(CompoundNBT tag) {
        CompoundNBT nbt = super.save(tag);
        return write(nbt);
    }

    @Deprecated
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 233, getUpdateTag());
    }

    @Deprecated
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        return write(nbt);
    }

    @Deprecated
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        assert this.level != null;
        handleUpdateTag(this.level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        read(tag);
    }
}
