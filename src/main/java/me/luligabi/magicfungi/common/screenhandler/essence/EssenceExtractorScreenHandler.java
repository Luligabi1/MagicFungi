package me.luligabi.magicfungi.common.screenhandler.essence;

import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class EssenceExtractorScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private final Slot inputSlot0;
    private final Slot inputSlot1;
    private final Slot inputSlot2;
    private final Slot ingredientSlot;

    public EssenceExtractorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(3));
    }

    public EssenceExtractorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlingRegistry.ESSENCE_EXTRACTOR_SCREEN_HANDLER, syncId);
        checkSize(inventory, 5);
        checkDataCount(propertyDelegate, 3);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.inputSlot0 = this.addSlot(new EssenceSlot(inventory, 0, 56, 51));
        this.inputSlot1 = this.addSlot(new EssenceSlot(inventory, 1, 79, 58));
        this.inputSlot2 = this.addSlot(new EssenceSlot(inventory, 2, 102, 51));
        this.ingredientSlot = this.addSlot(new Slot(inventory, 3, 79, 17));
        this.addSlot(new CatalystSlot(inventory, 4, 17, 17));
        this.addProperties(propertyDelegate);

        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if ((index < 0 || index > 2) && index != 3 && index != 4) {
                if (EssenceExtractorScreenHandler.CatalystSlot.matches(itemStack)) {
                    if (this.insertItem(itemStack2, 4, 5, false) || this.ingredientSlot.canInsert(itemStack2) && !this.insertItem(itemStack2, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (essenceSlotTransfer(inputSlot0, itemStack2)) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (essenceSlotTransfer(inputSlot1, itemStack2)) {
                    if (!this.insertItem(itemStack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (essenceSlotTransfer(inputSlot2, itemStack2)) {
                    if (!this.insertItem(itemStack2, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.ingredientSlot.canInsert(itemStack2) && !itemStack2.isOf(Items.GLASS_BOTTLE)) {
                    if (!this.insertItem(itemStack2, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 5 && index < 32) {
                    if (!this.insertItem(itemStack2, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 32 && index < 41) {
                    if (!this.insertItem(itemStack2, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(itemStack2, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(itemStack2, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemStack2, itemStack);
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    public int getFuel() {
        return this.propertyDelegate.get(1);
    }

    public int getFuelType() {
        return this.propertyDelegate.get(2);
    }

    public int getBrewTime() {
        return this.propertyDelegate.get(0);
    }


    private static class EssenceSlot extends Slot {

        public EssenceSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return matches(stack);
        }

        public int getMaxItemCount() {
            return 1;
        }

        public static boolean matches(ItemStack stack) {
            return stack.isOf(Items.GLASS_BOTTLE);
        }

    }


    private static class CatalystSlot extends Slot {

        public CatalystSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return matches(stack);
        }

        public int getMaxItemCount() {
            return 64;
        }

        public static boolean matches(ItemStack stack) {
            return isCatalyst(stack);
        }

    }


    public static boolean isCatalyst(ItemStack stack) {
        return stack.isIn(TagRegistry.IMPETUS_CATALYST) ||
                stack.isIn(TagRegistry.CLYPEUS_CATALYST) ||
                stack.isIn(TagRegistry.UTILIS_CATALYST) ||
                stack.isIn(TagRegistry.VIVIFICA_CATALYST) ||
                stack.isIn(TagRegistry.MORBUS_CATALYST);
    }

    private boolean essenceSlotTransfer(Slot slot, ItemStack stack) {
        return !slot.hasStack() && slot.canInsert(stack);
    }
}