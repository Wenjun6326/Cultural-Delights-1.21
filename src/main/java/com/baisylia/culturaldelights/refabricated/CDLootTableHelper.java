package com.baisylia.culturaldelights.refabricated;

import com.baisylia.culturaldelights.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.criterion.EntityEquipmentPredicate;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import vectorwing.farmersdelight.common.tag.CommonTags;

/**
 * Squids drop calamari when killed with a knife (raw when not on fire, cooked when on fire).
 * The 1.21 loot modifiers shipped as NeoForge JSON; on 26.1 the same behaviour is rebuilt in code.
 */
public class CDLootTableHelper {
    private static final ResourceKey<LootTable> ENTITIES_SQUID =
            ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("entities/squid"));
    private static final ResourceKey<LootTable> ENTITIES_GLOW_SQUID =
            ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("entities/glow_squid"));

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin()) {
                scavengingLoot(key, tableBuilder, registries);
            }
        });
    }

    private static void scavengingLoot(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
        if (key != ENTITIES_SQUID && key != ENTITIES_GLOW_SQUID) {
            return;
        }

        HolderLookup.RegistryLookup<Item> items = registries.lookupOrThrow(Registries.ITEM);

        tableBuilder.withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.RAW_CALAMARI.get())
                        .when(killedWithKnife(items))
                        .when(fireState(false))));
        tableBuilder.withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.COOKED_CALAMARI.get())
                        .when(killedWithKnife(items))
                        .when(fireState(true))));
    }

    private static LootItemCondition.Builder killedWithKnife(HolderLookup.RegistryLookup<Item> items) {
        return LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.entity().equipment(
                        EntityEquipmentPredicate.Builder.equipment()
                                .mainhand(ItemPredicate.Builder.item().of(items, CommonTags.Items.TOOLS_KNIFE))));
    }

    private static LootItemCondition.Builder fireState(boolean onFire) {
        return LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity()
                        .flags(EntityFlagsPredicate.Builder.flags().setOnFire(onFire)));
    }
}
