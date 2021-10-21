package me.luligabi.magicfungi.common.mixin;

import me.luligabi.magicfungi.common.item.armor.ArmorMushroomFeatureRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {

    protected LivingEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "<init>",
            at = @At(value = "TAIL"))
    private void constructor(EntityRendererFactory.Context ctx, M model, float shadowRadius, CallbackInfo callbackInfo) {
        LivingEntityRenderer livingEntityRenderer = ((LivingEntityRenderer) (Object) this);

        if(livingEntityRenderer.getModel() instanceof BipedEntityModel<?>) {
            ((LivingEntityRendererInvoker) livingEntityRenderer).invokeAddFeature(new ArmorMushroomFeatureRenderer<LivingEntity>(livingEntityRenderer));
        }
    }
}