package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.TestSpell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {


    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "test_spell"), TEST_SPELL);
    }

    public static final TestSpell TEST_SPELL = new TestSpell(new FabricItemSettings().group(MagicFungi.NATURAL));
}