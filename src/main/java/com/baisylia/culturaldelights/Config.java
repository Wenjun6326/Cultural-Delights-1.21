package com.baisylia.culturaldelights;

import java.util.function.Supplier;
import vectorwing.farmersdelight.refabricated.mlconfigs.ConfigBuilder;
import vectorwing.farmersdelight.refabricated.mlconfigs.ConfigType;
import vectorwing.farmersdelight.refabricated.mlconfigs.ModConfigHolder;

public class Config {
    public static ModConfigHolder COMMON_CONFIG;

    public static final String CATEGORY_SETTINGS = "settings";

    public static Supplier<Boolean> FARMERS_BUY_CD_CROPS;
    public static Supplier<Boolean> WANDERING_TRADER_SELLS_CD_ITEMS;

    public static void register() {
        // Values are read from the static suppliers; nothing else to do at runtime.
    }

    static {
        ConfigBuilder builder = ConfigBuilder.create(CulturalDelights.MOD_ID, ConfigType.COMMON);
        builder.comment("Game settings").push(CATEGORY_SETTINGS);

        FARMERS_BUY_CD_CROPS = builder
                .comment("Should Novice and Apprentice Farmers buy this mod's crops? (May reduce chances of other trades appearing)")
                .define("farmersBuyFDCrops", true);

        WANDERING_TRADER_SELLS_CD_ITEMS = builder
                .comment("Should the Wandering Trader sell some of this mod's items? (Currently includes crop seeds and onions)")
                .define("wanderingTraderSellsFDItems", true);

        builder.pop();
        COMMON_CONFIG = builder.build();
        COMMON_CONFIG.forceLoad();
    }
}
