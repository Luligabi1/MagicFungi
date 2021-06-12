package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class SpellBaseItem extends Item {

    protected int cooldown = 10;
    protected SoundEvent soundEvent;
    protected MushroomType mushroomType;

    public SpellBaseItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.UNKNOWN);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.NEUTRAL, 1F, 1F);
        user.getItemCooldownManager().set(this, cooldown);
        if (!world.isClient) {
            executeSpell();
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    public void executeSpell() { }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public MushroomType getMushroomType() {
        return mushroomType;
    }

    public void setMushroomType(MushroomType mushroomType) {
        this.mushroomType = mushroomType;
    }

    //TODO: Add tooltip info.
}