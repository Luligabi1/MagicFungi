package me.luligabi.magicfungi.common.item.glyph.vivifica;

import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.recipe.entity.pudicitiam.PudicitiamRecipe;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

import java.util.Optional;

public class PudicitiamGlyphItem extends BaseGlyphItem {

    public PudicitiamGlyphItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.VIVIFICA);
        setSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
        setActionType(ActionType.ENTITY);
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {
        if(!(livingEntity instanceof MobEntity)) return;

        Optional<PudicitiamRecipe> pudicitiamRecipe = playerEntity.world.getRecipeManager().getAllMatches(PudicitiamRecipe.Type.INSTANCE, playerEntity.getInventory(), playerEntity.world)
                .stream()
                .filter(recipe -> recipe.matches((MobEntity) livingEntity))
                .findAny();

        if(pudicitiamRecipe.isPresent()) {
            ((MobEntity) livingEntity).convertTo(pudicitiamRecipe.get().getRegularEntity(), true);
            super.executeGlyph(playerEntity, itemStack);
        }
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {}

}