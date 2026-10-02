package com.baisylia.culturaldelights.world;

import com.baisylia.culturaldelights.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Fabric replacement for the NeoForge biome-modifier JSON.
 * Reproduces exactly the same biome -> feature assignments.
 */
public class ModBiomeModifiers {
    public static void init() {
        addForBiomes(Set.of(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE), ModPlacedFeatures.AVOCADO_PLACED_KEY);
        addForBiomes(Set.of(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SAVANNA_PLATEAU), ModPlacedFeatures.CORN_PLACED_KEY);
        addForBiomes(Set.of(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE), ModPlacedFeatures.EGGPLANT_PLACED_KEY);
        addForBiomes(Set.of(Biomes.SWAMP, Biomes.MANGROVE_SWAMP), ModPlacedFeatures.CUCUMBER_PLACED_KEY);
    }

    private static void addForBiomes(Set<ResourceKey<Biome>> biomes, ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> feature) {
        Predicate<BiomeSelectionContext> selector = context -> biomes.contains(context.getBiomeKey());
        BiomeModifications.addFeature(selector, GenerationStep.Decoration.VEGETAL_DECORATION, feature);
    }
}
