package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.EnderChestSpell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "ender_chest_spell"), ENDER_CHEST_SPELL);
    }

    public static final EnderChestSpell ENDER_CHEST_SPELL = new EnderChestSpell(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));
}