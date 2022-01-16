package me.luligabi.magicfungi.mixin;

import net.minecraft.block.Block;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VanillaSurfaceRules.class)
public interface VanillaSurfaceRulesInvoker {

    @Invoker("block")
    static MaterialRules.MaterialRule block(Block block) {
        throw new AssertionError();
    }
}
