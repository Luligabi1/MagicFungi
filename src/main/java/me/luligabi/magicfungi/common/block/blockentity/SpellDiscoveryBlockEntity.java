package me.luligabi.magicfungi.common.block.blockentity;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.screenhandler.SpellDiscoveryScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

public class SpellDiscoveryBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    public SpellDiscoveryBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegistry.SPELL_DISCOVERY_BLOCK_ENTITY, pos, state);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SpellDiscoveryScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(this.getWorld(), this.getPos()));
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("title.magicfungi.spell_discovery");
    }

    // TODO: Fix read/write to work on new inventory system
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

}