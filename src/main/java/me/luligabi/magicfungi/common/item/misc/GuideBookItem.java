package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

public class GuideBookItem extends Item {

    public GuideBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            PatchouliAPI.get().openBookGUI((ServerPlayerEntity) user, new Identifier(MagicFungi.MOD_ID, MagicFungi.MOD_ID));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
