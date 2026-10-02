package com.baisylia.culturaldelights.item;

import com.baisylia.culturaldelights.CulturalDelights;
import com.baisylia.culturaldelights.block.ModBlocks;
import com.baisylia.culturaldelights.refabricated.CDRegUtils;
import com.baisylia.culturaldelights.refabricated.SeedBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    public static final LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

    public static ResourceKey<Item> key(String path) {
        return ResourceKey.create(Registries.ITEM, CulturalDelights.id(path));
    }

    /** Registers a plain item and adds it to the mod's creative tab. */
    private static Supplier<Item> item(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        properties.setId(key(name));
        Supplier<Item> item = CDRegUtils.regItem(name, () -> factory.apply(properties));
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    private static Supplier<Item> simple(String name, Item.Properties properties) {
        return item(name, Item::new, properties);
    }

    /** Resolves a block supplier that lives in {@link ModBlocks} without loading it eagerly. */
    private static Block seedBlock(Supplier<? extends Block> block) {
        return Objects.requireNonNull(block.get(), "block was not registered");
    }

    /**
     * Registers the BlockItem for a block that {@link ModBlocks} already registered,
     * and adds it to the mod's creative tab.
     *
     * <p>{@code setId()} alone leaves the item using the {@code item.<ns>.<id>} translation
     * key; block items are named through {@code block.<ns>.<id>} like vanilla, so the
     * description id has to be switched explicitly before the item is constructed.
     */
    private static Supplier<Item> blockItem(String name, Supplier<? extends Block> block, BiFunction<Block, Item.Properties, Item> factory) {
        final Block actualBlock = Objects.requireNonNull(block.get(), name + ": block was not registered before its item");
        Supplier<Item> registered = CDRegUtils.regItem(name, () ->
                factory.apply(actualBlock, new Item.Properties().setId(key(name)).useBlockDescriptionPrefix()));
        CREATIVE_TAB_ITEMS.add(registered);
        return registered;
    }

    // ---------------------------------------------------------------------
    // Items are declared in the same order as the 1.21 creative tab.
    // ---------------------------------------------------------------------

    // Seeds
    public static final Supplier<Item> CUCUMBER_SEEDS = item("cucumber_seeds",
            p -> new SeedBlockItem(seedBlock(ModBlocks.CUCUMBERS), p), new Item.Properties());
    public static final Supplier<Item> CORN_KERNELS = item("corn_kernels",
            p -> new SeedBlockItem(seedBlock(ModBlocks.CORN), p), new Item.Properties());
    public static final Supplier<Item> EGGPLANT_SEEDS = item("eggplant_seeds",
            p -> new SeedBlockItem(seedBlock(ModBlocks.EGGPLANTS), p), new Item.Properties());

    // Ingredients
    public static final Supplier<Item> AVOCADO = simple("avocado", ModFoods.foodItem(ModFoods.AVOCADO));
    public static final Supplier<Item> CUT_AVOCADO = simple("cut_avocado", ModFoods.foodItem(ModFoods.CUT_AVOCADO, ModFoods.FAST_FOOD));
    public static final Supplier<Item> CUCUMBER = simple("cucumber", ModFoods.foodItem(ModFoods.CUCUMBER));
    public static final Supplier<Item> PICKLE = simple("pickle", ModFoods.foodItem(ModFoods.PICKLE));
    public static final Supplier<Item> CUT_CUCUMBER = simple("cut_cucumber", ModFoods.foodItem(ModFoods.CUT_CUCUMBER, ModFoods.FAST_FOOD));
    public static final Supplier<Item> CUT_PICKLE = simple("cut_pickle", ModFoods.foodItem(ModFoods.CUT_PICKLE, ModFoods.FAST_FOOD));
    public static final Supplier<Item> EGGPLANT = simple("eggplant", ModFoods.foodItem(ModFoods.EGGPLANT));
    public static final Supplier<Item> CUT_EGGPLANT = simple("cut_eggplant", ModFoods.foodItem(ModFoods.CUT_EGGPLANT, ModFoods.FAST_FOOD));
    public static final Supplier<Item> SMOKED_EGGPLANT = simple("smoked_eggplant", ModFoods.foodItem(ModFoods.SMOKED_EGGPLANT));
    public static final Supplier<Item> SMOKED_TOMATO = simple("smoked_tomato", ModFoods.foodItem(ModFoods.SMOKED_TOMATO));
    public static final Supplier<Item> SMOKED_CUT_EGGPLANT = simple("smoked_cut_eggplant", ModFoods.foodItem(ModFoods.SMOKED_CUT_EGGPLANT, ModFoods.FAST_FOOD));
    public static final Supplier<Item> SMOKED_WHITE_EGGPLANT = simple("smoked_white_eggplant", ModFoods.foodItem(ModFoods.SMOKED_WHITE_EGGPLANT));
    public static final Supplier<Item> SMOKED_CORN = simple("smoked_corn", ModFoods.foodItem(ModFoods.SMOKED_CORN));
    public static final Supplier<Item> WHITE_EGGPLANT = simple("white_eggplant", ModFoods.foodItem(ModFoods.WHITE_EGGPLANT));
    public static final Supplier<Item> CORN_COB = simple("corn_cob", ModFoods.foodItem(ModFoods.CORN_COB));
    public static final Supplier<Item> SQUID = simple("squid", ModFoods.foodItem(ModFoods.SQUID));
    public static final Supplier<Item> COOKED_SQUID = simple("cooked_squid", ModFoods.foodItem(ModFoods.COOKED_SQUID));
    public static final Supplier<Item> GLOW_SQUID = simple("glow_squid", ModFoods.foodItem(ModFoods.GLOW_SQUID, ModFoods.GLOW_SQUID_CONSUMABLE));
    public static final Supplier<Item> RAW_CALAMARI = simple("raw_calamari", ModFoods.foodItem(ModFoods.RAW_CALAMARI));
    public static final Supplier<Item> COOKED_CALAMARI = simple("cooked_calamari", ModFoods.foodItem(ModFoods.COOKED_CALAMARI));

    // Meals
    public static final Supplier<Item> POPCORN = simple("popcorn", ModFoods.foodItem(ModFoods.POPCORN, ModFoods.FAST_FOOD));
    public static final Supplier<Item> CORN_DOUGH = simple("corn_dough", ModFoods.foodItem(ModFoods.CORN_DOUGH));
    public static final Supplier<Item> TORTILLA = simple("tortilla", ModFoods.foodItem(ModFoods.TORTILLA));
    public static final Supplier<Item> TORTILLA_CHIPS = simple("tortilla_chips", ModFoods.foodItem(ModFoods.TORTILLA_CHIPS, ModFoods.FAST_FOOD));
    public static final Supplier<Item> ELOTE = simple("elote", ModFoods.foodItem(ModFoods.ELOTE));
    public static final Supplier<Item> EMPANADA = simple("empanada", ModFoods.foodItem(ModFoods.EMPANADA));
    public static final Supplier<Item> HEARTY_SALAD = simple("hearty_salad",
            ModFoods.bowlFoodItem(ModFoods.HEARTY_SALAD, ModFoods.NOURISHMENT_LONG_DURATION));
    public static final Supplier<Item> BEEF_BURRITO = simple("beef_burrito", ModFoods.foodItem(ModFoods.BEEF_BURRITO));
    public static final Supplier<Item> MUTTON_SANDWICH = simple("mutton_sandwich", ModFoods.foodItem(ModFoods.MUTTON_SANDWICH));
    public static final Supplier<Item> EGGPLANT_PARMESAN = simple("eggplant_parmesan",
            ModFoods.bowlFoodItem(ModFoods.EGGPLANT_PARMESAN, ModFoods.NOURISHMENT_LONG_DURATION));
    public static final Supplier<Item> POACHED_EGGPLANTS = simple("poached_eggplants",
            ModFoods.bowlFoodItem(ModFoods.POACHED_EGGPLANTS, ModFoods.NOURISHMENT_LONG_DURATION));
    public static final Supplier<Item> EGGPLANT_BURGER = simple("eggplant_burger", ModFoods.foodItem(ModFoods.EGGPLANT_BURGER));
    public static final Supplier<Item> AVOCADO_TOAST = simple("avocado_toast", ModFoods.foodItem(ModFoods.AVOCADO_TOAST));
    public static final Supplier<Item> CREAMED_CORN = simple("creamed_corn",
            ModFoods.bowlFoodItem(ModFoods.CREAMED_CORN, null));
    public static final Supplier<Item> CHICKEN_TACO = simple("chicken_taco", ModFoods.foodItem(ModFoods.CHICKEN_TACO));
    public static final Supplier<Item> SPICY_CURRY = simple("spicy_curry",
            ModFoods.bowlFoodItem(ModFoods.SPICY_CURRY, ModFoods.NOURISHMENT_LONG_DURATION));
    public static final Supplier<Item> PORK_WRAP = simple("pork_wrap", ModFoods.foodItem(ModFoods.PORK_WRAP));
    public static final Supplier<Item> FISH_TACO = simple("fish_taco", ModFoods.foodItem(ModFoods.FISH_TACO));

    // Sushi
    public static final Supplier<Item> MIDORI_ROLL = simple("midori_roll", ModFoods.foodItem(ModFoods.MIDORI_ROLL));
    public static final Supplier<Item> MIDORI_ROLL_SLICE = simple("midori_roll_slice", ModFoods.foodItem(ModFoods.MIDORI_ROLL_SLICE, ModFoods.FAST_FOOD));
    public static final Supplier<Item> EGG_ROLL = simple("egg_roll", ModFoods.foodItem(ModFoods.EGG_ROLL, ModFoods.FAST_FOOD));
    public static final Supplier<Item> CHICKEN_ROLL = simple("chicken_roll", ModFoods.foodItem(ModFoods.CHICKEN_ROLL));
    public static final Supplier<Item> CHICKEN_ROLL_SLICE = simple("chicken_roll_slice", ModFoods.foodItem(ModFoods.CHICKEN_ROLL_SLICE, ModFoods.FAST_FOOD));
    public static final Supplier<Item> PUFFERFISH_ROLL = simple("pufferfish_roll",
            ModFoods.foodItem(ModFoods.PUFFERFISH_ROLL, ModFoods.PUFFERFISH_ROLL_CONSUMABLE));
    public static final Supplier<Item> TROPICAL_ROLL = simple("tropical_roll", ModFoods.foodItem(ModFoods.TROPICAL_ROLL, ModFoods.FAST_FOOD));
    public static final Supplier<Item> RICE_BALL = simple("rice_ball", ModFoods.foodItem(ModFoods.RICE_BALL, ModFoods.FAST_FOOD));
    public static final Supplier<Item> CALAMARI_ROLL = simple("calamari_roll", ModFoods.foodItem(ModFoods.CALAMARI_ROLL, ModFoods.FAST_FOOD));

    // Block items (registered by the block helpers so block+item ids stay in sync)
    public static final Supplier<Item> WILD_CUCUMBERS = blockItem("wild_cucumbers", ModBlocks.WILD_CUCUMBERS, BlockItem::new);
    public static final Supplier<Item> WILD_CORN = blockItem("wild_corn", ModBlocks.WILD_CORN, BlockItem::new);
    public static final Supplier<Item> WILD_EGGPLANTS = blockItem("wild_eggplants", ModBlocks.WILD_EGGPLANTS, BlockItem::new);
    public static final Supplier<Item> AVOCADO_PIT = blockItem("avocado_pit", ModBlocks.AVOCADO_PIT, BlockItem::new);
    public static final Supplier<Item> AVOCADO_SAPLING = blockItem("avocado_sapling", ModBlocks.AVOCADO_SAPLING, BlockItem::new);
    public static final Supplier<Item> AVOCADO_LOG = blockItem("avocado_log", ModBlocks.AVOCADO_LOG, BlockItem::new);
    public static final Supplier<Item> AVOCADO_WOOD = blockItem("avocado_wood", ModBlocks.AVOCADO_WOOD, BlockItem::new);
    public static final Supplier<Item> AVOCADO_LEAVES = blockItem("avocado_leaves", ModBlocks.AVOCADO_LEAVES, BlockItem::new);
    public static final Supplier<Item> FRUITING_AVOCADO_LEAVES = blockItem("fruiting_avocado_leaves", ModBlocks.FRUITING_AVOCADO_LEAVES, BlockItem::new);
    public static final Supplier<Item> AVOCADO_CRATE = blockItem("avocado_crate", ModBlocks.AVOCADO_CRATE, BlockItem::new);
    public static final Supplier<Item> CUCUMBER_CRATE = blockItem("cucumber_crate", ModBlocks.CUCUMBER_CRATE, BlockItem::new);
    public static final Supplier<Item> PICKLE_CRATE = blockItem("pickle_crate", ModBlocks.PICKLE_CRATE, BlockItem::new);
    public static final Supplier<Item> CORN_COB_CRATE = blockItem("corn_cob_crate", ModBlocks.CORN_COB_CRATE, BlockItem::new);
    public static final Supplier<Item> EGGPLANT_CRATE = blockItem("eggplant_crate", ModBlocks.EGGPLANT_CRATE, BlockItem::new);
    public static final Supplier<Item> WHITE_EGGPLANT_CRATE = blockItem("white_eggplant_crate", ModBlocks.WHITE_EGGPLANT_CRATE, BlockItem::new);
    public static final Supplier<Item> EXOTIC_ROLL_MEDLEY = blockItem("exotic_roll_medley", ModBlocks.EXOTIC_ROLL_MEDLEY,
            (b, p) -> new BlockItem(b, p.stacksTo(1)));
    public static final Supplier<Item> EGGPLANT_PARMESAN_BLOCK = blockItem("eggplant_parmesan_block", ModBlocks.EGGPLANT_PARMESAN_BLOCK,
            (b, p) -> new BlockItem(b, p.stacksTo(1)));

    public static void register() {
        // Registration happens in the static initialisers above.
    }

    /**
     * Lazy accessors used by custom blocks. Blocks are constructed while
     * {@link ModBlocks} initialises, which can happen before this class has finished
     * initialising; calling these only at runtime keeps the two classes from
     * deadlocking each other's static initialisers.
     */
    public static Item pufferfishRoll() {
        return PUFFERFISH_ROLL.get();
    }

    public static Item tropicalRoll() {
        return TROPICAL_ROLL.get();
    }

    public static Item chickenRollSlice() {
        return CHICKEN_ROLL_SLICE.get();
    }

    public static Item eggplantParmesan() {
        return EGGPLANT_PARMESAN.get();
    }

    /** Forces class initialisation in the right order from the mod entrypoint. */
    public static void touch() {
    }
}
