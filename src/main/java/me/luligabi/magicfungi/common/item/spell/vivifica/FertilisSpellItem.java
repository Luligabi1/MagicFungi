package me.luligabi.magicfungi.common.item.spell.vivifica;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class FertilisSpellItem extends AbstractSpellItem {

    public FertilisSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.VIVIFICA;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_BONE_MEAL_USE;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.fertilisSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.BLOCK;
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        double radius = 3.5;

        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(playerEntity.getX() + x,playerEntity.getY() + y,playerEntity.getZ() + z);
                    BlockState blockState = world.getBlockState(blockPos);
                    if ((blockState.getBlock() instanceof CropBlock) && !((CropBlock) blockState.getBlock()).isMature(blockState) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if(playerEntity.getRandom().nextInt(10) > 8) { //20% chance of instant growth, otherwise regular growth.
                            world.setBlockState(blockPos, ((CropBlock) blockState.getBlock()).withAge(CropBlock.MAX_AGE), 2);
                        } else {
                            blockState.randomTick((ServerWorld) world, blockPos, playerEntity.getRandom());
                        }
                    }
                }
            }
        }
        super.executeSpell(playerEntity, world);
    }

}