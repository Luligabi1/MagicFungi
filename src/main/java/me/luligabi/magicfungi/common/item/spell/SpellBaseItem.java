package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SpellBaseItem extends Item {

    protected int cooldown = 20;
    protected SoundEvent soundEvent;
    protected MushroomType mushroomType;

    public SpellBaseItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            executeSpell(user, user.getEntityWorld());
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    protected void executeSpell(PlayerEntity playerEntity, World world) {
        playerEntity.getItemCooldownManager().set(this, cooldown);
        playerEntity.getEntityWorld().playSound(null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                soundEvent, SoundCategory.NEUTRAL, 1F, 1F);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    protected void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    protected void setSound(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public MushroomType getMushroomType() {
        return mushroomType;
    }

    protected void setMushroomType(MushroomType mushroomType) {
        this.mushroomType = mushroomType;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                    .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", mushroomType.getFancyName(), mushroomType.getStatsName())
                    .formatted(MushroomType.getLightColor(getMushroomType()))));
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.3")
                .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.4", cooldown/20)
                        .formatted(MushroomType.getLightColor(getMushroomType()))));
    }
}