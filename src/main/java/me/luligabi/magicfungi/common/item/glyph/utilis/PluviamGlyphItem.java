package me.luligabi.magicfungi.common.item.glyph.utilis;

import me.luligabi.magicfungi.common.item.glyph.AbstractGlyphItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class PluviamGlyphItem extends AbstractGlyphItem {

    public PluviamGlyphItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.UTILIS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.WEATHER_RAIN_ABOVE;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.WORLD;
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        World world = playerEntity.getEntityWorld();

        if(world.isRaining() || world.isThundering()) {
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.refreshPositionAfterTeleport(blockPos.getX(), blockPos.getY(), blockPos.getZ());

            world.spawnEntity(lightningEntity);
            ((ServerWorld) world).setWeather(world.random.nextInt() * 10, 0, false, false);
            super.executeGlyph(playerEntity, itemStack);
        }
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {}

}