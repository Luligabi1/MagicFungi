package me.luligabi.magicfungi.common.mixin;

import com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient;
import me.luligabi.magicfungi.client.MagicFungiClient;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {
    private ShieldEntityModel modelClypeusShield;
    private static final SpriteIdentifier CLYPEUS_SHIELD_BASE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MagicFungi.MOD_ID, "entity/clypeus_shield_base"));
    private static final SpriteIdentifier CLYPEUS_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MagicFungi.MOD_ID, "entity/clypeus_shield_base_nopattern"));

    @Final
    @Shadow
    private EntityModelLoader entityModelLoader;


    @Inject(method = "reload", at = @At("HEAD"))
    private void setModelClypeusShield(CallbackInfo ci){
        this.modelClypeusShield = new ShieldEntityModel(this.entityModelLoader.getModelPart(MagicFungiClient.CLYPEUS_SHIELD_MODEL_LAYER));
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void mainRender(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {
        if (stack.isOf(ItemRegistry.CLYPEUS_SHIELD)) {
            FabricShieldLibClient.renderBanner(stack, matrices, vertexConsumers, light, overlay, modelClypeusShield, CLYPEUS_SHIELD_BASE, CLYPEUS_SHIELD_BASE_NO_PATTERN);
        }
    }

}