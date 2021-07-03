package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlingRegistry {

    public static void init() {
        GLYPH_CARVING_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BlockRegistry.GLYPH_CARVING_BENCH_IDENTIFIER, GlyphCarvingScreenHandler::new);

        SPELL_DISCOVERY_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BlockRegistry.SPELL_DISCOVERY_BENCH_IDENTIFIER, SpellDiscoveryScreenHandler::new);
    }

    public static ScreenHandlerType<GlyphCarvingScreenHandler> GLYPH_CARVING_SCREEN_HANDLER;

    public static ScreenHandlerType<SpellDiscoveryScreenHandler> SPELL_DISCOVERY_SCREEN_HANDLER;
}