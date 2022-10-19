package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.block.crafting.moldingcauldron.MoldingCauldronBlock;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


// Take Water from Molding Cauldron with a Dispenser
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$9")
public abstract class MoldingCauldronTakeDispenserBehaviorMixin {

    @Inject(
            method = "dispenseSilently",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void dispenseSilently(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockState state = pointer.getWorld().getBlockState(pos);

        if(!state.isOf(BlockRegistry.MOLDING_CAULDRON_BLOCK)) return;
        if(!stack.isIn(ConventionalItemTags.EMPTY_BUCKETS)) return;
        if(!state.get(MoldingCauldronBlock.FULL)) return;

        pointer.getWorld().setBlockState(pos, state.with(MoldingCauldronBlock.FULL, false));
        cir.setReturnValue(new ItemStack(Items.WATER_BUCKET)); // FIXME: return proper bucket
    }
}
