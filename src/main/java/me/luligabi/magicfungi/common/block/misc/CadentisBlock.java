package me.luligabi.magicfungi.common.block.misc;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class CadentisBlock extends Block {

    public static final EnumProperty<MushroomType> MUSHROOM_TYPE = EnumProperty.of("mushroom_type", MushroomType.class);

    private static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public CadentisBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(MUSHROOM_TYPE, MushroomType.UTILIS));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return BOUNDING_SHAPE; }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(MUSHROOM_TYPE); }


    /*@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.success(world.isClient());
        } else {
            state.get(MUSHROOM_TYPE)
            player.incrementStat(Stats.USED.getOrCreateStat(GlyphRegistry.CADENTIS_GLYPH));
            return ActionResult.CONSUME_PARTIAL;
        }
    }*/

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double x = (double) pos.getX() + 0.5D;
        double y = (double) pos.getY() + 0.5D;
        double z = (double) pos.getZ() + 0.5D;
        world.addParticle(MushroomType.getParticleEffect(state.get(MUSHROOM_TYPE)), x, y, z, 0.0D, 0.0D, 0.0D);
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D); // TODO: Check if there's an alternative particle to use for white smoke.
    }
}