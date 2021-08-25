package me.luligabi.magicfungi.common.item;

import com.oroarmor.multiitemlib.api.UniqueItemRegistry;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.tool.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_essence"), IMPETUS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_essence"), CLYPEUS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_essence"), UTILIS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_essence"), VIVIFICA_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_essence"), MORBUS_ESSENCE);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_sword"), IMPETUS_SWORD);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_shield"), CLYPEUS_SHIELD);
        UniqueItemRegistry.SHIELD.addItemToRegistry(CLYPEUS_SHIELD);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_pickaxe"), UTILIS_PICKAXE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_elixir"), VIVIFICA_ELIXIR);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_scythe"), MORBUS_SCYTHE);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "fungi_fertilizer"), FUNGI_FERTILIZER);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom_stew"), IMPETUS_MUSHROOM_STEW);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom_stew"), CLYPEUS_MUSHROOM_STEW);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom_stew"), UTILIS_MUSHROOM_STEW);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom_stew"), VIVIFICA_MUSHROOM_STEW);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom_stew"), MORBUS_MUSHROOM_STEW);


    }

    public static void initGuideBook() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "guide_book"), GUIDE_BOOK);
    }

    public static final Item GUIDE_BOOK = new GuideBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    public static final Item IMPETUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));

    public static final Item CLYPEUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));

    public static final Item UTILIS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));

    public static final Item VIVIFICA_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));

    public static final Item MORBUS_ESSENCE = new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


    public static final Item IMPETUS_SWORD = new ImpetusSwordItem(ToolMaterials.IMPETUS, 3, -2.4F, new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));

    public static final Item CLYPEUS_SHIELD = new ClypeusShieldItem(new FabricItemSettings().rarity(Rarity.EPIC).maxDamage(3072).group(MagicFungi.ITEM_GROUP)); //TODO: Fix Clypeus Shield

    public static final Item UTILIS_PICKAXE = new UtilisPickaxeItem(ToolMaterials.UTILIS, 1, -2.8F, new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));

    public static final Item VIVIFICA_ELIXIR = new VivificaElixirItem(new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(9).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 25*20, 2), 1.0F).alwaysEdible().build()));

    public static final Item MORBUS_SCYTHE = new MorbusScytheItem(ToolMaterials.MORBUS, 2, -1.2F, new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP));


    public static final Item FUNGI_FERTILIZER = new FungiFertilizerItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));

    public static final Item IMPETUS_MUSHROOM_STEW = new MushroomStewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10*20, 1), 1.0F).build()));

    public static final Item CLYPEUS_MUSHROOM_STEW = new MushroomStewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 15*20, 1), 1.0F).build()));

    public static final Item UTILIS_MUSHROOM_STEW = new MushroomStewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 12*20, 1), 1.0F).build()));

    public static final Item VIVIFICA_MUSHROOM_STEW = new MushroomStewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6*20, 1), 1.0F).build()));

    public static final Item MORBUS_MUSHROOM_STEW = new MushroomStewItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP).maxCount(1).food(
            (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 6*20, 1), 1.0F).build()));

}