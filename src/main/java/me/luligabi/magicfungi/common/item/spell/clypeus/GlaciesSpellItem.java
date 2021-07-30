package me.luligabi.magicfungi.common.item.spell.clypeus;

import me.luligabi.magicfungi.common.item.spell.BaseSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GlaciesSpellItem extends BaseSpellItem {

    public GlaciesSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.CLYPEUS);
        setSound(SoundEvents.BLOCK_SNOW_PLACE);
        setCooldown(40*20);
        setActionType(ActionType.PLAYER);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            executeSpell(user, user.getEntityWorld());
        } else {
            user.setInPowderSnow(true);
            for(int i = 0; i < 20*10; ++i) { //TODO: Fix particle not working.
                if (user.lastRenderX != user.getX() || user.lastRenderZ != user.getZ()) {
                    world.addParticle(ParticleTypes.SNOWFLAKE, user.getX(), user.getY() + 1, user.getZ(),
                            MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083333336F, 0.05000000074505806D,
                            MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083333336F);
                    i++;
                }
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }


    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.RESISTANCE, 10, 4);
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.SLOWNESS, 10, 2);
        super.executeSpell(playerEntity, world);
    }
}
