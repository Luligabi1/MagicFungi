package me.luligabi.magicfungi.client.renderer.entity.feature;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.entity.MorbusMooshroomEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.Vec3f;

public class MorbusMooshroomMushroomFeatureRenderer extends FeatureRenderer<MorbusMooshroomEntity, CowEntityModel<MorbusMooshroomEntity>> {

    public MorbusMooshroomMushroomFeatureRenderer(FeatureRendererContext<MorbusMooshroomEntity, CowEntityModel<MorbusMooshroomEntity>> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MorbusMooshroomEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isBaby()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(entity) && entity.isInvisible();
            if (!entity.isInvisible() || bl) {
                BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();
                BlockState blockState = BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK.getDefaultState();
                int m = LivingEntityRenderer.getOverlay(entity, 0.0F);
                BakedModel bakedModel = blockRenderManager.getModel(blockState);
                matrices.push();
                matrices.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
                matrices.scale(-1.0F, -1.0F, 1.0F);
                matrices.translate(-0.5D, -0.5D, -0.5D);
                renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
                matrices.pop();
                matrices.push();
                matrices.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
                matrices.translate(0.10000000149011612D, 0.0D, -0.6000000238418579D);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
                matrices.scale(-1.0F, -1.0F, 1.0F);
                matrices.translate(-0.5D, -0.5D, -0.5D);
                renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
                matrices.pop();
                matrices.push();
                this.getContextModel().getHead().rotate(matrices);
                matrices.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
                matrices.scale(-1.0F, -1.0F, 1.0F);
                matrices.translate(-0.5D, -0.5D, -0.5D);
                renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
                matrices.pop();
            }
        }
    }

    private void renderMushroom(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockRenderManager blockRenderManager, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0F, 0.0F, 0.0F, light, overlay);
        } else {
            blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }

    }
}