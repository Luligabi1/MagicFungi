package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class EntityRegistry {


    public static final EntityType<MorbusMooshroomEntity> MORBUS_MOOSHROOM =
            Registry.register(Registry.ENTITY_TYPE, new Identifier(MagicFungi.MOD_ID, "morbus_mooshroom"),
            FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(MorbusMooshroomEntity::new)
            .dimensions(EntityDimensions.fixed(0.9F, 1.4F))
            .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MorbusMooshroomEntity::canSpawn)
            .build());

    public static final Item MORBUS_MOOSHROOM_SPAWN_EGG = new SpawnEggItem(MORBUS_MOOSHROOM, 0x251412, 0xA9C1C3, new FabricItemSettings().group(MagicFungi.ITEM_GROUP));


    public static final EntityType<UtilisLaserEntity> UTILIS_LASER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MagicFungi.MOD_ID, "utilis_laser"),
            FabricEntityTypeBuilder.<UtilisLaserEntity>create(SpawnGroup.MISC, UtilisLaserEntity::new)
            .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
            .trackRangeBlocks(4).trackedUpdateRate(20)
            .build());

    public static final EntityType<MorbusProjectileEntity> MORBUS_PROJECTILE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MagicFungi.MOD_ID, "morbus_projectile"),
            FabricEntityTypeBuilder.<MorbusProjectileEntity>create(SpawnGroup.MISC, MorbusProjectileEntity::new)
            .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
            .trackRangeBlocks(4).trackedUpdateRate(20)
            .build());


    public static void init() {
        FabricDefaultAttributeRegistry.register(MORBUS_MOOSHROOM, MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 52).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.5).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 14));
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mooshroom_spawn_egg"), MORBUS_MOOSHROOM_SPAWN_EGG);
    }

    private EntityRegistry() {
        // NO-OP
    }

}