package committee.nova.flotage.tile.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import committee.nova.flotage.tile.RackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RackTER implements BlockEntityRenderer<RackBlockEntity> {
    public RackTER(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RackBlockEntity tile, float ticks, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        ItemStack stack = tile.getItem(0);
        int i = (int)tile.getBlockPos().asLong();
        Direction direction = tile.getItemDirection();
        if (!stack.isEmpty()) {
            pose.pushPose();
            float pos = stack.getItem() instanceof BlockItem ? 1.01f : 0.99f;
            pose.translate(0.5, pos, 0.5);
            float scale = stack.getItem() instanceof BlockItem? 0.8f : 0.6f;
            pose.scale(scale,scale,scale);
            pose.mulPose(direction.getRotation());
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GROUND, light, overlay, pose, buffer, Minecraft.getInstance().level, 0);
            pose.popPose();
        }
    }
}
