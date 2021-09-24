package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.glyph.morbus.CorrumpereGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.morbus.MaledictioGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.morbus.ParasitusGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.utilis.CadentisGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.utilis.PluviamGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.vivifica.PudicitiamGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.vivifica.SanctificareGlyphItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class GlyphRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "exponentia_glyph"), EXPONENTIA_GLYPH);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "pluviam_glyph"), PLUVIAM_GLYPH);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "cadentis_glyph"), new CadentisGlyphItem(BlockRegistry.CADENTIS_BLOCK, new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP)));


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "pudicitiam_glyph"), PUDICITIAM_GLYPH);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "sanctificare_glyph"), SANCTIFICARE_GLYPH);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "parasitus_glyph"), PARASITUS_GLYPH);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "corrumpere_glyph"), CORRUMPERE_GLYPH);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "maledictio_glyph"), MALEDICTIO_GLYPH);
    }

    // Incognita
    public static final Item EXPONENTIA_GLYPH = new ExponentiaGlyphItem(new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP));


    //Utilis
    public static final Item PLUVIAM_GLYPH = new PluviamGlyphItem(new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP));


    // Vivifica
    public static final Item PUDICITIAM_GLYPH = new PudicitiamGlyphItem(new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP));
    public static final Item SANCTIFICARE_GLYPH = new SanctificareGlyphItem(new FabricItemSettings().maxCount(8).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    // Morbus
    public static final Item PARASITUS_GLYPH = new ParasitusGlyphItem(new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP));
    public static final Item CORRUMPERE_GLYPH = new CorrumpereGlyphItem(new FabricItemSettings().maxCount(8).group(MagicFungi.ITEM_GROUP));
    public static final Item MALEDICTIO_GLYPH = new MaledictioGlyphItem(new FabricItemSettings().maxCount(8).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


}