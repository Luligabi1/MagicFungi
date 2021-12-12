package me.luligabi.magicfungi.mixin;

import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(VegetationPlacedFeatures.class)
public interface VegetationPlacedFeaturesInvoker {

    @Invoker("modifiersWithChance")
    static List<PlacementModifier> modifiersWithChance(int chance, @Nullable PlacementModifier modifier) {
        throw new AssertionError();
    }
}