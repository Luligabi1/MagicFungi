package me.luligabi.magicfungi.client.compat.patchouli;

import me.luligabi.magicfungi.common.recipe.condenser.MagicCondenserRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class MagicCondensationProcessor implements IComponentProcessor {

    private MagicCondenserRecipe recipe;
    private String text;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void setup(IVariableProvider variables) {
        String recipeId = variables.get("recipe").asString();
        recipe = (MagicCondenserRecipe) MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(recipeId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe: " + recipeId));

        if(variables.has("text")) text = variables.get("text").asString();
    }

    @Override
    public IVariable process(@NotNull String key) {
        switch(key) {
            case "input" -> {
                return IVariable.from(recipe.getInput());
            }
            case "output" -> {
                return IVariable.from(recipe.getOutput());
            }
            /*case "impetusEssence" -> { // TODO: Implement showing essence/nether star fuel required (needs to be properly colored)
                return IVariable.from(new LiteralText(Integer.toString(recipe.getImpetusEssenceCost())).formatted(Formatting.RED));
            }
            case "clypeusEssence" -> {
                return IVariable.from(new LiteralText(Integer.toString(recipe.getClypeusEssenceCost())).formatted(Formatting.AQUA));
            }
            case "utilisEssence" -> {
                return IVariable.from(new LiteralText(Integer.toString(recipe.getUtilisEssenceCost())).formatted(Formatting.LIGHT_PURPLE));
            }
            case "vivificaEssence" -> {
                return IVariable.from(new LiteralText(Integer.toString(recipe.getVivificaEssenceCost())).formatted(Formatting.GREEN));
            }
            case "morbusEssence" -> {
                return IVariable.from(new LiteralText(Integer.toString(recipe.getMorbusEssenceCost())).formatted(Formatting.GRAY));
            }
            case "netherStarFuel" -> {
                return IVariable.from(new LiteralText(Integer.toString(recipe.getNetherStarFuelCost())).formatted(Formatting.WHITE));
            }*/
            case "header" -> {
                return IVariable.from(recipe.getOutput().getName());
            }
            case "text" -> {
                return IVariable.from(Text.of(text));
            }
            default -> {
                return IVariable.empty();
            }
        }
    }

}