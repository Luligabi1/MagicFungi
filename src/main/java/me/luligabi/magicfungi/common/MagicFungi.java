package me.luligabi.magicfungi.common;

import me.luligabi.magicfungi.common.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";
}