package me.luligabi.magicfungi.common.mixin;

import me.luligabi.magicfungi.common.item.armor.ArmorMushroomFeatureRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"))
    private void constructor(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo callbackInfo) {
        PlayerEntityRenderer playerRenderer = ((PlayerEntityRenderer) (Object) this);

        this.addFeature(new ArmorMushroomFeatureRenderer(playerRenderer));
    }
}