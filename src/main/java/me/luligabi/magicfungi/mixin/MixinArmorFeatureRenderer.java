package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.client.item.armor.ArmorMushroomRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer extends FeatureRenderer<LivingEntity, BipedEntityModel<LivingEntity>> {

    private static final ArmorMushroomRenderer ARMOR_MUSHROOM_RENDERER = new ArmorMushroomRenderer();

    private MixinArmorFeatureRenderer(FeatureRendererContext<LivingEntity, BipedEntityModel<LivingEntity>> context) {
        super(context);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"))
    private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel<LivingEntity> model, CallbackInfo ci) {
        ItemStack stack = entity.getEquippedStack(armorSlot);
        Item item = stack.getItem();
        if (item == ItemRegistry.MAGICAL_FUNGI_HELMET) {
            ARMOR_MUSHROOM_RENDERER.renderHelmet(matrices, vertexConsumers, entity, light, getContextModel());
        }
        if(item == ItemRegistry.MAGICAL_FUNGI_CHESTPLATE) {
            ARMOR_MUSHROOM_RENDERER.renderChestplate(matrices, vertexConsumers, entity, light, getContextModel());
        }
    }
}
