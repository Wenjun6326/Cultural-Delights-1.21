package com.baisylia.culturaldelights.tab;

import com.baisylia.culturaldelights.item.ModItems;
import com.baisylia.culturaldelights.refabricated.CDRegUtils;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final Supplier<CreativeModeTab> CULTURALDELIGHTS_TAB = CDRegUtils.regTab("culturaldelights",
            () -> FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHICKEN_TACO.get()))
                    .title(Component.translatable("itemGroup.culturaldelights.culturaldelights_tab"))
                    .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach(item -> output.accept(item.get())))
                    .build());

    public static void register() {
        // Registered in the static initialiser.
    }

    public static void touch() {
    }
}
