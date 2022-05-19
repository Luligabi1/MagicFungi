package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtByte;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class UtilisPickaxeItem extends PickaxeItem {

    public UtilisPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    @Override // TODO: Add message and/or sound when switching State
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(world.isClient()) return TypedActionResult.pass(stack);

        if(!user.isSneaking()) {
            if(getState(stack) != State.LASER) return TypedActionResult.pass(stack);

            // TODO: Implement Laser
        } else {
            switch (stack.getOrCreateNbt().getByte("State")) {
                default -> { // Functionis -> Mollis
                    stack.setSubNbt("State", NbtByte.of((byte) 1));

                    stack = removeEnchantments(stack, stack.getDamage());
                    EnchantmentHelper.set(Collections.singletonMap(Enchantments.SILK_TOUCH, 1), stack);
                }
                case 1 -> { // Mollis -> Laser
                    stack.setSubNbt("State", NbtByte.of((byte) 2));

                    stack = removeEnchantments(stack, stack.getDamage());
                }
                case 2 -> stack.setSubNbt("State", NbtByte.of((byte) 3)); // Laser -> Dysfunctionis
                case 3 -> { // Dysfunctionis -> Functionis
                    stack.setSubNbt("State", NbtByte.of((byte) 0));

                    EnchantmentHelper.set(Collections.singletonMap(Enchantments.EFFICIENCY, 7), stack);
                }
            }
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return getState(stack) != State.DYSFUNCTIONIS ? super.getMiningSpeedMultiplier(stack, state) : 0.0F;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText(getState(stack).toString()));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getState(stack) != State.DYSFUNCTIONIS;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    private ItemStack removeEnchantments(ItemStack itemStack, int damage) {
        ItemStack copyStack = itemStack.copy();
        copyStack.removeSubNbt("Enchantments");
        copyStack.removeSubNbt("StoredEnchantments");

        if (damage > 0) {
            copyStack.setDamage(damage);
        } else {
            copyStack.removeSubNbt("Damage");
        }

        return copyStack;
    }


    private State getState(ItemStack stack) {
        return switch(stack.getOrCreateNbt().getByte("State")) {
            default -> State.FUNCTIONIS;
            case 1 -> State.MOLLIS;
            case 2 -> State.LASER;
            case 3 -> State.DYSFUNCTIONIS;
        };
    }


    public enum State {

        FUNCTIONIS(new TranslatableText("")),
        MOLLIS(new TranslatableText("")),
        LASER(new TranslatableText("")),
        DYSFUNCTIONIS(new TranslatableText(""));

        State(TranslatableText translatableText) {
            this.translatableText = translatableText;
        }

        private final TranslatableText translatableText;

        public TranslatableText getTranslatableText() {
            return translatableText;
        }
    }
}