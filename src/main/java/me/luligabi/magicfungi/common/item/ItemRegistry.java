package me.luligabi.magicfungi.common.item;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.relic.armor.MagicalFungiArmorItem;
import me.luligabi.magicfungi.common.item.relic.armor.MagicalFungiArmorMaterial;
import me.luligabi.magicfungi.common.item.misc.*;
import me.luligabi.magicfungi.common.item.relic.ToolMaterials;
import me.luligabi.magicfungi.common.item.relic.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "guide_book"), GUIDE_BOOK);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_essence"), IMPETUS_ESSENCE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_essence"), CLYPEUS_ESSENCE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_essence"), UTILIS_ESSENCE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_essence"), VIVIFICA_ESSENCE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_essence"), MORBUS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_sword"), IMPETUS_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_shield"), CLYPEUS_SHIELD);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_pickaxe"), UTILIS_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_elixir"), VIVIFICA_ELIXIR);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_scythe"), MORBUS_SCYTHE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_helmet"), MAGICAL_FUNGI_HELMET);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_chestplate"), MAGICAL_FUNGI_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_leggings"), MAGICAL_FUNGI_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_boots"), MAGICAL_FUNGI_BOOTS);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_alloy"), MAGICAL_FUNGI_ALLOY);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_nugget"), MAGICAL_FUNGI_ALLOY_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_leather"), MORBUS_LEATHER);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "fungi_fertilizer"), FUNGI_FERTILIZER);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "research_log"), RESEARCH_LOG);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_clock"), MORBUS_CLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "heart_of_vivifica"), HEART_OF_VIVIFICA);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "heart_of_morbus"), HEART_OF_MORBUS);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom_stew"), IMPETUS_MUSHROOM_STEW);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom_stew"), CLYPEUS_MUSHROOM_STEW);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom_stew"), UTILIS_MUSHROOM_STEW);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom_stew"), VIVIFICA_MUSHROOM_STEW);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom_stew"), MORBUS_MUSHROOM_STEW);
    }

    public static final Item GUIDE_BOOK = new AbstractBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1).group(MagicFungi.ITEM_GROUP), new Identifier(MagicFungi.MOD_ID, MagicFungi.MOD_ID));


    public static final Item IMPETUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    public static final Item CLYPEUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    public static final Item UTILIS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    public static final Item VIVIFICA_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    public static final Item MORBUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    public static final Item IMPETUS_SWORD = new ImpetusSwordItem(ToolMaterials.IMPETUS, 3, -2.4F, new FabricItemSettings().rarity(Rarity.RARE).group(MagicFungi.ITEM_GROUP));
    public static final Item CLYPEUS_SHIELD = new ClypeusShieldItem(new FabricItemSettings().rarity(Rarity.RARE).maxDamage(3072).group(MagicFungi.ITEM_GROUP), 10, ToolMaterials.CLYPEUS);
    public static final Item UTILIS_PICKAXE = new UtilisPickaxeItem(ToolMaterials.UTILIS, 1, -2.8F, new FabricItemSettings().rarity(Rarity.RARE).group(MagicFungi.ITEM_GROUP));
    public static final Item VIVIFICA_ELIXIR = new VivificaElixirItem(new FabricItemSettings().rarity(Rarity.RARE).group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(9).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 25*20, 2), 1.0F).alwaysEdible().build()));
    public static final Item MORBUS_SCYTHE = new MorbusScytheItem(ToolMaterials.MORBUS, 2, -1.2F, new FabricItemSettings().rarity(Rarity.RARE).group(MagicFungi.ITEM_GROUP));


    public static final ArmorMaterial MAGICAL_FUNGI_ARMOR_MATERIAL = new MagicalFungiArmorMaterial();
    public static final Item MAGICAL_FUNGI_HELMET = new MagicalFungiArmorItem(ItemRegistry.MAGICAL_FUNGI_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));
    public static final Item MAGICAL_FUNGI_CHESTPLATE = new MagicalFungiArmorItem(ItemRegistry.MAGICAL_FUNGI_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));
    public static final Item MAGICAL_FUNGI_LEGGINGS = new MagicalFungiArmorItem(ItemRegistry.MAGICAL_FUNGI_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));
    public static final Item MAGICAL_FUNGI_BOOTS = new MagicalFungiArmorItem(ItemRegistry.MAGICAL_FUNGI_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().fireproof().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));


    public static final Item MAGICAL_FUNGI_ALLOY = new MagicalFungiAlloyItem(new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));
    public static final Item MAGICAL_FUNGI_ALLOY_NUGGET = new MagicalFungiAlloyItem(new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));
    public static final Item MORBUS_LEATHER = new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    public static final Item FUNGI_FERTILIZER = new FungiFertilizerItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));


    public static final Item RESEARCH_LOG = new AbstractBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1).group(MagicFungi.ITEM_GROUP), new Identifier(MagicFungi.MOD_ID, "research_log"));
    public static final Item MORBUS_CLOCK = new MorbusClockItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1).group(MagicFungi.ITEM_GROUP));
    public static final Item HEART_OF_VIVIFICA = new VivificaHeartItem(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).group(MagicFungi.ITEM_GROUP));
    public static final Item HEART_OF_MORBUS = new MorbusHeartItem(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).group(MagicFungi.ITEM_GROUP));


    public static final Item IMPETUS_MUSHROOM_STEW = new StewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10*20, 1), 1.0F).build()));

    public static final Item CLYPEUS_MUSHROOM_STEW = new StewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 15*20, 1), 1.0F).build()));

    public static final Item UTILIS_MUSHROOM_STEW = new StewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 12*20, 1), 1.0F).build()));

    public static final Item VIVIFICA_MUSHROOM_STEW = new StewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6*20, 1), 1.0F).build()));

    public static final Item MORBUS_MUSHROOM_STEW = new StewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 6*20, 1), 1.0F).build()));

}