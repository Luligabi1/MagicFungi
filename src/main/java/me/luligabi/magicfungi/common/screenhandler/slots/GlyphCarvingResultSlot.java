package me.luligabi.magicfungi.common.screenhandler.slots;

import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.util.collection.DefaultedList;

public class GlyphCarvingResultSlot extends CraftingResultSlot {

    private final CraftingInventory input;
    private final PlayerEntity player;

    public GlyphCarvingResultSlot(PlayerEntity player, CraftingInventory input, Inventory inventory, int index, int x, int y) {
        super(player, input, inventory, index, x, y);
        this.player = player;
        this.input = input;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);
        DefaultedList<ItemStack> defaultedList = player.world.getRecipeManager().getRemainingStacks(GlyphRecipe.Type.INSTANCE, this.input, player.world);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack itemStack = this.input.getStack(i);
            ItemStack itemStack2 = defaultedList.get(i);
            if (!itemStack.isEmpty()) {
                this.input.removeStack(i, 1);
                itemStack = this.input.getStack(i);
            }

            if (!itemStack2.isEmpty()) {
                if (itemStack.isEmpty()) {
                    this.input.setStack(i, itemStack2);
                } else if (ItemStack.areItemsEqualIgnoreDamage(itemStack, itemStack2) && ItemStack.areNbtEqual(itemStack, itemStack2)) {
                    itemStack2.increment(itemStack.getCount());
                    this.input.setStack(i, itemStack2);
                } else if (!this.player.getInventory().insertStack(itemStack2)) {
                    this.player.dropItem(itemStack2, false);
                }
            }
        }

    }
}
