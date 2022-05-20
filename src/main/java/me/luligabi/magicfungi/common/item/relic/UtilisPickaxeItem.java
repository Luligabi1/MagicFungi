package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtByte;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UtilisPickaxeItem extends PickaxeItem implements StateBasedRelic<UtilisPickaxeItem.State> {

    public UtilisPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    @Override
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
                    addEnchantment(stack, Enchantments.SILK_TOUCH, 1);
                }
                case 1 -> { // Mollis -> Laser
                    stack.setSubNbt("State", NbtByte.of((byte) 2));

                    stack = removeEnchantments(stack, stack.getDamage());
                }
                case 2 -> stack.setSubNbt("State", NbtByte.of((byte) 3)); // Laser -> Dysfunctionis
                case 3 -> { // Dysfunctionis -> Functionis
                    stack.setSubNbt("State", NbtByte.of((byte) 0));

                    addEnchantment(stack, Enchantments.EFFICIENCY, 7);
                }
            }
            sendStateChangeMessage(user, getState(stack));
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


    @Override // TODO: Change sound
    public void sendStateChangeMessage(PlayerEntity player, State state) {
        player.sendMessage(new LiteralText(state.getSymbol()).formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD), true);
        player.world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,
                SoundCategory.PLAYERS, 1F, 1F);
    }

    public State getState(ItemStack stack) {
        return switch(stack.getOrCreateNbt().getByte("State")) {
            default -> State.FUNCTIONIS;
            case 1 -> State.MOLLIS;
            case 2 -> State.LASER;
            case 3 -> State.DYSFUNCTIONIS;
        };
    }


    public enum State {

        FUNCTIONIS(new TranslatableText("relicState.magicfungi.functionis"), "⛏"),
        MOLLIS(new TranslatableText("relicState.magicfungi.mollis"), "~"),
        LASER(new TranslatableText("relicState.magicfungi.laser"), "/"),
        DYSFUNCTIONIS(new TranslatableText("relicState.magicfungi.dysfunctionis"), "❌");

        State(TranslatableText translatableText, String symbol) {
            this.translatableText = translatableText;
            this.symbol = symbol;
        }

        private final TranslatableText translatableText;
        private final String symbol;


        public TranslatableText getTranslatableText() {
            return translatableText;
        }

        public String getSymbol() {
            return symbol;
        }

    }
}