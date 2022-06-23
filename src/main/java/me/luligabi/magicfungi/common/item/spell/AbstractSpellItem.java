package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.client.tooltip.spell.SpellTooltipData;
import me.luligabi.magicfungi.mixin.PlayerInventoryAccessor;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
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
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractSpellItem extends Item {

    public AbstractSpellItem(Settings settings) {
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
        applyCooldown(playerEntity);
        playerEntity.getEntityWorld().playSound(null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                getSoundEvent(), SoundCategory.NEUTRAL, 1F, 1F);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    /*
     * Applies cooldown to spells, following the instructions bellow:
     *
     * - The spell that was cast will receive a cooldown with a variable time, set individually for each spell;
     * - If another spell has the same MushroomType as the cast spell, the other spell will receive a static 15 seconds cooldown.
     *
     * This is made mostly for balancing, as this avoids spell spams being made.
     */
    private void applyCooldown(PlayerEntity playerEntity) {
        playerEntity.getItemCooldownManager().set(this, getCooldown());

        for (DefaultedList<ItemStack> itemStacks : ((PlayerInventoryAccessor) playerEntity.getInventory()).getCombinedInventory()) {
            for (ItemStack itemStack : itemStacks) {
                if (itemStack.getItem() instanceof AbstractSpellItem) {
                    if (itemStack.getItem() != this) {
                        if (((AbstractSpellItem) itemStack.getItem()).getMushroomType() == this.getMushroomType()) {
                            playerEntity.getItemCooldownManager().set(itemStack.getItem(), 15*20);
                        }
                    }
                }
            }
        }
    }

    public abstract @NotNull MushroomType getMushroomType();

    public abstract @NotNull SoundEvent getSoundEvent();

    public abstract int getCooldown();

    public abstract @NotNull ActionType getActionType();


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!Screen.hasShiftDown()) return;
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", getMushroomType().getFancyName(), getMushroomType().getStatsName())
                            .formatted(MushroomType.getLightColor(getMushroomType()))));
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.3")
                .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.4", getCooldown()/20)
                            .formatted(MushroomType.getLightColor(getMushroomType()))));
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.5")
                .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(getActionType().getTranslatableText()
                            .formatted(MushroomType.getLightColor(getMushroomType()))));
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SpellTooltipData(getMushroomType(), getCooldown(), getActionType()));
    }

}