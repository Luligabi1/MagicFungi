package me.luligabi.magicfungi.rei.common.display;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class MoldingCauldronDisplay extends BasicDisplay {

    public MoldingCauldronDisplay() {
        this(null, null, null);
    }

    private MoldingCauldronDisplay(@Nullable List<EntryIngredient> input, @Nullable List<EntryIngredient> output, @Nullable NbtCompound tag) {
        super(
                Collections.singletonList(EntryIngredients.ofItemTag(ConventionalItemTags.FOODS)),
                Collections.singletonList(EntryIngredient.of(EntryStacks.of(new ItemStack(ItemRegistry.CLYPEUS_ESSENCE)).copy()
                        .tooltip(
                                Text.translatable("tooltip.magicfungi.molding_cauldron.output.1").formatted(Formatting.GRAY, Formatting.ITALIC),
                                Text.translatable("tooltip.magicfungi.molding_cauldron.output.2").formatted(Formatting.GRAY, Formatting.ITALIC)                        )
                        )
                )
        );
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CommonReiPlugin.MOLDING;
    }


    public static BasicDisplay.Serializer<MoldingCauldronDisplay> serializer() {
        return BasicDisplay.Serializer.ofRecipeLess(MoldingCauldronDisplay::new);
    }
}