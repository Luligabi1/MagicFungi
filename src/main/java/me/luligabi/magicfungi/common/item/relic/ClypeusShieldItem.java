package me.luligabi.magicfungi.common.item.relic;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldDisabledCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Box;

public class ClypeusShieldItem extends FabricBannerShieldItem implements Chargeable {

    public ClypeusShieldItem(Settings settings, int cooldownTicks, ToolMaterial material) {
        super(settings, cooldownTicks, material);
    }

    @Override
    public int getMaxCharge() {
        return 72;
    }

    @Override
    public int getChargeBarColor(ItemStack stack) {
        return 0x55FFFF;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isChargeFull(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public void initShieldEvents() {
        ShieldBlockCallback.EVENT.register((entity, source, amount, hand, shield) -> {
            if(!entity.world.isClient() && shield.getItem() instanceof ClypeusShieldItem) {
                increaseCharge(entity.getStackInHand(hand));
                if(entity.isSneaking()) {
                    for(Entity entities : entity.world.getOtherEntities(entity, new Box(entity.getX() - 5, entity.getY() - 3, entity.getZ() - 5, entity.getX() + 5, entity.getY() + 3, entity.getZ() + 5))) {
                        if(entities instanceof LivingEntity && !(entities instanceof TameableEntity)) {
                            entities.setVelocity(entities.getPos().subtract(entity.getPos()).normalize().multiply(3)); // TODO: Make this configurable
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });

        ShieldDisabledCallback.EVENT.register((entity, hand, shield) -> {
            if(!entity.world.isClient() && shield.getItem() instanceof ClypeusShieldItem) {
                for (int i = 0; i < 5; ++i) {
                    increaseCharge(entity.getStackInHand(hand));
                }
            }
            return ActionResult.PASS;
        });
    }

}