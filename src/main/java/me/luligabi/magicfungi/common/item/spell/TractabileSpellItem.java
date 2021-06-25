package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class TractabileSpellItem extends SpellBaseItem {

    public TractabileSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.UTILIS);
        setCooldown(3*20);
        setSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN);
    }

    @Override
    public void executeSpell(PlayerEntity playerEntity, World world) {
        EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
        if (enderChestInventory != null) {
            playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) ->
                    GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory),
                    new TranslatableText("container.enderchest")));
            PiglinBrain.onGuardedBlockInteracted(playerEntity, true);
        }
        super.executeSpell(playerEntity, world);
    }
}