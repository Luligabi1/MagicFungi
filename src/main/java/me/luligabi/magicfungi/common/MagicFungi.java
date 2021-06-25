package me.luligabi.magicfungi.common;

import me.luligabi.magicfungi.common.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();
        GlyphRegistry.init();
        SpellRegistry.init();

        ConfiguredFeatureRegistry.init();
        BiomeRegistry.init();

        RecipeRegistry.init();
        ScreenHandlingRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(BlockRegistry.VIVIFICA_MUSHROOM_BLOCK))
            .build();

}