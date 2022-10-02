package committee.nova.flotage.tiles.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import committee.nova.flotage.tiles.RackTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RackTER extends TileEntityRenderer<RackTileEntity> {
    public RackTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(RackTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemStack stack = tile.getItem(0);
        if (!stack.isEmpty()) {
            matrixStack.pushPose();
            float pos = stack.getItem() instanceof BlockItem? 1.01f : 0.99f;
            matrixStack.translate(0.5, pos, 0.5);
            float scale = stack.getItem() instanceof BlockItem? 0.8f : 0.6f;
            matrixStack.scale(scale,scale,scale);
            Quaternion quaternion = Vector3f.XP.rotationDegrees(90);
            matrixStack.mulPose(quaternion);
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel model = itemRenderer.getModel(stack, tile.getLevel(), null);
            itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, model);
            matrixStack.popPose();
        }
    }
}
