package me.luligabi.magicfungi.common.item.spell.utilis;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.BaseSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class TractabileSpellItem extends BaseSpellItem {

    public TractabileSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.UTILIS);
        setCooldown(MagicFungi.CONFIG.tractabileSpellCooldown*20);
        setSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN);
        setActionType(ActionType.PLAYER);
    }

    @Override
    public void executeSpell(PlayerEntity playerEntity, World world) {
        EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
        if (enderChestInventory == null) return;
        playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) ->
                GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory),
                new TranslatableText("container.enderchest")));
        super.executeSpell(playerEntity, world);
    }
}