package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.client.tooltip.relic.utilis.UtilisPickaxeTooltipData;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
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
import java.util.Optional;

public class UtilisPickaxeItem extends PickaxeItem implements StateBasedRelic<UtilisPickaxeItem.State> {

    public UtilisPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    @Override // FIXME: Stack does not have Efficiency 7 by default
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
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new UtilisPickaxeTooltipData(getState(stack)));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getState(stack) != State.DYSFUNCTIONIS;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }


    @Override
    public void sendStateChangeMessage(PlayerEntity player, State state) {
        player.sendMessage(new LiteralText(state.getSymbol()).formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD), true);
        player.world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_HARP,
                SoundCategory.PLAYERS, 200F, 1.5F);
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