package me.luligabi.magicfungi.common.block.misc;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.glyph.GlyphRegistry;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class CadentisBlock extends Block {

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


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.FAIL;
        if(!player.getStackInHand(hand).isIn(TagRegistry.MOD_MUSHROOMS)) return ActionResult.PASS;
        if(getMushroomTypeByStack(player.getStackInHand(hand)) != state.get(MUSHROOM_TYPE) && hit.getBlockPos() == pos) {
            world.setBlockState(pos, state.with(MUSHROOM_TYPE, getMushroomTypeByStack(player.getStackInHand(hand))));
            world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                    SoundCategory.BLOCKS, 1.0F, 1.0F);
            player.incrementStat(Stats.USED.getOrCreateStat(GlyphRegistry.CADENTIS_GLYPH));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        double x = (double) pos.getX() + 0.5D;
        double y = (double) pos.getY() + 0.5D;
        double z = (double) pos.getZ() + 0.5D;
        world.addParticle(MushroomType.getParticleEffect(state.get(MUSHROOM_TYPE)), x, y, z, 0.0D, 0.0D, 0.0D);
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
    }


    private MushroomType getMushroomTypeByStack(ItemStack itemStack) {
        if(itemStack.isOf(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK.asItem())) return MushroomType.IMPETUS;
        if(itemStack.isOf(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK.asItem())) return MushroomType.CLYPEUS;
        if(itemStack.isOf(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK.asItem())) return MushroomType.UTILIS;
        if(itemStack.isOf(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK.asItem())) return MushroomType.VIVIFICA;
        if(itemStack.isOf(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK.asItem())) return MushroomType.MORBUS;
        return MushroomType.INCOGNITA;
    }


    public static final EnumProperty<MushroomType> MUSHROOM_TYPE = EnumProperty.of("mushroom_type", MushroomType.class);
    private static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

}