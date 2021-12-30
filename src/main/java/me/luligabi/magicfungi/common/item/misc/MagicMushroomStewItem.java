package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.misc.component.MagicFungiComponents;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;

public class MagicMushroomStewItem extends StewItem {

    MushroomType mushroomType;

    public MagicMushroomStewItem(Settings settings, MushroomType mushroomType) {
        super(settings);
        this.mushroomType = mushroomType;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if(user instanceof PlayerEntity) {
            switch (mushroomType) {
                case IMPETUS:
                case CLYPEUS:
                case UTILIS:
                    MagicFungiComponents.MORBUS_CORRUPTION.get(user).decreaseBy(MagicFungi.CONFIG.magicMushroomStewCorruptionDecrease);
                    break;
                case VIVIFICA:
                    MagicFungiComponents.MORBUS_CORRUPTION.get(user).decreaseBy(MagicFungi.CONFIG.vivificaStewCorruptionDecrease);
                case MORBUS:
                    MagicFungiComponents.MORBUS_CORRUPTION.get(user).increaseBy(MagicFungi.CONFIG.morbusStewCorruptionIncrease);
                default:
                    break;
            }
        }

        return user instanceof PlayerEntity && ((PlayerEntity)user).getAbilities().creativeMode ? itemStack : new ItemStack(Items.BOWL);
    }
}