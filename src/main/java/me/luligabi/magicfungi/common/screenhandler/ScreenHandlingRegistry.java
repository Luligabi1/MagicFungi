package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import me.luligabi.magicfungi.common.screenhandler.essence.EssenceExtractorScreenHandler;
import me.luligabi.magicfungi.common.screenhandler.glyph.GlyphCarvingScreenHandler;
import me.luligabi.magicfungi.common.screenhandler.misc.MorbusClockScreenHandler;
import me.luligabi.magicfungi.common.screenhandler.spell.SpellDiscoveryScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("deprecation")
public class ScreenHandlingRegistry {


    public static final ScreenHandlerType<GlyphCarvingScreenHandler> GLYPH_CARVING_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "glyph_carving_workbench"), GlyphCarvingScreenHandler::new);
    public static final ScreenHandlerType<SpellDiscoveryScreenHandler> SPELL_DISCOVERY_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "spell_discovery_workbench"), SpellDiscoveryScreenHandler::new);
    public static final ScreenHandlerType<EssenceExtractorScreenHandler> ESSENCE_EXTRACTOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "essence_extractor"), EssenceExtractorScreenHandler::new);
    public static final ScreenHandlerType<MagicCondenserScreenHandler> MAGIC_CONDENSER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "magic_condenser"), MagicCondenserScreenHandler::new);


    public static final ExtendedScreenHandlerType<MorbusClockScreenHandler> MORBUS_CLOCK_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MagicFungi.MOD_ID, "morbus_clock"), new ExtendedScreenHandlerType<>(MorbusClockScreenHandler::new));


    public static void init() {
        // NO-OP
    }

    private ScreenHandlingRegistry() {
        // NO-OP
    }
}