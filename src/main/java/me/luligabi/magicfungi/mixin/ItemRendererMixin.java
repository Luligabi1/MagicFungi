package me.luligabi.magicfungi.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.item.relic.SpecialChargeRelic;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At("RETURN"))
    public void renderGuiItemOverlay(TextRenderer renderer, ItemStack stack, int x, int y, String countLabel, CallbackInfo ci) {
        ItemRenderer itemRenderer = ((ItemRenderer) (Object) this);

        if(!stack.isEmpty()) {
            if(stack.getItem() instanceof SpecialChargeRelic) {
                if(((SpecialChargeRelic) stack.getItem()).getCharge(stack) <= 0) return;
                RenderSystem.disableDepthTest();
                RenderSystem.disableTexture();
                RenderSystem.disableBlend();
                Tessellator string = Tessellator.getInstance();
                BufferBuilder immediate = string.getBuffer();
                int chargeBarColor = ((SpecialChargeRelic) stack.getItem()).getChargeBarColor(stack);
                int chargeBarStep = ((SpecialChargeRelic) stack.getItem()).getChargeBarStep(stack);
                ((ItemRendererInvoker)  itemRenderer).invoke_renderGuiQuad(immediate, x, y + 15, 16, 1, 0, 0, 0, 255);
                ((ItemRendererInvoker)  itemRenderer).invoke_renderGuiQuad(immediate, x, y + 15, chargeBarStep, 1, chargeBarColor >> 16 & 255, chargeBarColor >> 8 & 255, chargeBarColor & 255, 255);
                RenderSystem.enableBlend();
                RenderSystem.enableTexture();
                RenderSystem.enableDepthTest();
            }
        }
    }
}