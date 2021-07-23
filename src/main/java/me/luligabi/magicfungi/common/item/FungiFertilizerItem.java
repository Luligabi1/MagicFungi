package me.luligabi.magicfungi.common.item;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FungiFertilizerItem extends Item {

    public FungiFertilizerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        Block impetusMushroom = BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK;
        double radius = 2.5;

        if(!world.isClient()) {
            for (int x = (int) -radius - 1; x <= radius; x++) {
                for (int y = (int) -radius - 1; y <= radius; y++) {
                    for (int z = (int) -radius - 1; z <= radius; z++) {
                        BlockPos blockPos2 = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                        if (!world.getBlockState(new BlockPos(blockPos.getX() + x, (blockPos.getY() + y) - 1, blockPos.getZ() + z)).isAir()
                                && impetusMushroom.canPlaceAt(impetusMushroom.getDefaultState(), world, blockPos2)
                                && world.getBlockState(blockPos2).isAir()) {
                            if (world.getRandom().nextDouble() > 0.8) { // 20% chance
                                world.setBlockState(blockPos2, Util.getMushroomByNumber(world.getRandom().nextInt(4)).getDefaultState());
                                context.getStack().decrement(1);
                                BoneMealItem.createParticles(context.getWorld(), context.getBlockPos(), 2);
                                playerEntity.getEntityWorld().playSound(null,
                                        playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                                        SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1F, 1F);
                            }
                        }
                    }
                }
            }
        }
        return ActionResult.success(world.isClient());
    }
}