package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class SpellDiscoveryScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory> {

    private final CraftingInventory input;
    private final CraftingResultInventory result;
    public final PlayerInventory playerInventory;
    private final ScreenHandlerContext context;

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public SpellDiscoveryScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, syncId);
        //checkSize(inventory, 9);
        this.playerInventory = playerInventory;
        this.input = new CraftingInventory(this, 3, 3);
        this.result = new CraftingResultInventory();
        this.context = context;

        //inventory.onOpen(playerInventory.player);

        // Crafting Slots
        this.addSlot(new Slot(this.input, 0, 62, 56 + -2 * 18)); // inputA
        this.addSlot(new Slot(this.input, 1, 62 + 2 * 18, 56 + -2 * 18)); // inputB
        this.addSlot(new Slot(this.input, 2, 62 + -1 * 18, 56 + -1 * 18)); // inputC
        this.addSlot(new Slot(this.input, 3, 62 + 3 * 18, 56 + -1 * 18)); // inputD
        this.addSlot(new Slot(this.input, 4, 62 + -1 * 18, 56 + 18)); // inputE
        this.addSlot(new Slot(this.input, 5, 62 + 3 * 18, 56 + 18)); // inputF
        this.addSlot(new Slot(this.input, 6, 62, 56 + 2 * 18)); // inputG
        this.addSlot(new Slot(this.input, 7, 62 + 2 * 18, 56 + 2 * 18)); // inputH

        this.addSlot(new CraftingResultSlot(playerInventory.player, this.input, this.result, 8, 62 + 18, 56)); // Output

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

    //TODO: Fix items getting added instead of subtracted
    protected static void updateResult(ScreenHandler handler, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<SpellRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(SpellRecipe.Type.INSTANCE, craftingInventory, world);
            if (optional.isPresent()) {
                SpellRecipe spellRecipe = optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, spellRecipe)) {
                    itemStack = spellRecipe.craft(craftingInventory);
                }
            }

            resultInventory.setStack(8, itemStack);
            handler.setPreviousTrackedSlot(8, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, 8, itemStack));
        }
    }

    public void onContentChanged(Inventory inventory) {
        this.context.run((world, pos) -> updateResult(this, world, this.playerInventory.player, this.input, this.result));
    }

    @Override // Shift + Player Inv Slot //TODO: Fix this working screen -> player but not the other way
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.input.size()) {
                if (!this.insertItem(originalStack, this.input.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 8, this.input.size(), false)) {
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
    public boolean canUse(PlayerEntity player) { return canUse(this.context, player, BlockRegistry.SPELL_DISCOVERY_BLOCK); }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder) {
        this.input.provideRecipeInputs(finder);
    }

    @Override
    public void clearCraftingSlots() {
        this.input.clear();
        this.result.clear();
    }

    @Override
    public boolean matches(Recipe<? super CraftingInventory> recipe) { return recipe.matches(this.input, this.playerInventory.player.world); }

    @Override
    public int getCraftingResultSlotIndex() {
        return 8;
    }

    @Override
    public int getCraftingWidth() {
        return 4;
    }

    @Override
    public int getCraftingHeight() {
        return 4;
    }

    @Override
    public int getCraftingSlotCount() {
        return 9;
    }

    @Override
    public RecipeBookCategory getCategory() {
        return null;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return index != this.getCraftingResultSlotIndex();
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) { return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot); }

}