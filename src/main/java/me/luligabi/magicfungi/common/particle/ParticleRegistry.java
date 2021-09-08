package me.luligabi.magicfungi.common.particle;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {


    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "utilis_flame"), UTILIS_FLAME);
    }

    public static void clientInit() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/utilis_flame"));
        }));

        ParticleFactoryRegistry.getInstance().register(UTILIS_FLAME, FlameParticle.Factory::new);
    }

    public static final DefaultParticleType UTILIS_FLAME = FabricParticleTypes.simple();

}