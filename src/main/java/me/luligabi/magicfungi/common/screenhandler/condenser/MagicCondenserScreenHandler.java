package me.luligabi.magicfungi.common.screenhandler.condenser;

import me.luligabi.magicfungi.common.item.misc.EssenceItem;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NetherStarItem;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MagicCondenserScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    private final Slot inputSlot;
    private final Slot essenceSlot;
    private final Slot netherStarSlot;

    public MagicCondenserScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(3));
    }

    public MagicCondenserScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlingRegistry.MAGIC_CONDENSER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 2);
        checkDataCount(propertyDelegate, 3);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.inputSlot = this.addSlot(new MagicCondenserScreenHandler.InputSlot(inventory, false, 0, 80, 56));
        this.essenceSlot = this.addSlot(new MagicCondenserScreenHandler.EssenceSlot(inventory, 1, 8, 95));
        this.netherStarSlot = this.addSlot(new MagicCondenserScreenHandler.NetherStarSlot(inventory, 2, 152, 95));
        this.addProperties(propertyDelegate);

        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 126+ i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 184));
        }

    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    /*public ItemStack transferSlot(PlayerEntity player, int index) { //TODO: Implement shift-click interaction
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
    }*/

    private static class InputSlot extends Slot {

        public InputSlot(Inventory inventory, boolean isProcessingRecipe, int index, int x, int y) {
            super(inventory, index, x, y);
            this.isProcessingRecipe = isProcessingRecipe;
        }

        public boolean canInsert(ItemStack stack) {
            return !isProcessingRecipe;
        }

        public int getMaxItemCount() {
            return 1;
        }

        protected boolean isProcessingRecipe;

    }

    private static class EssenceSlot extends Slot {

        public EssenceSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return matches(stack);
        }

        public static boolean matches(ItemStack stack) {
            return stack.getItem() instanceof EssenceItem;
        }

    }

    private static class NetherStarSlot extends Slot {

        public NetherStarSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return matches(stack);
        }

        public static boolean matches(ItemStack stack) {
            return stack.getItem() instanceof NetherStarItem;
        }

    }

}