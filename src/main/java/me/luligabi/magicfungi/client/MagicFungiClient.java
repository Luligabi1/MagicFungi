package me.luligabi.magicfungi.client;

import me.luligabi.magicfungi.client.screen.GlyphCarvingScreen;
import me.luligabi.magicfungi.client.screen.SpellDiscoveryScreen;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MagicFungiClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.POTTED_IMPETUS_MUSHROOM, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.POTTED_CLYPEUS_MUSHROOM, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.POTTED_UTILIS_MUSHROOM, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.POTTED_VIVIFICA_MUSHROOM, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.POTTED_MORBUS_MUSHROOM, RenderLayer.getCutout());

        ScreenRegistry.register(ScreenHandlingRegistry.GLYPH_CARVING_SCREEN_HANDLER, GlyphCarvingScreen::new);
        ScreenRegistry.register(ScreenHandlingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, SpellDiscoveryScreen::new);
    }
}