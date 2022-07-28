package me.luligabi.magicfungi.common.item.spell.utilis;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class TractabileSpellItem extends AbstractSpellItem {

    public TractabileSpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.UTILIS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.BLOCK_ENDER_CHEST_OPEN;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.tractabileSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.PLAYER;
    }

    @Override
    public void executeSpell(PlayerEntity playerEntity, World world) {
        EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
        if (enderChestInventory == null) return;
        playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) ->
                GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory),
                Text.translatable("container.enderchest")));
        super.executeSpell(playerEntity, world);
    }

}