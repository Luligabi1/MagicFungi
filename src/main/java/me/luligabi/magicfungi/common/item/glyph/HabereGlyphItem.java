package me.luligabi.magicfungi.common.item.glyph;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.luligabi.magicfungi.common.mixin.MobSpawnerBlockEntityAccessor;
import me.luligabi.magicfungi.common.mixin.MobSpawnerLogicAccessor;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class HabereGlyphItem extends GlyphBaseItem {

    public HabereGlyphItem(Settings settings) {
        super(settings);
        setSound(SoundEvents.BLOCK_BEACON_ACTIVATE);
        setMushroomType(MushroomType.UTILIS);
    }

    @Override
    public void executeGlyph(PlayerEntity playerEntity) {
        World world = playerEntity.getEntityWorld();
        ItemStack mobSpawner = new ItemStack(world.getBlockState(blockPos).getBlock());
        if(mobSpawner.getItem() != Blocks.SPAWNER.asItem()) return;
        
        try {
            mobSpawner.putSubTag("BlockEntityTag", StringNbtReader.parse( // Puts tag on mobSpawner itemstack containing which mob the spawner generates.
                    "{SpawnData:{id:" + ((MobSpawnerLogicAccessor) ((MobSpawnerBlockEntityAccessor) world.getBlockEntity(blockPos)).getLogic()).getSpawnEntry().getEntityNbt().getString("id") + "}}"));
            System.out.println(((MobSpawnerLogicAccessor) ((MobSpawnerBlockEntityAccessor) world.getBlockEntity(blockPos)).getLogic()).getSpawnEntry().getEntityNbt().getString("id"));
        } catch(CommandSyntaxException ignored) {}
        world.spawnEntity(new ItemEntity(world,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), mobSpawner));
        world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
    }
}