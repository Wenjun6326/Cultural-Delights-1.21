package com.baisylia.culturaldelights;

import com.baisylia.culturaldelights.block.ModBlocks;
import com.baisylia.culturaldelights.item.ModItems;
import com.baisylia.culturaldelights.refabricated.CDLootTableHelper;
import com.baisylia.culturaldelights.refabricated.CDResourceConditions;
import com.baisylia.culturaldelights.tab.ModCreativeModeTabs;
import com.baisylia.culturaldelights.world.ModBiomeModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CulturalDelights implements ModInitializer {
    public static final String MOD_ID = "culturaldelights";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // ModItems owns every block-item, and ModBlocks in turn asks ModItems for
        // serving items lazily. Initialising ModItems first keeps the two static
        // initialisers from running into each other.
        ModItems.register();
        ModBlocks.register();
        ModCreativeModeTabs.register();
        ModBlocks.touch();
        ModCreativeModeTabs.touch();

        // Config first: the villager-trade resource conditions read these values.
        Config.register();
        CDResourceConditions.register();
        CDLootTableHelper.init();

        // World generation. Fabric wants biome modifications handed over during mod
        // initialisation, before any world is loaded. Without this call the wild crops
        // and avocado trees are registered but never injected into a biome, so they
        // never generate naturally.
        ModBiomeModifiers.init();

        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.AVOCADO_SAPLING.get(), 50);
            builder.add(ModBlocks.AVOCADO_LOG.get(), 50);
            builder.add(ModBlocks.AVOCADO_WOOD.get(), 50);
        });

        CompostableRegistry.INSTANCE.add(ModItems.CUCUMBER_SEEDS.get(), 0.25F);
        CompostableRegistry.INSTANCE.add(ModItems.CORN_KERNELS.get(), 0.25F);
        CompostableRegistry.INSTANCE.add(ModItems.EGGPLANT_SEEDS.get(), 0.25F);
        CompostableRegistry.INSTANCE.add(ModItems.AVOCADO_PIT.get(), 0.25F);
        CompostableRegistry.INSTANCE.add(ModItems.AVOCADO_SAPLING.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.AVOCADO_TOAST.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.CUT_CUCUMBER.get(), 0.35F);
        CompostableRegistry.INSTANCE.add(ModItems.CUT_AVOCADO.get(), 0.35F);
        CompostableRegistry.INSTANCE.add(ModItems.CUT_EGGPLANT.get(), 0.35F);
        CompostableRegistry.INSTANCE.add(ModItems.CUT_PICKLE.get(), 0.35F);
        CompostableRegistry.INSTANCE.add(ModItems.AVOCADO.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.CORN_COB.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.CUCUMBER.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.EGGPLANT.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.PICKLE.get(), 0.45F);
        CompostableRegistry.INSTANCE.add(ModItems.WILD_CORN.get(), 0.65F);
        CompostableRegistry.INSTANCE.add(ModItems.WILD_CUCUMBERS.get(), 0.65F);
        CompostableRegistry.INSTANCE.add(ModItems.WILD_EGGPLANTS.get(), 0.65F);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
