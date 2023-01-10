package committee.nova.flotage.data.provider;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.FlotageUtil;
import committee.nova.flotage.block.*;
import committee.nova.flotage.data.NameUtil;
import committee.nova.flotage.init.FloBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;


public class FloBlocksProvider extends BlockStateProvider {

    public FloBlocksProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Flotage.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        Collection<RegistryObject<Block>> collection = FloBlocks.BLOCKS.getEntries();

        for (RegistryObject<Block> ro : collection) {
            Block block = ro.get();
            String name = NameUtil.name(block);
            ResourceLocation parent = FlotageUtil.asRes("base/error");
            boolean hasTop = (block instanceof RackBlock || block instanceof CrossedFenceBlock);

            if (block instanceof BrokenRaftBlock) {
                parent = FlotageUtil.asRes("base/raft");
            } else if (block instanceof RaftBlock){
                parent = FlotageUtil.asRes("base/broken_raft");
            } else if (block instanceof RackBlock) {
                parent = FlotageUtil.asRes("base/rack");
            } else if (block instanceof CrossedFenceBlock) {
                parent = FlotageUtil.asRes("base/crossed_fence");
            } else if (block instanceof SimpleFenceBlock) {
                parent = FlotageUtil.asRes("base/fence");
            }

            if (hasTop) {
                simpleBlock(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("1", NameUtil.textureTop(block))
                        .texture("particle", NameUtil.texture(block))
                );
                simpleBlockItem(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("1", NameUtil.textureTop(block))
                        .texture("particle", NameUtil.texture(block))
                );
            } else if (block instanceof SimpleFenceBlock) {
                horizontalBlock(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("1", NameUtil.textureTop(block))
                        .texture("particle", NameUtil.texture(block))
                );
                simpleBlockItem(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("1", NameUtil.textureTop(block))
                        .texture("particle", NameUtil.texture(block))
                );
            } else {
                simpleBlock(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("particle", NameUtil.texture(block))
                );
                simpleBlockItem(block, models()
                        .withExistingParent(name, parent)
                        .texture("0", NameUtil.texture(block))
                        .texture("particle", NameUtil.texture(block))
                );
            }
        }
    }
}
