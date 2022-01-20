package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    public static void init() {
        FabricDefaultAttributeRegistry.register(MORBUS_MOOSHROOM, PolarBearEntity.createPolarBearAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 120.0D));
    }

    public static final EntityType<MorbusMooshroomEntity> MORBUS_MOOSHROOM =
            Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MagicFungi.MOD_ID, "morbus_mooshroom"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MorbusMooshroomEntity::new)
            .dimensions(EntityDimensions.fixed(0.9F, 1.4F)).build());
    
}