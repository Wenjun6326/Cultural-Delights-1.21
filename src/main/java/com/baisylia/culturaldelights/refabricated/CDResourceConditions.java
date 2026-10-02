package com.baisylia.culturaldelights.refabricated;

import com.baisylia.culturaldelights.Config;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * Data-driven counterpart of the 1.21 config gates. Villager trade entries carry a
 * {@code culturaldelights:config} load condition so the original config behaviour
 * ("farmersBuyFDCrops" / "wanderingTraderSellsFDItems") is preserved on 26.1,
 * where villager trades are data driven instead of registered in code.
 */
public final class CDResourceConditions {
    public static final ResourceConditionType<ConfigCondition> CONFIG = ResourceConditionType.create(
            Identifier.fromNamespaceAndPath("culturaldelights", "config"),
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.fieldOf("value").forGetter(ConfigCondition::value)
            ).apply(instance, ConfigCondition::new))
    );

    private CDResourceConditions() {
    }

    public static void register() {
        ResourceConditions.register(CONFIG);
    }

    private static boolean flag(String name) {
        Supplier<Boolean> supplier = switch (name) {
            case "farmersBuyFDCrops" -> Config.FARMERS_BUY_CD_CROPS;
            case "wanderingTraderSellsFDItems" -> Config.WANDERING_TRADER_SELLS_CD_ITEMS;
            default -> null;
        };
        return supplier == null || Boolean.TRUE.equals(supplier.get());
    }

    public record ConfigCondition(String value) implements ResourceCondition {
        @Override
        public ResourceConditionType<?> getType() {
            return CONFIG;
        }

        @Override
        public boolean test(@Nullable RegistryOps.RegistryInfoLookup registryInfoLookup) {
            return flag(this.value);
        }
    }
}
