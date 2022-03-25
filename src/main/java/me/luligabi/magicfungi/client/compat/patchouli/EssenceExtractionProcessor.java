package me.luligabi.magicfungi.client.compat.patchouli;

import com.mojang.logging.LogUtils;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.ArrayList;
import java.util.List;

public class EssenceExtractionProcessor implements IComponentProcessor {

    private EssenceRecipe recipe;
    private String text;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void setup(IVariableProvider variables) {
        String recipeId = variables.get("recipe").asString();
        recipe = (EssenceRecipe) MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(recipeId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipe: " + recipeId));

        if (variables.has("text")) text = variables.get("text").asString();
    }

    @Override
    public IVariable process(@NotNull String key) {
        switch(key) {
            case "input" -> { return IVariable.from(recipe.getInput()); }
            case "catalyst" -> {
                List<ItemConvertible> catalystList = new ArrayList<>();
                Registry.ITEM.getEntryList(recipe.getCatalystType().getTag()).ifPresentOrElse(
                        registryEntries -> registryEntries.forEach(itemRegistryEntry -> catalystList.add(itemRegistryEntry.value())),
                        () -> LogUtils.getLogger().error("Catalyst tag is empty!"));

                return IVariable.from(Ingredient.ofItems(catalystList.toArray(new ItemConvertible[0])));
            }
            case "output" -> { return IVariable.from(recipe.getOutput()); }
            case "header" -> { return IVariable.from(recipe.getOutput().getName()); }
            case "text" -> { return IVariable.from(Text.of(text)); }
            default -> { return null; }
        }
    }

}