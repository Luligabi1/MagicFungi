package me.luligabi.magicfungi.common.misc.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class MagicFungiComponents implements EntityComponentInitializer {

    @Override
    public void registerEntityComponentFactories(@NotNull EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MORBUS_CORRUPTION, MorbusCorruptionComponent::new, RespawnCopyStrategy.ALWAYS_COPY); // TODO: Implement custom RespawnCopyStrategy
    }


    public static final ComponentKey<MorbusCorruptionComponent> MORBUS_CORRUPTION =
            ComponentRegistry.getOrCreate(new Identifier(MagicFungi.MOD_ID, "morbus_corruption"), MorbusCorruptionComponent.class);
}