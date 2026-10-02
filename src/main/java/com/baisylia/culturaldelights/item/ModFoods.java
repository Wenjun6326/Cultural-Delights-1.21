package com.baisylia.culturaldelights.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class ModFoods {
    //INGREDIENTS
    public static final FoodProperties AVOCADO = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties CUCUMBER = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.5F).build();
    public static final FoodProperties PICKLE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.7F).build();
    public static final FoodProperties EGGPLANT = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.2F).build();
    public static final FoodProperties SMOKED_EGGPLANT = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties SMOKED_TOMATO = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties SMOKED_WHITE_EGGPLANT = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.5F).build();
    public static final FoodProperties SMOKED_CORN = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.7F).build();
    public static final FoodProperties WHITE_EGGPLANT = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties CORN_COB = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.4F).build();
    public static final FoodProperties GINGER = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.5F).build();
    public static final FoodProperties SQUID = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.2F).build();
    public static final FoodProperties COOKED_SQUID = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.8F).build();
    public static final FoodProperties GLOW_SQUID = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.2F).build();
    public static final FoodProperties RAW_CALAMARI = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.2F).build();
    public static final FoodProperties COOKED_CALAMARI = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.5F).build();

    public static final FoodProperties CUT_AVOCADO = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties CUT_CUCUMBER = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties CUT_PICKLE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.5F).build();
    public static final FoodProperties CUT_EGGPLANT = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties SMOKED_CUT_EGGPLANT = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.4F).build();

    //SUSHI
    public static final FoodProperties MIDORI_ROLL = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.7F).build();
    public static final FoodProperties MIDORI_ROLL_SLICE = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.4F).build();
    public static final FoodProperties EGG_ROLL = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.5F).build();
    public static final FoodProperties CHICKEN_ROLL = (new FoodProperties.Builder()).nutrition(16).saturationModifier(0.8F).build();
    public static final FoodProperties CHICKEN_ROLL_SLICE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties PUFFERFISH_ROLL = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties TROPICAL_ROLL = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties RICE_BALL = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.5F).build();
    public static final FoodProperties CALAMARI_ROLL = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();

    //MEALS
    public static final FoodProperties CORN_DOUGH = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties TORTILLA = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties POPCORN = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.2F).build();
    public static final FoodProperties TORTILLA_CHIPS = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties ELOTE = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.7F).build();
    public static final FoodProperties EMPANADA = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties BEEF_BURRITO = (new FoodProperties.Builder()).nutrition(14).saturationModifier(0.7F).build();
    public static final FoodProperties MUTTON_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).build();
    public static final FoodProperties AVOCADO_TOAST = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5F).build();
    public static final FoodProperties CREAMED_CORN = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.8F).build();
    public static final FoodProperties CHICKEN_TACO = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).build();
    public static final FoodProperties PORK_WRAP = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).build();
    public static final FoodProperties FISH_TACO = (new FoodProperties.Builder()).nutrition(10).saturationModifier(0.8F).build();
    public static final FoodProperties HEARTY_SALAD = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.7F).build();
    public static final FoodProperties EGGPLANT_PARMESAN = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.9F).build();
    public static final FoodProperties EGGPLANT_BURGER = (new FoodProperties.Builder()).nutrition(12).saturationModifier(0.7F).build();
    public static final FoodProperties SPICY_CURRY = (new FoodProperties.Builder()).nutrition(12).saturationModifier(1.3F).build();
    public static final FoodProperties POACHED_EGGPLANTS = (new FoodProperties.Builder()).nutrition(8).saturationModifier(1.0F).build();

    // Consumable behaviour (26.1 split these out of FoodProperties)
    public static final Consumable FAST_FOOD = Consumables.defaultFood().consumeSeconds(0.8F).build();
    public static final Consumable GLOW_SQUID_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1), 1.0F))
            .build();
    public static final Consumable PUFFERFISH_ROLL_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 1), 1.0F))
            .build();
    public static final Consumable NOURISHMENT_LONG_DURATION = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F))
            .build();

    /** Mirrors FD's foodItem() helper: nutrition + consumable component. */
    public static Item.Properties foodItem(FoodProperties food) {
        return foodItem(food, null);
    }

    public static Item.Properties foodItem(FoodProperties food, @Nullable Consumable consumable) {
        return new Item.Properties().food(food)
                .component(DataComponents.CONSUMABLE, consumable != null ? consumable : Consumables.DEFAULT_FOOD);
    }

    /** Bowl food: keeps a bowl behind and stacks to 16, just like the 1.21 behaviour. */
    public static Item.Properties bowlFoodItem(FoodProperties food, @Nullable Consumable consumable) {
        return foodItem(food, consumable).usingConvertsTo(Items.BOWL).stacksTo(16);
    }
}
