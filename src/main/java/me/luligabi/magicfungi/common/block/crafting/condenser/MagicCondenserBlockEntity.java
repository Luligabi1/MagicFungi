package me.luligabi.magicfungi.common.block.crafting.condenser;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.item.misc.EssenceItem;
import me.luligabi.magicfungi.common.recipe.condenser.MagicCondenserRecipe;
import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.NetherStarItem;
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
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Optional;

// TODO: Implement SidedInventory interactions
public class MagicCondenserBlockEntity extends LockableContainerBlockEntity implements SidedInventory {

    public MagicCondenserBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegistry.MAGIC_CONDENSER_BLOCK_ENTITY_TYPE, pos, state);
        this.inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {

            public int get(int index) {
                return switch(index) {
                    case 0 -> MagicCondenserBlockEntity.this.impetusEssence;
                    case 1 -> MagicCondenserBlockEntity.this.clypeusEssence;
                    case 2 -> MagicCondenserBlockEntity.this.utilisEssence;
                    case 3 -> MagicCondenserBlockEntity.this.vivificaEssence;
                    case 4 -> MagicCondenserBlockEntity.this.morbusEssence;
                    case 5 -> MagicCondenserBlockEntity.this.condensingTime;
                    case 6 -> MagicCondenserBlockEntity.this.fuel;
                    default -> throw new IllegalStateException("Unexpected index: " + index);
                };
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0 -> MagicCondenserBlockEntity.this.impetusEssence = value;
                    case 1 -> MagicCondenserBlockEntity.this.clypeusEssence = value;
                    case 2 -> MagicCondenserBlockEntity.this.utilisEssence = value;
                    case 3 -> MagicCondenserBlockEntity.this.vivificaEssence = value;
                    case 4 -> MagicCondenserBlockEntity.this.morbusEssence = value;
                    case 5 -> MagicCondenserBlockEntity.this.condensingTime = value;
                    case 6 -> MagicCondenserBlockEntity.this.fuel = value;
                }
            }

            public int size() {
                return 3;
            }
        };
    }

    private DefaultedList<ItemStack> inventory;
    int condensingTime;
    private Item itemCondensing;

    int impetusEssence;
    int clypeusEssence;
    int utilisEssence;
    int vivificaEssence;
    int morbusEssence;
    int fuel;

    boolean hasCheckedRecipe;
    int impetusNeeded;
    int clypeusNeeded;
    int utilisNeeded;
    int vivificaNeeded;
    int morbusNeeded;
    int fuelNeeded;

    protected final PropertyDelegate propertyDelegate;


    public static void tick(World world, BlockPos pos, BlockState state, MagicCondenserBlockEntity blockEntity) {
        ItemStack ingredientStack = blockEntity.inventory.get(0);
        ItemStack essenceStack = blockEntity.inventory.get(1);
        ItemStack netherStarStack = blockEntity.inventory.get(2);

        consumeEssences(world, pos, state, blockEntity, essenceStack);
        if(blockEntity.fuel <= 8 && !netherStarStack.isEmpty()) {
            netherStarStack.decrement(1);
            blockEntity.fuel += 2;
            markDirty(world, pos, state);
        }

        boolean canCraft = canCraft(blockEntity, world);

        if(blockEntity.condensingTime > 0) {
            --blockEntity.condensingTime;
            if(blockEntity.condensingTime == 0 && blockEntity.hasCheckedRecipe) {
                craft(world, pos, blockEntity);
                markDirty(world, pos, state);
            } else if(!blockEntity.hasCheckedRecipe || !ingredientStack.isOf(blockEntity.itemCondensing)) {
                blockEntity.condensingTime = 0;
                blockEntity.itemCondensing = Items.AIR;

                blockEntity.hasCheckedRecipe = false;
                blockEntity.impetusNeeded = 0;
                blockEntity.clypeusNeeded = 0;
                blockEntity.utilisNeeded = 0;
                blockEntity.vivificaNeeded = 0;
                blockEntity.morbusNeeded = 0;
                blockEntity.fuelNeeded = 0;
                markDirty(world, pos, state);
            }
        } else if(canCraft) {
            blockEntity.condensingTime = 32*20;
            blockEntity.itemCondensing = ingredientStack.getItem();
            markDirty(world, pos, state);
        }
    }

    private static void consumeEssences(World world, BlockPos pos, BlockState state, MagicCondenserBlockEntity blockEntity, ItemStack essenceStack) {
        if(!essenceStack.isEmpty()) {
            if(blockEntity.impetusEssence <= 18 && essenceStack.isOf(ItemRegistry.IMPETUS_ESSENCE)) {
                blockEntity.impetusEssence += 4;
                essenceStack.decrement(1);
            }
            if(blockEntity.clypeusEssence <= 18 && essenceStack.isOf(ItemRegistry.CLYPEUS_ESSENCE)) {
                blockEntity.clypeusEssence += 4;
                essenceStack.decrement(1);
            }
            if(blockEntity.utilisEssence <= 18 && essenceStack.isOf(ItemRegistry.UTILIS_ESSENCE)) {
                blockEntity.utilisEssence += 4;
                essenceStack.decrement(1);
            }
            if(blockEntity.vivificaEssence <= 18 && essenceStack.isOf(ItemRegistry.VIVIFICA_ESSENCE)) {
                blockEntity.vivificaEssence += 4;
                essenceStack.decrement(1);
            }
            if(blockEntity.morbusEssence <= 18 && essenceStack.isOf(ItemRegistry.MORBUS_ESSENCE)) {
                blockEntity.morbusEssence += 4;
                essenceStack.decrement(1);
            }
        }
        markDirty(world, pos, state);
    }

    private static boolean canCraft(MagicCondenserBlockEntity blockEntity, World world) {
        ItemStack inputStack = blockEntity.inventory.get(0);
        Optional<MagicCondenserRecipe> recipeOptional = createRecipeOptional(blockEntity, world);
        if(inputStack.isEmpty() || recipeOptional.isEmpty() || blockEntity.fuel == 0) return false;

        if(!blockEntity.hasCheckedRecipe) {
            blockEntity.hasCheckedRecipe = canCheckRecipe(recipeOptional.get(), blockEntity);
            if(blockEntity.hasCheckedRecipe) {
                blockEntity.impetusNeeded = recipeOptional.get().getImpetusEssenceCost();
                blockEntity.clypeusNeeded = recipeOptional.get().getClypeusEssenceCost();
                blockEntity.utilisNeeded = recipeOptional.get().getUtilisEssenceCost();
                blockEntity.vivificaNeeded = recipeOptional.get().getVivificaEssenceCost();
                blockEntity.morbusNeeded = recipeOptional.get().getMorbusEssenceCost();

                blockEntity.fuelNeeded = recipeOptional.get().getNetherStarFuelCost();
            }
            markDirty(world, blockEntity.pos, blockEntity.getCachedState());
            return blockEntity.hasCheckedRecipe;
        }
        return false;
    }

    // Check if Magic Condenser has enough essence and fuel to for this recipe
    private static boolean canCheckRecipe(MagicCondenserRecipe recipe, MagicCondenserBlockEntity blockEntity) {
        if(recipe.getNetherStarFuelCost() > blockEntity.fuel) return false;
        if(recipe.getImpetusEssenceCost() > blockEntity.impetusEssence) return false;
        if(recipe.getClypeusEssenceCost() > blockEntity.clypeusEssence) return false;
        if(recipe.getUtilisEssenceCost() > blockEntity.utilisEssence) return false;
        if(recipe.getVivificaEssenceCost() > blockEntity.vivificaEssence) return false;
        return recipe.getMorbusEssenceCost() <= blockEntity.morbusEssence;
    }

    private static void craft(World world, BlockPos pos, MagicCondenserBlockEntity blockEntity) {
        ItemStack itemStack = blockEntity.inventory.get(0);
        Optional<MagicCondenserRecipe> recipeOptional = createRecipeOptional(blockEntity, world);
        if(recipeOptional.isEmpty()) return;

        if(itemStack.isItemEqual(recipeOptional.get().getOutput()) && itemStack.isStackable()) {
            itemStack.increment(1);
        }
        if(itemStack.getItem().hasRecipeRemainder()) {
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(itemStack.getItem().getRecipeRemainder()));
        }

        blockEntity.impetusEssence -= blockEntity.impetusNeeded;
        blockEntity.clypeusEssence -= blockEntity.clypeusNeeded;
        blockEntity.utilisEssence -= blockEntity.utilisNeeded;
        blockEntity.vivificaEssence -= blockEntity.vivificaNeeded;
        blockEntity.morbusEssence -= blockEntity.morbusNeeded;

        blockEntity.fuel -= blockEntity.fuelNeeded;

        blockEntity.inventory.set(0, recipeOptional.get().getOutput());

        blockEntity.hasCheckedRecipe = false;
        blockEntity.impetusNeeded = 0;
        blockEntity.clypeusNeeded = 0;
        blockEntity.utilisNeeded = 0;
        blockEntity.vivificaNeeded = 0;
        blockEntity.morbusNeeded = 0;
        blockEntity.fuelNeeded = 0;
        markDirty(world, blockEntity.pos, blockEntity.getCachedState());
    }

    private static Optional<MagicCondenserRecipe> createRecipeOptional(MagicCondenserBlockEntity blockEntity, World world) {
        return world.getServer().getRecipeManager().getFirstMatch(MagicCondenserRecipe.Type.INSTANCE, blockEntity, world);
    }


    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.condensingTime = nbt.getShort("CondensingTime");
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory);
        this.impetusEssence = nbt.getByte("ImpetusEssence");
        this.clypeusEssence = nbt.getByte("ClypeusEssence");
        this.utilisEssence = nbt.getByte("UtilisEssence");
        this.vivificaEssence = nbt.getByte("VivificaEssence");
        this.morbusEssence = nbt.getByte("MorbusEssence");
        this.fuel = nbt.getByte("Fuel");

        this.hasCheckedRecipe = nbt.getBoolean("CheckedRecipe");
        this.impetusNeeded = nbt.getByte("ImpetusNeeded");
        this.clypeusNeeded = nbt.getByte("ClypeusNeeded");
        this.utilisNeeded = nbt.getByte("UtilisNeeded");
        this.vivificaNeeded = nbt.getByte("VivificaNeeded");
        this.morbusNeeded = nbt.getByte("MorbusNeeded");
        this.fuelNeeded = nbt.getByte("FuelNeeded");
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("CondensingTime", (short) condensingTime);
        Inventories.writeNbt(nbt, inventory);
        nbt.putByte("ImpetusEssence", (byte) impetusEssence);
        nbt.putByte("ClypeusEssence", (byte) clypeusEssence);
        nbt.putByte("UtilisEssence", (byte) utilisEssence);
        nbt.putByte("VivificaEssence", (byte) vivificaEssence);
        nbt.putByte("MorbusEssence", (byte) morbusEssence);
        nbt.putByte("Fuel", (byte) fuel);

        nbt.putBoolean("CheckedRecipe", hasCheckedRecipe);
        nbt.putByte("ImpetusNeeded", (byte) impetusNeeded);
        nbt.putByte("ClypeusNeeded", (byte) clypeusNeeded);
        nbt.putByte("UtilisNeeded", (byte) utilisNeeded);
        nbt.putByte("VivificaNeeded", (byte) vivificaNeeded);
        nbt.putByte("MorbusNeeded", (byte) morbusNeeded);
        nbt.putByte("FuelNeeded", (byte) fuelNeeded);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.magicfungi.magic_condenser");
    }

    @Override
    public Text getDisplayName() {
        return this.getName().shallowCopy().formatted(Formatting.WHITE);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new MagicCondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public boolean isValid(int slot, ItemStack stack) {
        return switch(slot) {
            case 0 -> true; // ingredient slot
            case 1 -> stack.getItem() instanceof EssenceItem;
            case 2 -> stack.getItem() instanceof NetherStarItem;
            default -> false;
        };
    }

    public int[] getAvailableSlots(Direction side) {
        return switch(side.getId()) {
            case 0 -> new int[]{0}; // DOWN - ingredient slot
            case 1 -> new int[]{0}; // UP - ingredient slot
            default -> new int[]{1, 2}; // SIDES - essence/nether star fuel slots
        };
    }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return isValid(slot, stack);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return switch(side.getId()) {
            case 0, 1 -> condensingTime <= 0; // Extract up/downward if nothing is being condensed
            default -> false; // Never extract from sides
        };
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
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

    @Override
    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < this.inventory.size() ? this.inventory.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) {
            this.inventory.set(slot, stack);
        }

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

}