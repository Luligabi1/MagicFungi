package me.luligabi.magicfungi.client.compat.patchouli;

import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class SpellDiscoveryProcessor implements IComponentProcessor {

    private SpellRecipe recipe;
    private String text;

    @Override
    public void setup(IVariableProvider variables) {
        String recipeId = variables.get("recipe").asString();
        recipe = (SpellRecipe) MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(recipeId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipe: " + recipeId));

        if (variables.has("text")) text = variables.get("text").asString();
    }

    @Override
    public IVariable process(String key) {
        if (key.startsWith("input")) {
            int index = Integer.parseInt(key.substring(5)); // #index0 -> 0
            Ingredient ingredient = recipe.getInputs().get(index);
            ItemStack[] stacks = ingredient.getMatchingStacks();
            return IVariable.from(stacks);
        } else if (key.equals("output")) {
            return IVariable.from(new ItemStack[]{recipe.getOutput()});
        } else if (key.equals("header")) {
            return IVariable.from(recipe.getOutput().getName());
        } else if (key.equals("text")) {
            return IVariable.from(Text.of(text));
        } else {
            return IVariable.empty();
        }
    }

}