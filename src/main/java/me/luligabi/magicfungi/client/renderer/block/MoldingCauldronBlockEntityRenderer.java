package me.luligabi.magicfungi.client.renderer.block;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.block.crafting.moldingcauldron.MoldingCauldronBlock;
import me.luligabi.magicfungi.common.block.crafting.moldingcauldron.MoldingCauldronBlockEntity;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MoldingCauldronBlockEntityRenderer implements BlockEntityRenderer<MoldingCauldronBlockEntity> {

    public MoldingCauldronBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(MoldingCauldronBlockEntity entity, float tickDelta, MatrixStack ms, VertexConsumerProvider vcp, int light, int overlay) {
        if(!entity.getWorld().getBlockState(entity.getPos()).isOf(BlockRegistry.MOLDING_CAULDRON_BLOCK)) return;

        ms.push();
        if(entity.getWorld().getBlockState(entity.getPos()).get(MoldingCauldronBlock.FULL)) {
            drawWater(ms, vcp, entity.getWorld(), entity.getPos(), entity);
        }
        if(!entity.isEmpty()) {
            ms.translate(0.5D, 0.25D, 0.5D);
            ms.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90F));

            Direction direction = entity.getWorld().getBlockState(entity.getPos()).get(MoldingCauldronBlock.FACING);
            ms.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(getItemAngle(direction)));
            ms.scale(0.75F, 0.75F, 0.75F);

            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, ms, vcp, (int) entity.getPos().asLong());
        }
        ms.pop();
    }

    private float getItemAngle(Direction direction) {
        return switch(direction) {
            case NORTH -> 180;
            case SOUTH -> 0;
            case WEST, EAST -> direction.getHorizontal() * 90;
            default -> throw new IllegalStateException("Unexpected Molding Cauldron direction: " + direction);
        };
    }

    /*
     * This code is derivative of the one found in Modern Industrialization, copyrighted by Azercoco & Technici4n and licensed under MIT.
     *
     * You may see the original code here: https://github.com/AztechMC/Modern-Industrialization/blob/8e1be7d3b607614ded24f60ec5927d97c6649cc9/src/main/java/aztech/modern_industrialization/util/RenderHelper.java#L124
     */
    @SuppressWarnings("UnstableApiUsage") // TODO: Find way to add transparency
    private static void drawWater(MatrixStack ms, VertexConsumerProvider vcp, @Nullable World world, @Nullable BlockPos pos, MoldingCauldronBlockEntity entity) {
        FluidVariant water = FluidVariant.of(Fluids.WATER);
        VertexConsumer vc = vcp.getBuffer(RenderLayer.getCutout());
        Sprite sprite = FluidVariantRendering.getSprite(water);

        int waterColor = ((OCEAN_COLOR[0]&0x0ff)<<16)|((OCEAN_COLOR[1]&0x0ff)<<8)|(OCEAN_COLOR[2]&0x0ff);
        int color = entity.standBy ? waterColor : interpolateColor(entity.processTime, entity.totalProcessTime);

        float r = ((color >> 16) & 255) / 256f;
        float g = ((color >> 8) & 255) / 256f;
        float b = (color & 255) / 256f;

        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        QuadEmitter emitter = renderer.meshBuilder().getEmitter();

        emitter.square(Direction.UP, 0.125F, 0.125F, 0.875F, 0.875F, 0.3125F);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);
        vc.quad(ms.peek(), emitter.toBakedQuad(0, sprite, false), r, g, b, 0x00F0_00F0, OverlayTexture.DEFAULT_UV);
    }

    private static int interpolateColor(int currentClass, int numOfClasses) {
        float[] minhsb = Color.RGBtoHSB(SWAMP_COLOR[0], SWAMP_COLOR[1], SWAMP_COLOR[2], null); // Swamp Water Color
        float[] maxhsb = Color.RGBtoHSB(OCEAN_COLOR[0], OCEAN_COLOR[1], OCEAN_COLOR[2], null);

        float minHue = minhsb[0];
        float maxHue = maxhsb[0];

        float minSat = minhsb[1];
        float maxSat = maxhsb[1];

        float minBri = minhsb[2];
        float maxBri = maxhsb[2];

        double hue = minHue + (currentClass * (maxHue - minHue) / (numOfClasses - 1));
        double saturation = minSat + (currentClass * (maxSat - minSat) / (numOfClasses - 1));
        double brightness = minBri + (currentClass * (maxBri - minBri) / (numOfClasses - 1));

        Color hsbColor = Color.getHSBColor((float) hue, (float) saturation, (float) brightness);

        return ((hsbColor.getRed()&0x0ff)<<16)|((hsbColor.getGreen()&0x0ff)<<8)|(hsbColor.getBlue()&0x0ff);
    }

    private static final int[] OCEAN_COLOR = {63, 118, 228};
    private static final int[] SWAMP_COLOR = {97, 123, 100};

}