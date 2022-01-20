package me.luligabi.magicfungi.client;

import draylar.omegaconfiggui.OmegaConfigGui;
import me.luligabi.magicfungi.client.screen.GlyphCarvingScreen;
import me.luligabi.magicfungi.client.screen.SpellDiscoveryScreen;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MagicFungiClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK,
                BlockRegistry.POTTED_IMPETUS_MUSHROOM,

                BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK,
                BlockRegistry.POTTED_CLYPEUS_MUSHROOM,

                BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK,
                BlockRegistry.POTTED_UTILIS_MUSHROOM,

                BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK,
                BlockRegistry.POTTED_VIVIFICA_MUSHROOM,

                BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK,
                BlockRegistry.POTTED_MORBUS_MUSHROOM,

                BlockRegistry.HOST_GRASS,
                BlockRegistry.HOST_TALL_GRASS,

                BlockRegistry.HOST_FERN,
                BlockRegistry.POTTED_HOST_FERN,

                BlockRegistry.LARGE_HOST_FERN);

        ScreenRegistry.register(ScreenHandlingRegistry.GLYPH_CARVING_SCREEN_HANDLER, GlyphCarvingScreen::new);
        ScreenRegistry.register(ScreenHandlingRegistry.SPELL_DISCOVERY_SCREEN_HANDLER, SpellDiscoveryScreen::new);


        ParticleRegistry.clientInit();

        EntityModelLayerRegistry.registerModelLayer(CLYPEUS_SHIELD_MODEL_LAYER, ShieldEntityModel::getTexturedModelData);
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier(MagicFungi.MOD_ID, "entity/clypeus_shield_base"));
            registry.register(new Identifier(MagicFungi.MOD_ID, "entity/clypeus_shield_base_nopattern"));
        });

        OmegaConfigGui.registerConfigScreen(MagicFungi.CONFIG);
    }

    public static final EntityModelLayer CLYPEUS_SHIELD_MODEL_LAYER = new EntityModelLayer(new Identifier(MagicFungi.MOD_ID, "clypeus_shield"),"main");
}