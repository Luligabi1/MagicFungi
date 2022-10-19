package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.block.crafting.moldingcauldron.MoldingCauldronBlock;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Place Water on a Molding Cauldron with a Dispenser
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$8")
public abstract class MoldingCauldronPlaceDispenserBehaviorMixin {

    @Inject(
            method = "dispenseSilently",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void dispenseSilently(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockState state = pointer.getWorld().getBlockState(pos);

        if(!state.isOf(BlockRegistry.MOLDING_CAULDRON_BLOCK)) return;
        if(!stack.isIn(ConventionalItemTags.WATER_BUCKETS)) return;
        if(state.get(MoldingCauldronBlock.FULL)) return;

        pointer.getWorld().setBlockState(pos, state.with(MoldingCauldronBlock.FULL, true));

        Item recipeRemainder = stack.getItem().getRecipeRemainder();
        cir.setReturnValue(recipeRemainder != null ? new ItemStack(recipeRemainder) : ItemStack.EMPTY);
    }
}
