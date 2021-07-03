package me.luligabi.magicfungi.common.screenhandler.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;

public class SpellCraftingInventory implements Inventory, RecipeInputProvider {

    public final DefaultedList<ItemStack> stacks = DefaultedList.ofSize(9, ItemStack.EMPTY);
    ScreenHandler screenHandler;

    public SpellCraftingInventory(ScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
    }
    
    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= this.size()) {
            return ItemStack.EMPTY;
        } else {
            return this.stacks.get(slot);
        }
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);

        if(!itemStack.isEmpty()) {
            this.screenHandler.onContentChanged(this);
        }
        return itemStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.stacks, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.stacks.set(slot, stack);
        this.screenHandler.onContentChanged(this);
    }

    @Override
    public void markDirty() { }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        this.stacks.forEach(finder::addInput);
    }

}