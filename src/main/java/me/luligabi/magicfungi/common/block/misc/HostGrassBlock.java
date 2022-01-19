package me.luligabi.magicfungi.common.block.misc;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.block.mushroom.MagicMushroomPlantBlock;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.util.WorldUtil;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class HostGrassBlock extends GrassBlock {

    public HostGrassBlock(Settings settings) {
        super(settings);
    }


    @Override // Vanilla grass growth logic is overridden to use host variants of grass/dirt.
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = BlockRegistry.HOST_GRASS_BLOCK.getDefaultState();

        label48:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;

            for(int j = 0; j < i / 16; ++j) {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
                    continue label48;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
                ((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
            }

            if (blockState2.isAir()) {
                if (blockState.canPlaceAt(world, blockPos2)) {
                    world.setBlockState(blockPos2, blockState, 3);
                }
            }
        }

    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canSurvive(state, world, pos)) {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState blockState = this.getDefaultState();
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (canSpread(blockState, world, blockPos) && !world.getBlockState(blockPos).isAir()) {
                        if(WorldUtil.isMorbusSpreadingActive(world) ? world.getBlockState(blockPos).isIn(TagRegistry.MORBUS_GRASS_BLOCK_SPREADABLE) : world.getBlockState(blockPos).isOf(BlockRegistry.HOST_DIRT)) {
                            world.setBlockState(blockPos, blockState.with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                        }
                    }
                }
                // Slowly turns the block below into Host Dirt. Check HostDirtBlock#randomTick for how the dirt spreads through itself.
                if(world.getBlockState(pos.down()).isIn(TagRegistry.MORBUS_DIRT_SPREADABLE) && random.nextBoolean()) {
                    world.setBlockState(pos.down(), BlockRegistry.HOST_DIRT.getDefaultState());
                }

                // Converts any PlantBlock planted to either a Morbus Mushroom or Wither Rose
                BlockState blockStateUp = world.getBlockState(pos.up());
                if(blockStateUp.getBlock() instanceof PlantBlock && !blockStateUp.isIn(TagRegistry.HOST_BIOME_VEGETATION) && random.nextBoolean()) {
                    setToMorbusVariant(world, blockStateUp, pos);
                }
            }
        } else {
            world.setBlockState(pos, BlockRegistry.HOST_DIRT.getDefaultState());
        }
    }

    private boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    public boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    /*
     * Converts nature-blocks to their Morbus variant.
     *
     * Grass-like blocks -> Host Grass
     * Fern-like blocks -> Host Fern
     * Tall Grass-like blocks -> Host Tall Grass
     * Large Fern-like blocks -> Large Host Fern
     *
     * Any flower/non-Magic Mushrooms -> Wither Rose
     * Magic Mushrooms -> Morbus Mushroom
     *
     */
    private void setToMorbusVariant(World world, BlockState blockState, BlockPos pos) {
        if(blockState.isIn(TagRegistry.MORBUS_GRASS_SPREADABLE)) {
            setMorbusVariantBlock(world, pos, BlockRegistry.HOST_GRASS.getDefaultState());
        } else if(blockState.isIn(TagRegistry.MORBUS_FERN_SPREADABLE)) {
            setMorbusVariantBlock(world, pos, BlockRegistry.HOST_FERN.getDefaultState());
        } /*else if(blockState.isIn(TagRegistry.MORBUS_TALL_GRASS_SPREADABLE)) { // TODO: Complete this.

            setMorbusVariantBlock(world, pos, BlockRegistry.HOST_TALL_GRASS.getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
            world.setBlockState(pos.up(2), BlockRegistry.HOST_TALL_GRASS.getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER));
        } else if(blockState.isIn(TagRegistry.MORBUS_LARGE_FERN_SPREADABLE)) {
            setMorbusVariantBlock(world, pos, BlockRegistry.LARGE_HOST_FERN.getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
            world.setBlockState(pos.up(2), BlockRegistry.LARGE_HOST_FERN.getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER));
        }*/ else if(blockState.getBlock() instanceof FlowerBlock ||
                (blockState.getBlock() instanceof MushroomPlantBlock && !(blockState.getBlock() instanceof MagicMushroomPlantBlock))) {
            setMorbusVariantBlock(world, pos, Blocks.WITHER_ROSE.getDefaultState());
        } else if(blockState.getBlock() instanceof MagicMushroomPlantBlock && !blockState.isOf(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK)) {
            setMorbusVariantBlock(world, pos, BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK.getDefaultState());
        }
    }

    private void setMorbusVariantBlock(World world, BlockPos pos, BlockState state) {
        WorldUtil.setBlockWithSound(world, pos, state, SoundEvents.ENTITY_WITHER_AMBIENT,
                SoundCategory.BLOCKS, 0.6F, 0.8F);
    }

}