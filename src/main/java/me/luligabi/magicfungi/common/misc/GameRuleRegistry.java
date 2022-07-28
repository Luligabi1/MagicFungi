package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class GameRuleRegistry {


    public static final GameRules.Key<GameRules.BooleanRule> DO_MORBUS_SPREADING = register("doMorbusSpreading", GameRuleFactory.createBooleanRule(MagicFungi.CONFIG.doMorbusSpread));
    public static final GameRules.Key<GameRules.IntRule> MORBUS_SPREADING_DAY = register("morbusSpreadStartingDay", GameRuleFactory.createIntRule(MagicFungi.CONFIG.morbusSpreadStartingDay, 0));

    private static <T extends GameRules.Rule<T>> GameRules.Key<T> register(String name, GameRules.Type<T> type) {
        return net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry.register(name,
                new CustomGameRuleCategory(new Identifier(MagicFungi.MOD_ID,"gamerule_category"), Text.translatable("gamerule.magicfungi.category").formatted(Formatting.YELLOW, Formatting.BOLD)),
                type);
    }

    public static void init() {
        // NO-OP
    }

    private GameRuleRegistry() {
        // NO-OP
    }

}