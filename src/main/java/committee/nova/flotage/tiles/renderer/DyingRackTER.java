package committee.nova.flotage.tiles.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import committee.nova.flotage.tiles.DryingRackTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class DyingRackTER extends TileEntityRenderer<DryingRackTileEntity> {
    public DyingRackTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(DryingRackTileEntity tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5, 1, 0.5);
        matrixStackIn.scale(0.5f,0.5f,0.5f);
        Quaternion quaternion = Vector3f.XP.rotationDegrees(90);
        matrixStackIn.mulPose(quaternion);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = tile.getItem(0).copy();
        IBakedModel ibakedmodel = itemRenderer.getModel(stack, tile.getLevel(), null);
        itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.popPose();
    }
}
