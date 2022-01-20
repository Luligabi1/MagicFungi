package me.luligabi.magicfungi.client.renderer.item.armor;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3f;

public class ArmorMushroomRenderer  {

    public void renderHelmet(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, LivingEntity playerEntity, int light, BipedEntityModel<LivingEntity> contextModel) {
        matrixStack.push();
        contextModel.head.rotate(matrixStack);
        matrixStack.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.translate(-0.75D, -0.20D, -0.25D);
        blockRenderManager.renderBlockAsEntity(RED_MUSHROOM_STATE, matrixStack, vertexConsumerProvider, light, LivingEntityRenderer.getOverlay(playerEntity, 0.0F));
        matrixStack.pop();
    }

    public void renderChestplate(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, LivingEntity playerEntity, int light, BipedEntityModel<LivingEntity> contextModel) {
        matrixStack.push();
        contextModel.leftArm.rotate(matrixStack);
        matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
        matrixStack.scale(-0.5F, -0.5F, 0.5F);
        matrixStack.translate(0.35D, -0.35D, -1.25D);
        blockRenderManager.renderBlockAsEntity(RED_MUSHROOM_STATE, matrixStack, vertexConsumerProvider, light, LivingEntityRenderer.getOverlay(playerEntity, 0.0F));
        matrixStack.pop();
    }

    private final BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
    private static final BlockState RED_MUSHROOM_STATE = Blocks.RED_MUSHROOM.getDefaultState();
}