package me.luligabi.magicfungi.common;

import me.luligabi.magicfungi.common.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";


    public static final ItemGroup NATURAL = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "natural"))
            .icon(() -> new ItemStack(Items.BOWL))
            .build();

}