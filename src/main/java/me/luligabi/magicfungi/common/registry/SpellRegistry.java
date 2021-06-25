package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.IgneiSpellItem;
import me.luligabi.magicfungi.common.item.spell.TractabileSpellItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "ignei_spell"), IGNEI_SPELL);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "tractabile_spell"), TRACTABILE_SPELL);
    }

    public static final IgneiSpellItem IGNEI_SPELL = new IgneiSpellItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));

    public static final TractabileSpellItem TRACTABILE_SPELL = new TractabileSpellItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));
}