package me.luligabi.magicfungi.client.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.ModConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ConfigScreenEntrypoint implements ModMenuApi {

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen parent) {
        ModConfig config = MagicFungi.CONFIG;

        /*
         * GLYPHS
         */
        // Morbus Glyphs
        Option<Integer> parasitusGlyphEffectTime = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.parasitusGlyphEffectTime"))
                .tooltip(Text.translatable("configOption.magicfungi.parasitusGlyphEffectTime.tooltip"))
                .binding(
                        7,
                        () -> config.parasitusGlyphEffectTime,
                        newValue -> config.parasitusGlyphEffectTime = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 1, 20, 1))
                .build();

        /*
         * SPELLS
         */
        // Impetus Spells
        Option<Integer> igneiSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.igneiSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.igneiSpellCooldown.tooltip"))
                .binding(
                        12,
                        () -> config.igneiSpellCooldown,
                        newValue -> config.igneiSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        Option<Integer> scintillamSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.scintillamSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.scintillamSpellCooldown.tooltip"))
                .binding(
                        22,
                        () -> config.igneiSpellCooldown,
                        newValue -> config.igneiSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Clypeus Spells
        Option<Integer> cadereSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.cadereSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.cadereSpellCooldown.tooltip"))
                .binding(
                        35,
                        () -> config.cadereSpellCooldown,
                        newValue -> config.cadereSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        Option<Integer> cadereSpellEffectTime = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.cadereSpellEffectTime"))
                .tooltip(Text.translatable("configOption.magicfungi.cadereSpellEffectTime.tooltip"))
                .binding(
                        12,
                        () -> config.cadereSpellEffectTime,
                        newValue -> config.cadereSpellEffectTime = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();


        Option<Integer> glaciesSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.glaciesSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.glaciesSpellCooldown.tooltip"))
                .binding(
                        40,
                        () -> config.glaciesSpellCooldown,
                        newValue -> config.glaciesSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        Option<Integer> glaciesSpellEffectTime = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.glaciesSpellEffectTime"))
                .tooltip(Text.translatable("configOption.magicfungi.glaciesSpellEffectTime.tooltip"))
                .binding(
                        10,
                        () -> config.glaciesSpellEffectTime,
                        newValue -> config.glaciesSpellEffectTime = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Utilis Spells
        Option<Integer> tractabileSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.tractabileSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.tractabileSpellCooldown.tooltip"))
                .binding(
                        3,
                        () -> config.tractabileSpellCooldown,
                        newValue -> config.tractabileSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();


        Option<Integer> cibusSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.cibusSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.cibusSpellCooldown.tooltip"))
                .binding(
                        160,
                        () -> config.cibusSpellCooldown,
                        newValue -> config.cibusSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 256, 2))
                .build();

        Option<Integer> cibusSpellHungerModifier = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.cibusSpellHungerModifier"))
                .tooltip(Text.translatable("configOption.magicfungi.cibusSpellHungerModifier.tooltip"))
                .binding(
                        9,
                        () -> config.cibusSpellHungerModifier,
                        newValue -> config.cibusSpellHungerModifier = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        Option<Float> cibusSpellSaturationModifier = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.cibusSpellSaturationModifier"))
                .tooltip(Text.translatable("configOption.magicfungi.cibusSpellSaturationModifier.tooltip"))
                .binding(
                        0.8F,
                        () -> config.cibusSpellSaturationModifier,
                        newValue -> config.cibusSpellSaturationModifier = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.1F, 1F, 0.1F))
                .build();

        // Vivifica Spells
        Option<Integer> fertilisSpellCooldown = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.fertilisSpellCooldown"))
                .tooltip(Text.translatable("configOption.magicfungi.fertilisSpellCooldown.tooltip"))
                .binding(
                        170,
                        () -> config.fertilisSpellCooldown,
                        newValue -> config.fertilisSpellCooldown = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 256, 2))
                .build();

        /*
         * FUNGIC RELICS
         */
        Option<Integer> relicDurability = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.relicDurability"))
                .tooltip(Text.translatable("configOption.magicfungi.relicDurability.tooltip"))
                .binding(
                        3046,
                        () -> config.relicDurability,
                        newValue -> config.relicDurability = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 5120, 64))
                .build();

        Option<Float> relicMiningSpeed = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.relicMiningSpeed"))
                .tooltip(Text.translatable("configOption.magicfungi.relicMiningSpeed.tooltip"))
                .binding(
                        10.5F,
                        () -> config.relicMiningSpeed,
                        newValue -> config.relicMiningSpeed = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.5F, 20F, 0.5F))
                .build();

        // Impetus Sword
        Option<Float> impetusSwordAttackDamage = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.impetusSwordAttackDamage"))
                .tooltip(Text.translatable("configOption.magicfungi.impetusSwordAttackDamage.tooltip"))
                .binding(
                        10.0F,
                        () -> config.impetusSwordAttackDamage,
                        newValue -> config.impetusSwordAttackDamage = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.5F, 20F, 0.5F))
                .build();

        Option<Integer> impetusSwordMaxCharge = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.impetusSwordMaxCharge"))
                .tooltip(Text.translatable("configOption.magicfungi.impetusSwordMaxCharge.tooltip"))
                .binding(
                        96,
                        () -> config.impetusSwordMaxCharge,
                        newValue -> config.impetusSwordMaxCharge = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Clypeus Shield
        Option<Integer> clypeusShieldMaxCharge = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.clypeusShieldMaxCharge"))
                .tooltip(Text.translatable("configOption.magicfungi.clypeusShieldMaxCharge.tooltip"))
                .binding(
                        72,
                        () -> config.clypeusShieldMaxCharge,
                        newValue -> config.clypeusShieldMaxCharge = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Vivifica Elixir
        Option<Integer> vivificaElixirMaxCharge = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.vivificaElixirMaxCharge"))
                .tooltip(Text.translatable("configOption.magicfungi.vivificaElixirMaxCharge.tooltip"))
                .binding(
                        72,
                        () -> config.vivificaElixirMaxCharge,
                        newValue -> config.vivificaElixirMaxCharge = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Morbus Scythe
        Option<Float> morbusScytheAttackDamage = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.morbusScytheAttackDamage"))
                .tooltip(Text.translatable("configOption.magicfungi.morbusScytheAttackDamage.tooltip"))
                .binding(
                        8.5F,
                        () -> config.morbusScytheAttackDamage,
                        newValue -> config.morbusScytheAttackDamage = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.5F, 20F, 0.5F))
                .build();

        Option<Integer> morbusScytheEffectDuration = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.morbusScytheEffectDuration"))
                .tooltip(Text.translatable("configOption.magicfungi.morbusScytheEffectDuration.tooltip"))
                .binding(
                        8,
                        () -> config.morbusScytheEffectDuration,
                        newValue -> config.morbusScytheEffectDuration = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 20, 2))
                .build();

        Option<Integer> morbusScytheEffectStrength = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.morbusScytheEffectStrength"))
                .tooltip(Text.translatable("configOption.magicfungi.morbusScytheEffectStrength.tooltip"))
                .binding(
                        0,
                        () -> config.morbusScytheEffectStrength,
                        newValue -> config.morbusScytheEffectStrength = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 0, 4, 2))
                .build();

        Option<Integer> morbusScytheMaxCharge = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.morbusScytheMaxCharge"))
                .tooltip(Text.translatable("configOption.magicfungi.morbusScytheMaxCharge.tooltip"))
                .binding(
                        100,
                        () -> config.morbusScytheMaxCharge,
                        newValue -> config.morbusScytheMaxCharge = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 128, 2))
                .build();

        // Magical Fungi Armor
        Option<Integer> magicalFungiArmorDurabilityMultiplier = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.magicalFungiArmorDurabilityMultiplier"))
                .tooltip(Text.translatable("configOption.magicfungi.magicalFungiArmorDurabilityMultiplier.tooltip"))
                .binding(
                        45,
                        () -> config.magicalFungiArmorDurabilityMultiplier,
                        newValue -> config.magicalFungiArmorDurabilityMultiplier = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 5, 100, 5))
                .build();

        Option<Float> magicalFungiArmorToughness = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.magicalFungiArmorToughness"))
                .tooltip(Text.translatable("configOption.magicfungi.magicalFungiArmorToughness.tooltip"))
                .binding(
                        3.5F,
                        () -> config.magicalFungiArmorToughness,
                        newValue -> config.magicalFungiArmorToughness = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.5F, 10F, 0.5F))
                .build();

        Option<Float> magicalFungiKnockBackResistance = Option.createBuilder(Float.class)
                .name(Text.translatable("configOption.magicfungi.magicalFungiKnockBackResistance"))
                .tooltip(Text.translatable("configOption.magicfungi.magicalFungiKnockBackResistance.tooltip"))
                .binding(
                        0.15F,
                        () -> config.magicalFungiKnockBackResistance,
                        newValue -> config.magicalFungiKnockBackResistance = newValue
                )
                .controller((floatOption) -> new FloatSliderController(floatOption, 0.0F, 0.2F, 0.05F))
                .build();

        /*
         * WORLDGEN
         */
        // Impetus Mushroom
        Option<Integer> impetusRegularSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.impetusRegularSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.impetusRegularSpawnRate.tooltip"))
                .binding(
                        12,
                        () -> config.impetusRegularSpawnRate,
                        newValue -> config.impetusRegularSpawnRate = newValue
                )
                .available(config.canGenerateImpetusMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Integer> impetusBiomeEnhancedSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.impetusBiomeEnhancedSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.impetusBiomeEnhancedSpawnRate.tooltip"))
                .binding(
                        28,
                        () -> config.impetusBiomeEnhancedSpawnRate,
                        newValue -> config.impetusBiomeEnhancedSpawnRate = newValue
                )
                .available(config.canGenerateImpetusMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Boolean> canGenerateImpetusMushrooms = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateImpetusMushrooms"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateImpetusMushrooms.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateImpetusMushrooms,
                        newValue -> {
                            config.canGenerateImpetusMushrooms = newValue;
                            impetusRegularSpawnRate.setAvailable(newValue);
                            impetusBiomeEnhancedSpawnRate.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        // Clypeus Mushroom
        Option<Integer> clypeusRegularSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.clypeusRegularSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.clypeusRegularSpawnRate.tooltip"))
                .binding(
                        12,
                        () -> config.clypeusRegularSpawnRate,
                        newValue -> config.clypeusRegularSpawnRate = newValue
                )
                .available(config.canGenerateClypeusMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Integer> clypeusBiomeEnhancedSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.clypeusBiomeEnhancedSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.clypeusBiomeEnhancedSpawnRate.tooltip"))
                .binding(
                        28,
                        () -> config.clypeusBiomeEnhancedSpawnRate,
                        newValue -> config.clypeusBiomeEnhancedSpawnRate = newValue
                )
                .available(config.canGenerateClypeusMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Boolean> canGenerateClypeusMushrooms = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateClypeusMushrooms"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateClypeusMushrooms.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateClypeusMushrooms,
                        newValue -> {
                            config.canGenerateClypeusMushrooms = newValue;
                            clypeusRegularSpawnRate.setAvailable(newValue);
                            clypeusBiomeEnhancedSpawnRate.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        // Utilis Mushroom
        Option<Integer> utilisRegularSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.utilisRegularSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.utilisRegularSpawnRate.tooltip"))
                .binding(
                        12,
                        () -> config.utilisRegularSpawnRate,
                        newValue -> config.utilisRegularSpawnRate = newValue
                )
                .available(config.canGenerateUtilisMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Integer> utilisBiomeEnhancedSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.utilisBiomeEnhancedSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.utilisBiomeEnhancedSpawnRate.tooltip"))
                .binding(
                        28,
                        () -> config.utilisBiomeEnhancedSpawnRate,
                        newValue -> config.utilisBiomeEnhancedSpawnRate = newValue
                )
                .available(config.canGenerateUtilisMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Boolean> canGenerateUtilisMushrooms = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateUtilisMushrooms"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateUtilisMushrooms.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateUtilisMushrooms,
                        newValue -> {
                            config.canGenerateUtilisMushrooms = newValue;
                            utilisRegularSpawnRate.setAvailable(newValue);
                            utilisBiomeEnhancedSpawnRate.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        // Vivifica Mushroom
        Option<Integer> vivificaRegularSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.vivificaRegularSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.vivificaRegularSpawnRate.tooltip"))
                .binding(
                        12,
                        () -> config.vivificaRegularSpawnRate,
                        newValue -> config.vivificaRegularSpawnRate = newValue
                )
                .available(config.canGenerateVivificaMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Integer> vivificaBiomeEnhancedSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.vivificaBiomeEnhancedSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.vivificaBiomeEnhancedSpawnRate.tooltip"))
                .binding(
                        28,
                        () -> config.vivificaBiomeEnhancedSpawnRate,
                        newValue -> config.vivificaBiomeEnhancedSpawnRate = newValue
                )
                .available(config.canGenerateVivificaMushrooms)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Boolean> canGenerateVivificaMushrooms = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateVivificaMushrooms"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateVivificaMushrooms.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateVivificaMushrooms,
                        newValue -> {
                            config.canGenerateVivificaMushrooms = newValue;
                            vivificaRegularSpawnRate.setAvailable(newValue);
                            vivificaBiomeEnhancedSpawnRate.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        // Morbus Mushroom
        Option<Boolean> canGenerateMorbusMushrooms = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateMorbusMushrooms"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateMorbusMushrooms.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateMorbusMushrooms,
                        newValue -> config.canGenerateMorbusMushrooms = newValue
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        // Host Biome
        Option<Integer> hostBiomeSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.hostBiomeSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.hostBiomeSpawnRate.tooltip"))
                .binding(
                        7,
                        () -> config.hostBiomeSpawnRate,
                        newValue -> config.hostBiomeSpawnRate = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Integer> hostBiomeWitherRoseSpawnRate = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.hostBiomeWitherRoseSpawnRate"))
                .tooltip(Text.translatable("configOption.magicfungi.hostBiomeWitherRoseSpawnRate.tooltip"))
                .binding(
                        12,
                        () -> config.hostBiomeWitherRoseSpawnRate,
                        newValue -> config.hostBiomeWitherRoseSpawnRate = newValue
                )
                .available(config.canGenerateWitherRoseHostBiome)
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 32, 2))
                .build();

        Option<Boolean> canGenerateWitherRoseHostBiome = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canGenerateWitherRoseHostBiome"))
                .tooltip(Text.translatable("configOption.magicfungi.canGenerateWitherRoseHostBiome.tooltip"))
                .binding(
                        true,
                        () -> config.canGenerateWitherRoseHostBiome,
                        newValue -> {
                            config.canGenerateWitherRoseHostBiome = newValue;
                            hostBiomeWitherRoseSpawnRate.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        /*
         * MORBUS SPREADING
         */
        Option<Integer> morbusSpreadStartingDay = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.magicfungi.morbusSpreadStartingDay"))
                .tooltip(Text.translatable("configOption.magicfungi.morbusSpreadStartingDay.tooltip"))
                .binding(
                        100,
                        () -> config.morbusSpreadStartingDay,
                        newValue -> config.morbusSpreadStartingDay = newValue
                )
                .available(config.doMorbusSpread)
                .controller((intOption) -> new IntegerSliderController(intOption, 1, 999, 1))
                .build();

        Option<Boolean> doMorbusSpread = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.doMorbusSpread"))
                .tooltip(Text.translatable("configOption.magicfungi.doMorbusSpread.tooltip"))
                .binding(
                        false,
                        () -> config.doMorbusSpread,
                        newValue -> {
                            config.doMorbusSpread = newValue;
                            morbusSpreadStartingDay.setAvailable(newValue);
                        }
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        Option<Boolean> canUseMorbusCorruptionItems = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.canUseMorbusCorruptionItems"))
                .tooltip(Text.translatable("configOption.magicfungi.canUseMorbusCorruptionItems.tooltip"))
                .binding(
                        false,
                        () -> config.canUseMorbusCorruptionItems,
                        newValue -> config.canUseMorbusCorruptionItems = newValue
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        /*
         * MISC
         */
        Option<Boolean> addModContentToChests = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.addModContentToChests"))
                .tooltip(Text.translatable("configOption.magicfungi.addModContentToChests.tooltip"))
                .binding(
                        true,
                        () -> config.addModContentToChests,
                        newValue -> config.addModContentToChests = newValue
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        Option<Boolean> addModContentToTrades = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.magicfungi.addModContentToTrades"))
                .tooltip(Text.translatable("configOption.magicfungi.addModContentToTrades.tooltip"))
                .binding(
                        true,
                        () -> config.addModContentToTrades,
                        newValue -> config.addModContentToTrades = newValue
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("itemGroup.magicfungi.item_group"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.glyphs"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.morbus_glyphs").formatted(Formatting.GRAY))
                                .options(List.of(
                                        parasitusGlyphEffectTime
                                ))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.spells"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.impetus_spells").formatted(Formatting.RED))
                                .options(List.of(
                                        igneiSpellCooldown,
                                        scintillamSpellCooldown
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.clypeus_spells").formatted(Formatting.AQUA))
                                .options(List.of(
                                        cadereSpellCooldown,
                                        cadereSpellEffectTime,

                                        glaciesSpellCooldown,
                                        glaciesSpellEffectTime
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.utilis_spells").formatted(Formatting.LIGHT_PURPLE))
                                .options(List.of(
                                        tractabileSpellCooldown,

                                        cibusSpellCooldown,
                                        cibusSpellHungerModifier,
                                        cibusSpellSaturationModifier
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.vivifica_spells").formatted(Formatting.GREEN))
                                .options(List.of(
                                        fertilisSpellCooldown
                                ))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.fungic_relics"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.fungic_relics"))
                                .options(List.of(
                                        relicDurability,
                                        relicMiningSpeed
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("item.magicfungi.impetus_sword").formatted(Formatting.RED))
                                .options(List.of(
                                        impetusSwordAttackDamage,
                                        impetusSwordMaxCharge
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("item.magicfungi.clypeus_shield").formatted(Formatting.AQUA))
                                .options(List.of(
                                        clypeusShieldMaxCharge
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("item.magicfungi.vivifica_elixir").formatted(Formatting.GREEN))
                                .options(List.of(
                                        vivificaElixirMaxCharge
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("item.magicfungi.morbus_scythe").formatted(Formatting.GRAY))
                                .options(List.of(
                                        morbusScytheAttackDamage,
                                        morbusScytheEffectDuration,
                                        morbusScytheEffectStrength,
                                        morbusScytheMaxCharge
                                ))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.magical_fungi_armor").formatted(Formatting.LIGHT_PURPLE))
                                .options(List.of(
                                        magicalFungiArmorDurabilityMultiplier,
                                        magicalFungiArmorToughness,
                                        magicalFungiKnockBackResistance
                                ))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.worldgen"))
                        .group(
                                createMushroomWorldgenCategory("impetus_mushroom",
                                        canGenerateImpetusMushrooms,
                                        impetusRegularSpawnRate,
                                        impetusBiomeEnhancedSpawnRate,
                                        Formatting.RED
                                )
                        )
                        .group(
                                createMushroomWorldgenCategory("clypeus_mushroom",
                                        canGenerateClypeusMushrooms,
                                        clypeusRegularSpawnRate,
                                        clypeusBiomeEnhancedSpawnRate,
                                        Formatting.AQUA
                                )
                        )
                        .group(
                                createMushroomWorldgenCategory("utilis_mushroom",
                                        canGenerateUtilisMushrooms,
                                        utilisRegularSpawnRate,
                                        utilisBiomeEnhancedSpawnRate,
                                        Formatting.LIGHT_PURPLE
                                )
                        )
                        .group(
                                createMushroomWorldgenCategory("vivifica_mushroom",
                                        canGenerateVivificaMushrooms,
                                        vivificaRegularSpawnRate,
                                        vivificaBiomeEnhancedSpawnRate,
                                        Formatting.GREEN
                                )
                        )
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("block.magicfungi.morbus_mushroom").formatted(Formatting.GRAY))

                                .option(canGenerateMorbusMushrooms)
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("biome.magicfungi.host_biome").formatted(Formatting.GRAY))
                                .options(List.of(
                                        hostBiomeSpawnRate,
                                        canGenerateWitherRoseHostBiome,
                                        hostBiomeWitherRoseSpawnRate
                                ))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.morbus_spreading"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.morbus_spreading").formatted(Formatting.GRAY))
                                .options(List.of(
                                        doMorbusSpread,
                                        morbusSpreadStartingDay,
                                        canUseMorbusCorruptionItems
                                ))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configCategory.magicfungi.misc"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("configCategory.magicfungi.misc"))
                                .options(List.of(
                                        addModContentToChests,
                                        addModContentToTrades
                                ))
                                .build())
                        .build())
                .save(() -> MagicFungi.saveConfig(config))
                .build()
                .generateScreen(parent);
    }

    private OptionGroup createMushroomWorldgenCategory(String mushroomId, Option<Boolean> canGenerate, Option<Integer> regularSpawnRate, Option<Integer> biomeEnhancedSpawnRate, Formatting color) {
        return OptionGroup.createBuilder()
                .name(Text.translatable("block.magicfungi." + mushroomId).formatted(color))
                .options(List.of(
                        canGenerate,
                        regularSpawnRate,
                        biomeEnhancedSpawnRate
                ))
                .build();
    }

}