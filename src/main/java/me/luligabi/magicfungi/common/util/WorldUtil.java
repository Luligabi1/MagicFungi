package me.luligabi.magicfungi.common.util;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.misc.GameRuleRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {

    public static long getCurrentInGameDay(World world) {
        return world.getTimeOfDay()/24000L;
    }

    public static boolean isMorbusSpreadingActive(World world) {
        return world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING) &&
                WorldUtil.getCurrentInGameDay(world) >= world.getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY);
    }

    public static void setBlockWithSound(World world, BlockPos pos, BlockState state, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        world.setBlockState(pos.up(), state);
        world.playSound(null, pos.up(), soundEvent, soundCategory, volume, pitch);
    }

    public static final BlockState LARGE_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false);
    public static final BlockState TALL_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);

    public static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);
}