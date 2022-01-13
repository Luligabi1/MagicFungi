package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class CompostableRegistry {

    public static void init() {
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, 0.65F);

        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.MORBUS_MUSHROOM_BLOCK, 0.85F);

        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.HOST_GRASS, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.HOST_TALL_GRASS, 0.5F);

        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.HOST_FERN, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.LARGE_HOST_FERN, 0.5F);
    }

}