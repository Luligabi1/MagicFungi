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

    //TODO: Remove comments
    // TODO: Change slot count and positions

    //This constructor gets called on the client when the server wants it to open the screenHandler,
    //The client will call the other constructor with an empty Inventory and the screenHandler will automatically
    //sync this empty inventory with the inventory on the server.
    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9));
    }

    //This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
    //and can therefore directly provide it as an argument. This inventory will then be synced to the client.
    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandllingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        int m;
        int l;
        //Our inventory
        this.addSlot(new Slot(inventory, 0, 62 + 0 * 18, 17 + -2 * 18)); // inputA
        this.addSlot(new Slot(inventory, 1, 62 + 2 * 18, 17 + -2 * 18)); // inputB
        this.addSlot(new Slot(inventory, 2, 62 + -1 * 18, 17 + -1 * 18)); // inputC
        this.addSlot(new Slot(inventory, 3, 62 + 3 * 18, 17 + -1 * 18)); // inputD
        this.addSlot(new Slot(inventory, 4, 62 + -1 * 18, 17 + 1 * 18)); // inputE
        this.addSlot(new Slot(inventory, 5, 62 + 3 * 18, 17 + 1 * 18)); // inputF
        this.addSlot(new Slot(inventory, 6, 62 + 0 * 18, 17 + 2 * 18)); // inputG
        this.addSlot(new Slot(inventory, 7, 62 + 2 * 18, 17 + 2 * 18)); // inputH

        this.addSlot(new Slot(inventory, 8, 62 + 1 * 18, 17 + 0 * 18)); // output

        /*for (m = 0; m < 3; ++m) {
            for (l = 0; l < 3; ++l) {
                this.addSlot(new Slot(inventory, l + m * 3, 62 + l * 18, 17 + m * 18));
            }
        }*/
        //Player Inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        //Player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
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