package me.luligabi.magicfungi.common.item.armor;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3f;

public class ArmorMushroomFeatureRenderer <T extends PlayerEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {

    public ArmorMushroomFeatureRenderer(FeatureRendererContext<T, PlayerEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T playerEntity, float f, float g, float h, float j, float k, float l) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        boolean bl = minecraftClient.hasOutline(playerEntity) && playerEntity.isInvisible();
        if (!playerEntity.isInvisible() || bl) {
            BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();
            BlockState blockState = Blocks.RED_MUSHROOM.getDefaultState();
            int m = LivingEntityRenderer.getOverlay(playerEntity, 0.0F);
            BakedModel bakedModel = blockRenderManager.getModel(blockState);

            if(playerEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ItemRegistry.MAGICAL_FUNGI_HELMET) {
                matrixStack.push();
                this.getContextModel().head.rotate(matrixStack);
                matrixStack.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.75D, -0.20D, -0.25D);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, blockState, m, bakedModel);
                matrixStack.pop();
            }

            if(playerEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() == ItemRegistry.MAGICAL_FUNGI_CHESTPLATE) {
                matrixStack.push();
                this.getContextModel().leftArm.rotate(matrixStack);
                matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
                matrixStack.scale(-0.5F, -0.5F, 0.5F);
                matrixStack.translate(0.35D, -0.35D, -1.25D);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, blockState, m, bakedModel);
                matrixStack.pop();
            }

        }
    }

    private void renderMushroom(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockRenderManager blockRenderManager, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0F, 0.0F, 0.0F, light, overlay);
        } else {
            blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }

    }
}