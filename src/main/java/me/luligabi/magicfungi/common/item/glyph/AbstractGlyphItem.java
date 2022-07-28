package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.client.tooltip.glyph.GlyphTooltipData;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGlyphItem extends Item {

    protected BlockPos blockPos;

    public AbstractGlyphItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        if (!world.isClient()) {
            executeBlockGlyph(user, context.getStack());
        }
        return ActionResult.CONSUME;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getEntityWorld();
        if (!world.isClient) {
            executeEntityGlyph(user, stack, entity);
        }
        return ActionResult.CONSUME;
    }

    protected void executeGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        itemStack.decrement(1);
        playerEntity.getEntityWorld().playSound(null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                getSoundEvent(), SoundCategory.NEUTRAL, 1F, 1F);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    public abstract @NotNull MushroomType getMushroomType();

    public abstract @NotNull SoundEvent getSoundEvent();

    public abstract @NotNull ActionType getActionType();


    public abstract void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack);

    public abstract void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity);

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!Screen.hasShiftDown()) return;
        tooltip.add(Text.translatable("tooltip.magicfungi.spell_info.1")
                    .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(Text.translatable("tooltip.magicfungi.spell_info.2", getMushroomType().getFancyName(), getMushroomType().getStatsName())
                        .formatted(MushroomType.getLightColor(getMushroomType()))));
        tooltip.add(Text.translatable("tooltip.magicfungi.spell_info.5")
                    .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(getActionType().getTranslatableText()
                        .copy().formatted(MushroomType.getLightColor(getMushroomType()))));
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new GlyphTooltipData(getMushroomType(), getActionType()));
    }

}