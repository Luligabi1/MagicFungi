package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class SpellDiscoveryScreenHandler extends ScreenHandler {

    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    private final ScreenHandlerContext context;

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9), ScreenHandlerContext.EMPTY);
    }

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, ScreenHandlerContext context) {
        super(ScreenHandlingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
        this.context = context;

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

        this.addSlot(new Slot(inventory, 8, 62 + 18, 56) {
            public boolean canInsert(ItemStack stack) { return false; }

            public void onTakeItem(PlayerEntity player, ItemStack stack) {

                for(int i = 0; i < 7; ++i) {
                    getSlot(i).getStack().decrement(1);
                }

                super.onTakeItem(player, stack);
            }
        });

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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.context.run((world, blockPos) -> updateResult(world));
    }

    private void updateResult(World world) {
        System.out.println("test 0");
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerInventory.player;
            ItemStack itemStack = ItemStack.EMPTY;
            System.out.println(inventory.isEmpty() + "- test 1");
            Optional<SpellRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(SpellRecipe.Type.INSTANCE, inventory, world);
            if (optional.isPresent()) {
                System.out.println("test 2");
                SpellRecipe craftingRecipe = optional.get();
                itemStack = craftingRecipe.craft(inventory);
            }

            inventory.setStack(8, itemStack);
            this.setPreviousTrackedSlot(8, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(syncId, 8, itemStack));
        }
    }

    /*protected static void updateResult(ScreenHandler screenHandler, World world, PlayerEntity player, SpellCraftingInventory inventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            System.out.println(inventory.isEmpty() + "- test 1");
            Optional<SpellRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(SpellRecipe.Type.INSTANCE, inventory, world);
            if (optional.isPresent()) {
                System.out.println("test 2");
                SpellRecipe craftingRecipe = optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
                    itemStack = craftingRecipe.craft(inventory);
                }
            }

            resultInventory.setStack(8, itemStack);
            screenHandler.setPreviousTrackedSlot(8, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(screenHandler.syncId, 8, itemStack));
        }
    }*/

}