package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.clypeus.CadereSpellItem;
import me.luligabi.magicfungi.common.item.spell.clypeus.GlaciesSpellItem;
import me.luligabi.magicfungi.common.item.spell.impetus.IgneiSpellItem;
import me.luligabi.magicfungi.common.item.spell.impetus.ScintillamSpellItem;
import me.luligabi.magicfungi.common.item.spell.utilis.CibusSpellItem;
import me.luligabi.magicfungi.common.item.spell.utilis.TractabileSpellItem;
import me.luligabi.magicfungi.common.item.spell.vivifica.FertilisSpellItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class SpellRegistry { // TODO: Add Morbus Corruption system to all Spells

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "ignei_spell"), IGNEI_SPELL);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "scintillam_spell"), SCINTILLAM_SPELL);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "cadere_spell"), CADERE_SPELL);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "glacies_spell"), GLACIES_SPELL);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "tractabile_spell"), TRACTABILE_SPELL);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "cibus_spell"), CIBUS_SPELL);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "fertilis_spell"), FERTILIS_SPELL);
    }

    // Impetus
    public static final Item IGNEI_SPELL = new IgneiSpellItem(new FabricItemSettings().maxCount(1).group(MagicFungi.ITEM_GROUP));
    public static final Item SCINTILLAM_SPELL = new ScintillamSpellItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    // Clypeus
    public static final Item CADERE_SPELL = new CadereSpellItem(new FabricItemSettings().maxCount(1).group(MagicFungi.ITEM_GROUP));
    public static final Item GLACIES_SPELL = new GlaciesSpellItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    // Utilis
    public static final Item TRACTABILE_SPELL = new TractabileSpellItem(new FabricItemSettings().maxCount(1).group(MagicFungi.ITEM_GROUP));
    public static final Item CIBUS_SPELL = new CibusSpellItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    // Vivifica
    public static final Item FERTILIS_SPELL = new FertilisSpellItem(new FabricItemSettings().maxCount(1).group(MagicFungi.ITEM_GROUP));

}