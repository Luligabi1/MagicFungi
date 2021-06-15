package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.screenhandler.SpellDiscoveryScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandllingRegistry {

    public static void init() {
        SPELL_DISCOVERY_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BlockRegistry.SPELL_DISCOVERY_BENCH_IDENTIFIER, SpellDiscoveryScreenHandler::new);
    }

    public static ScreenHandlerType<SpellDiscoveryScreenHandler> SPELL_DISCOVERY_SCREEN_HANDLER;
}