package me.luligabi.magicfungi.common.worldgen;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

// /setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/igloo_chest"}
public class LootTableRegistry {

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            String idString = id.toString();

            // Don't waste any time if the loot table isn't what we're looking for
            if (!idString.startsWith("minecraft:chests")) return;

            switch(idString) {
                case IGLOO -> supplier.withPool(CLYPEUS_ESSENCE);
                case END_CITY -> supplier.withPool(UTILIS_ESSENCE);
                case JUNGLE_TEMPLE -> supplier.withPool(VIVIFICA_ESSENCE);
                case BASTION -> supplier.withPool(MAGICAL_FUNGI_ALLOY_NUGGET);
            }

        });
    }


    private static LootPoolEntry createEntry(ItemConvertible item, int weight) {
        return ItemEntry.builder(item).weight(weight)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).build();
    }


    private static final LootPool CLYPEUS_ESSENCE = FabricLootPoolBuilder.builder()
            .withEntry(createEntry(ItemRegistry.CLYPEUS_ESSENCE, 2)).rolls(UniformLootNumberProvider.create(0F, 3.0F))
            .build();

    private static final LootPool UTILIS_ESSENCE = FabricLootPoolBuilder.builder()
            .withEntry(createEntry(ItemRegistry.UTILIS_ESSENCE, 1)).rolls(UniformLootNumberProvider.create(0.5F, 4.0F))
            .build();

    private static final LootPool VIVIFICA_ESSENCE = FabricLootPoolBuilder.builder()
            .withEntry(createEntry(ItemRegistry.VIVIFICA_ESSENCE, 2)).rolls(UniformLootNumberProvider.create(0.25F, 2.0F))
            .build();

    private static final LootPool MAGICAL_FUNGI_ALLOY_NUGGET = FabricLootPoolBuilder.builder()
            .withEntry(createEntry(ItemRegistry.MAGICAL_FUNGI_ALLOY_NUGGET, 1)).rolls(UniformLootNumberProvider.create(0F, 3.0F))
            .build();


    private static final String IGLOO = "minecraft:chests/igloo_chest";
    private static final String END_CITY = "minecraft:chests/end_city_treasure";
    private static final String JUNGLE_TEMPLE = "minecraft:chests/jungle_temple";
    private static final String BASTION = "minecraft:chests/bastion_treasure";
}