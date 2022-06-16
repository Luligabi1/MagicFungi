package me.luligabi.magicfungi.common.item.glyph.morbus;

import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.recipe.entity.corrumpere.CorrumpereRecipe;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

import java.util.Optional;

public class CorrumpereGlyphItem extends BaseGlyphItem {

    public CorrumpereGlyphItem(Item.Settings settings) {
        super(settings);
        setMushroomType(MushroomType.MORBUS);
        setSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
        setActionType(ActionType.ENTITY);
    }


    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {
        if(!(livingEntity instanceof MobEntity)) return;

        Optional<CorrumpereRecipe> corrumpereRecipe = playerEntity.world.getRecipeManager().getAllMatches(CorrumpereRecipe.Type.INSTANCE, playerEntity.getInventory(), playerEntity.world)
                .stream()
                .filter(recipe -> recipe.matches((MobEntity) livingEntity))
                .findAny();

        if(corrumpereRecipe.isPresent()) {
            ((MobEntity) livingEntity).convertTo(corrumpereRecipe.get().getCorruptedEntity(), true);
            super.executeGlyph(playerEntity, itemStack);
        }
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {}

}