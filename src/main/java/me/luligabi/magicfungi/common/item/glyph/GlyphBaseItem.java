package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GlyphBaseItem extends Item {

    protected SoundEvent soundEvent;
    protected MushroomType mushroomType;
    protected BlockPos blockPos;

    public GlyphBaseItem (Item.Settings settings) {
        super(settings);
        setMushroomType(MushroomType.INCOGNITA);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.NEUTRAL, 1F, 1F);
        if (!world.isClient) {
            executeGlyph(user);
        }
        if (!user.getAbilities().creativeMode) {
            context.getStack().decrement(1);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return super.useOnBlock(context);
    }

    public void executeGlyph(PlayerEntity playerEntity) { }

    public void setSound(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public MushroomType getMushroomType() {
        return mushroomType;
    }

    public void setMushroomType(MushroomType mushroomType) {
        this.mushroomType = mushroomType;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", mushroomType.getFancyName(), mushroomType.getStatsName())
                        .formatted(MushroomType.getLightColor(getMushroomType()))));
    }
}