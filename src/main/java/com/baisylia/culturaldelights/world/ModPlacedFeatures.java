package com.baisylia.culturaldelights.world;

import com.baisylia.culturaldelights.CulturalDelights;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Placed features are shipped as datapack JSON
 * (data/culturaldelights/worldgen/placed_feature/*.json).
 */
public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> AVOCADO_PLACED_KEY = key("avocado_placed");
    public static final ResourceKey<PlacedFeature> CORN_PLACED_KEY = key("corn_placed");
    public static final ResourceKey<PlacedFeature> EGGPLANT_PLACED_KEY = key("eggplant_placed");
    public static final ResourceKey<PlacedFeature> CUCUMBER_PLACED_KEY = key("cucumber_placed");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, CulturalDelights.id(name));
    }

    public static void register() {
        // Features are added to biomes by ModBiomeModifiers.
    }
}
