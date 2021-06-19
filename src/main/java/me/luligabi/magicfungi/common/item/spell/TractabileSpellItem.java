package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class TractabileSpellItem extends SpellBaseItem {

    public TractabileSpellItem(Settings settings) {
        super(settings);
        setSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN);
        setCooldown(60);
        setMushroomType(MushroomType.UTILIS);
    }

    @Override
    public void executeSpell(PlayerEntity playerEntity, World world) {
        EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
        if (enderChestInventory != null) {
            playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) ->
                    GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory),
                    new TranslatableText("container.enderchest")));
            playSound(playerEntity);
            playerEntity.incrementStat(Stats.OPEN_ENDERCHEST);
            PiglinBrain.onGuardedBlockInteracted(playerEntity, true);
        }
    }
}