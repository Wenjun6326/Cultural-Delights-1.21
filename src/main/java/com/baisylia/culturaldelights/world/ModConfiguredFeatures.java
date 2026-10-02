package com.baisylia.culturaldelights.world;

import com.baisylia.culturaldelights.CulturalDelights;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/**
 * Configured features are shipped as datapack JSON
 * (data/culturaldelights/worldgen/configured_feature/*.json).
 */
public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY = key("avocado");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORN_KEY = key("corn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EGGPLANT_KEY = key("eggplant");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUCUMBER_KEY = key("cucumber");

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CulturalDelights.id(name));
    }
}
