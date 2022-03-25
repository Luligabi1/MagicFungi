package me.luligabi.magicfungi.common.block.crafting.essence;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipe;
import me.luligabi.magicfungi.common.screenhandler.essence.EssenceExtractorScreenHandler;
import me.luligabi.magicfungi.common.util.CatalystType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class EssenceExtractorBlockEntity extends LockableContainerBlockEntity implements SidedInventory {

    public EssenceExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegistry.ESSENCE_EXTRACTOR_BLOCK_ENTITY_TYPE, pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {

            public int get(int index) {
                return switch (index) {
                    case 0 -> EssenceExtractorBlockEntity.this.brewTime;
                    case 1 -> EssenceExtractorBlockEntity.this.fuel;
                    case 2 -> EssenceExtractorBlockEntity.this.fuelType;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EssenceExtractorBlockEntity.this.brewTime = value;
                    case 1 -> EssenceExtractorBlockEntity.this.fuel = value;
                    case 2 -> EssenceExtractorBlockEntity.this.fuelType = value;
                }

            }

            public int size() {
                return 3;
            }
        };
    }

    /*
       Catalysts are stored on the NBT under an integer, with the purpose of taking advantage of the PropertyDelegate.
       As primitive types cannot be null, we use CatalystType#EMPTY (5 on the values array) instead.
    */
    private static final int EMPTY_CATALYST = 5;
    private DefaultedList<ItemStack> inventory;
    int brewTime;
    private boolean[] slotsEmptyLastTick;
    private Item itemBrewing;
    int fuel;
    int fuelType;
    boolean hasCheckedCatalyst;
    protected final PropertyDelegate propertyDelegate;

    protected Text getContainerName() {
        return new TranslatableText("block.magicfungi.essence_extractor");
    }

    @Override
    public Text getDisplayName() {
        return this.getName().shallowCopy().formatted(Formatting.WHITE);
    }

    public int size() {
        return this.inventory.size();
    }

    public boolean isEmpty() {
        Iterator<ItemStack> var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = var1.next();
        } while(itemStack.isEmpty());

        return false;
    }

    public static void tick(World world, BlockPos pos, BlockState state, EssenceExtractorBlockEntity blockEntity) {
        ItemStack itemStack = blockEntity.inventory.get(4);
        if (blockEntity.fuel <= 18 && !itemStack.isEmpty()) {
            blockEntity.fuel += 2;
            blockEntity.fuelType = blockEntity.checkFuelType(itemStack);
            itemStack.decrement(1);
            markDirty(world, pos, state);
        }

        boolean canCraft = canCraft(blockEntity, world, blockEntity.fuelType);
        boolean isBrewing = blockEntity.brewTime > 0;
        ItemStack itemStack2 = blockEntity.inventory.get(3);

        if (isBrewing) {
            --blockEntity.brewTime;
            boolean bl3 = blockEntity.brewTime == 0;
            if (bl3 && canCraft) {
                craft(world, pos, blockEntity);
                markDirty(world, pos, state);
            } else if (!canCraft || !itemStack2.isOf(blockEntity.itemBrewing)) {
                blockEntity.brewTime = 0;
                markDirty(world, pos, state);
            }
        } else if (canCraft && blockEntity.fuel > 0) {
            --blockEntity.fuel;
            if(blockEntity.fuel <= 0) { // if fuel type reaches 0, remove fuelType tag
                blockEntity.fuelType = EMPTY_CATALYST;
            }
            blockEntity.brewTime = 400;
            blockEntity.itemBrewing = itemStack2.getItem();
            markDirty(world, pos, state);
        }

        boolean[] bl3 = blockEntity.getSlotsEmpty();
        if (!Arrays.equals(bl3, blockEntity.slotsEmptyLastTick)) {
            blockEntity.slotsEmptyLastTick = bl3;
            BlockState blockState = state;
            if (!(state.getBlock() instanceof EssenceExtractorBlock)) {
                return;
            }

            for(int i = 0; i < EssenceExtractorBlock.BOTTLE_PROPERTIES.length; ++i) {
                blockState = blockState.with(EssenceExtractorBlock.BOTTLE_PROPERTIES[i], bl3[i]);
            }

            world.setBlockState(pos, blockState, 2);
        }

    }

    private boolean[] getSlotsEmpty() {
        boolean[] bls = new boolean[3];

        for(int i = 0; i < 3; ++i) {
            if (!this.inventory.get(i).isEmpty()) {
                bls[i] = true;
            }
        }

        return bls;
    }

    private static boolean canCraft(EssenceExtractorBlockEntity blockEntity, World world, int fuelType) {
        ItemStack inputStack = blockEntity.inventory.get(3);
        Optional<EssenceRecipe> recipeOptional = createRecipeOptional(blockEntity, world);
        if(inputStack.isEmpty() ||recipeOptional.isEmpty()) return false;

        if(blockEntity.hasCheckedCatalyst) {
            for (int i = 0; i < 3; ++i) {
                ItemStack outputStacks = blockEntity.inventory.get(i);
                if (!outputStacks.isEmpty()) {
                    return true;
                }
            }
        } else {
            blockEntity.hasCheckedCatalyst = recipeOptional.get().getCatalystType() == CatalystType.values()[fuelType];
            markDirty(world, blockEntity.pos, blockEntity.getCachedState());
        }
        return false;
    }

    private static void craft(World world, BlockPos pos, EssenceExtractorBlockEntity blockEntity) {
        ItemStack itemStack = blockEntity.inventory.get(3);

        Optional<EssenceRecipe> recipeOptional = createRecipeOptional(blockEntity, world);
        if(recipeOptional.isEmpty()) return;

        for(int i = 0; i < 3; ++i) {
            ItemStack outputStack = blockEntity.inventory.get(i);
            if(outputStack.isOf(Items.GLASS_BOTTLE)) {
                blockEntity.inventory.set(i, recipeOptional.get().getOutput());
            } else if(outputStack.isItemEqual(recipeOptional.get().getOutput()) && outputStack.isStackable()) {
                outputStack.increment(1);
            }
        }

        itemStack.decrement(1);
        if (itemStack.getItem().hasRecipeRemainder()) { // This won't ever be triggered by normal means, but I'll let it slide as it might help modpack makers :)
            ItemStack i = new ItemStack(itemStack.getItem().getRecipeRemainder());
            if (itemStack.isEmpty()) {
                itemStack = i;
            } else {
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), i);
            }
        }

        blockEntity.inventory.set(3, itemStack);
        world.syncWorldEvent(WorldEvents.BREWING_STAND_BREWS, pos, 0);

        blockEntity.hasCheckedCatalyst = false;
        markDirty(world, blockEntity.pos, blockEntity.getCachedState());
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory);
        this.brewTime = nbt.getShort("BrewTime");
        this.fuel = nbt.getByte("Fuel");
        this.fuelType = nbt.getByte("FuelType");
        this.hasCheckedCatalyst = nbt.getBoolean("CheckedCatalyst");
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BrewTime", (short) brewTime);
        Inventories.writeNbt(nbt, inventory);
        nbt.putByte("Fuel", (byte) fuel);
        nbt.putByte("FuelType", (byte) fuelType);
        nbt.putBoolean("CheckedCatalyst", hasCheckedCatalyst);
    }

    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < this.inventory.size() ? this.inventory.get(slot) : ItemStack.EMPTY;
    }

    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) {
            this.inventory.set(slot, stack);
        }

    }

    public boolean canPlayerUse(PlayerEntity player) {
        return world.getBlockEntity(pos) == this && !(player.squaredDistanceTo(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) > 64.0D);
    }

    public boolean isValid(int slot, ItemStack stack) {
        return switch(slot) {
            case 3 -> true; // input slots
            case 4 -> EssenceExtractorScreenHandler.isCatalyst(stack); // catalyst slot
            default -> inventory.get(slot).isEmpty() && stack.isOf(Items.GLASS_BOTTLE); // bottle slots
        };
    }

    public int[] getAvailableSlots(Direction side) {
        return switch(side.getId()) {
            case 0 -> new int[]{0, 1, 2}; // DOWN - bottle slots
            case 1 -> new int[]{3}; // UP - input slot
            default -> new int[]{4, 0, 1, 2}; // SIDES - catalyst slot, bottle slots
        };
    }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return isValid(slot, stack);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return switch(side.getId()) {
            case 0 -> brewTime <= 0 && inventory.get(3).isEmpty() && !stack.isOf(Items.GLASS_BOTTLE); // Extract downward if nothing is being brewed, the input slot is empty and the extracted is not a glass bottle.
            case 1 -> brewTime <= 0; // Extract upward if nothing is being brewed.
            default -> false; // Never extract from sides
        };
    }

    public void clear() {
        this.inventory.clear();
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {

        return new EssenceExtractorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    private static Optional<EssenceRecipe> createRecipeOptional(EssenceExtractorBlockEntity blockEntity, World world) {
        return world.getServer().getRecipeManager().getFirstMatch(EssenceRecipe.Type.INSTANCE, blockEntity, world);
    }

    private int checkFuelType(ItemStack stack) {
        if(stack.isIn(TagRegistry.IMPETUS_CATALYST)) {
            return 0;
        } else if(stack.isIn(TagRegistry.CLYPEUS_CATALYST)) {
            return 1;
        } else if(stack.isIn(TagRegistry.UTILIS_CATALYST)) {
            return 2;
        } else if(stack.isIn(TagRegistry.VIVIFICA_CATALYST)) {
            return 3;
        } else if(stack.isIn(TagRegistry.MORBUS_CATALYST)) {
            return 4;
        }
        return EMPTY_CATALYST;
    }

}