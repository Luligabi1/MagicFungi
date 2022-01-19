package me.luligabi.magicfungi.common.misc;

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
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "impetus_flame"), IMPETUS_FLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "clypeus_flame"), CLYPEUS_FLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "utilis_flame"), UTILIS_FLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "vivifica_flame"), VIVIFICA_FLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MagicFungi.MOD_ID, "morbus_flame"), MORBUS_FLAME);
    }

    public static void clientInit() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/impetus_flame"));
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/clypeus_flame"));
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/utilis_flame"));
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/vivifica_flame"));
            registry.register(new Identifier(MagicFungi.MOD_ID, "particle/morbus_flame"));
        }));

        ParticleFactoryRegistry.getInstance().register(IMPETUS_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(CLYPEUS_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UTILIS_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(VIVIFICA_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(MORBUS_FLAME, FlameParticle.Factory::new);
    }

    public static final DefaultParticleType IMPETUS_FLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType CLYPEUS_FLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType UTILIS_FLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType VIVIFICA_FLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType MORBUS_FLAME = FabricParticleTypes.simple();

}