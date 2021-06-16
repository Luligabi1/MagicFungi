package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.registry.ScreenHandllingRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class SpellDiscoveryScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9));
    }

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandllingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        // Crafting Slots
        this.addSlot(new Slot(inventory, 0, 62, 56 + -2 * 18)); // inputA
        this.addSlot(new Slot(inventory, 1, 62 + 2 * 18, 56 + -2 * 18)); // inputB
        this.addSlot(new Slot(inventory, 2, 62 + -1 * 18, 56 + -1 * 18)); // inputC
        this.addSlot(new Slot(inventory, 3, 62 + 3 * 18, 56 + -1 * 18)); // inputD
        this.addSlot(new Slot(inventory, 4, 62 + -1 * 18, 56 + 18)); // inputE
        this.addSlot(new Slot(inventory, 5, 62 + 3 * 18, 56 + 18)); // inputF
        this.addSlot(new Slot(inventory, 6, 62, 56 + 2 * 18)); // inputG
        this.addSlot(new Slot(inventory, 7, 62 + 2 * 18, 56 + 2 * 18)); // inputH

        this.addSlot(new Slot(inventory, 8, 62 + 18, 56)); // Output

        int m;
        int l;

        //Player Inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 123 + m * 18));
            }
        }
        //Player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 181));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}