package me.luligabi.magicfungi.common.block.crafting;

import me.luligabi.magicfungi.common.screenhandler.spell.SpellDiscoveryScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SpellDiscoveryBlock extends Block {

    public SpellDiscoveryBlock(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.success(world.isClient());
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResult.CONSUME_PARTIAL;
        }
    }

    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                new SpellDiscoveryScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), Text.translatable("title.magicfungi.spell_discovery"));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if(EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.getStackInHand(player.getActiveHand())) <= 0) {
            if(!world.isClient()) {
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                        SoundEvents.BLOCK_LARGE_AMETHYST_BUD_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            for(int i = 0; i < 5; ++i) {
                Direction direction = Direction.random(player.getRandom());
                if (direction != Direction.UP) {
                    BlockPos blockPos = pos.offset(direction);
                    BlockState blockState = world.getBlockState(blockPos);
                    if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                        double x = pos.getX();
                        double y = pos.getY() + 0.5D;
                        double z = pos.getZ();
                        if (direction == Direction.DOWN) {
                            y -= 0.05D;
                            x += player.getRandom().nextDouble();
                            z += player.getRandom().nextDouble();
                        } else {
                            y += player.getRandom().nextDouble() * 0.8D;
                            if (direction.getAxis() == Direction.Axis.X) {
                                z += player.getRandom().nextDouble();
                                if (direction == Direction.EAST) {
                                    ++x;
                                } else {
                                    x += 0.05D;
                                }
                            } else {
                                x += player.getRandom().nextDouble();
                                if (direction == Direction.SOUTH) {
                                    ++z;
                                } else {
                                    z += 0.05D;
                                }
                            }
                        }

                        world.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

}