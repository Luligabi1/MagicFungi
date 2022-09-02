package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.item.glyph.AbstractGlyphItem;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class EventRegistry {

    public static void init() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(!player.isSpectator() || !world.isClient()) {
                if(entity instanceof MobEntity && (player.getStackInHand(hand).getItem() instanceof AbstractGlyphItem)) {
                    ItemStack glyphStack = player.getStackInHand(hand);
                    ((AbstractGlyphItem) glyphStack.getItem()).executeEntityGlyph(player, glyphStack, (LivingEntity) entity);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });

    }

}