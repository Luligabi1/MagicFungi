package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.luligabi.magicfungi.common.screenhandler.slots.GlyphCarvingResultSlot;
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
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class GlyphCarvingScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory> {

    private final CraftingInventory input;
    private final CraftingResultInventory result;
    public final PlayerInventory playerInventory;
    private final ScreenHandlerContext context;

    public GlyphCarvingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public GlyphCarvingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlingRegistry.GLYPH_CARVING_SCREEN_HANDLER, syncId);
        this.playerInventory = playerInventory;
        this.input = new CraftingInventory(this, 3, 3);
        this.result = new CraftingResultInventory();
        this.context = context;

        checkSize(this.input, 5);
        this.input.onOpen(playerInventory.player);

        this.addSlot(new Slot(this.input, 0, 62 + 18, 56 + -2 * 18)); // inputA
        this.addSlot(new Slot(this.input, 1, 62 + -1 * 18, 56)); // inputB
        this.addSlot(new Slot(this.input, 2, 62 + 3 * 18, 56)); // inputC
        this.addSlot(new Slot(this.input, 3, 62 + 18, 56 + 2 * 18)); // inputD

        this.addSlot(new GlyphCarvingResultSlot(playerInventory.player, this.input, this.result, 4, 62 + 18, 56)); // Output

        int m;
        int l;

        // Inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 123 + m * 18));
            }
        }
        // Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 181));
        }

    }

    protected static void updateResult(ScreenHandler handler, World world, PlayerEntity playerEntity, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<GlyphRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(GlyphRecipe.Type.INSTANCE, craftingInventory, world);
            if (optional.isPresent()) {
                GlyphRecipe glyphRecipe = optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, glyphRecipe)) {
                    itemStack = glyphRecipe.craft(craftingInventory);
                }
            }

            resultInventory.setStack(4, itemStack);
            handler.setPreviousTrackedSlot(4, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 4, itemStack));
        }
    }

    public void onContentChanged(Inventory inventory) {
        this.context.run((world, pos) -> updateResult(this, world, this.playerInventory.player, this.input, this.result));
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }

    @Override // Shift-Click interactions
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 4) {
                this.context.run((world, pos) -> itemStack2.getItem().onCraft(itemStack2, world, player));
                if (!this.insertItem(itemStack2, 10, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (index >= 5 && index < 42) {
                if (!this.insertItem(itemStack2, 0, 4, false)) {
                    if (index < 37) {
                        if (!this.insertItem(itemStack2, 37, 41, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(itemStack2, 5, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(itemStack2, 5, 41, false)) {
                return ItemStack.EMPTY;
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
            if (index == 4) {
                player.dropItem(itemStack2, false);
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) { return canUse(this.context, player, BlockRegistry.GLYPH_CARVING_BLOCK); }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder) { this.input.provideRecipeInputs(finder); }

    @Override
    public void clearCraftingSlots() {
        this.input.clear();
        this.result.clear();
    }

    @Override
    public boolean matches(Recipe<? super CraftingInventory> recipe) { return recipe.matches(this.input, this.playerInventory.player.world); }

    @Override
    public int getCraftingResultSlotIndex() { return 8; }

    @Override
    public int getCraftingWidth() { return Integer.MAX_VALUE; }

    @Override
    public int getCraftingHeight() { return Integer.MAX_VALUE; }

    @Override
    public int getCraftingSlotCount() { return 5; }

    @Override
    public RecipeBookCategory getCategory() { return null; }

    @Override
    public boolean canInsertIntoSlot(int index) { return index != this.getCraftingResultSlotIndex(); }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) { return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot); }
}