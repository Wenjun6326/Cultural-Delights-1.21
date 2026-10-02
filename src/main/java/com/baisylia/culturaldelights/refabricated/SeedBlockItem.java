package com.baisylia.culturaldelights.refabricated;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * Replaces 1.21's {@code ItemNameBlockItem}, which no longer exists in 26.1.
 *
 * <p>Vanilla's ItemNameBlockItem deliberately names the item after the seed itself
 * ("Cucumber Seeds") rather than after the crop it plants ("Cucumbers"). In 26.1 the
 * translation key is baked into {@link Item.Properties} before the item is built, so
 * the item prefix has to be selected explicitly: with the default block prefix the
 * seed would be labelled "Cucumbers" / "Corn" / "Eggplants".
 */
public class SeedBlockItem extends BlockItem {
    public SeedBlockItem(Block block, Item.Properties properties) {
        super(block, properties.useItemDescriptionPrefix());
    }
}
