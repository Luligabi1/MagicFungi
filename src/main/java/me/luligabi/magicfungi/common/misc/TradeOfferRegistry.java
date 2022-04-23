package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class TradeOfferRegistry {

    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 2, factories -> factories.add(new TradeOffers.SellItemFactory(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, 3, 1, 8, 8)));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5, factories -> factories.add(new TradeOffers.SellItemFactory(ItemRegistry.VIVIFICA_ESSENCE, 6, 2, 3, 25)));

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ItemRegistry.FUNGI_FERTILIZER, 2, 2, 6, 8));
            factories.add(new TradeOffers.SellItemFactory(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, 4, 3, 5, 12));
            factories.add(new TradeOffers.SellItemFactory(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, 6, 1, 8, 32));
        });
    }

}