package com.baisylia.culturaldelights.block;

import com.baisylia.culturaldelights.block.custom.AvocadoPitBlock;
import com.baisylia.culturaldelights.block.custom.CornBlock;
import com.baisylia.culturaldelights.block.custom.CornUpperBlock;
import com.baisylia.culturaldelights.block.custom.CucumbersBlock;
import com.baisylia.culturaldelights.block.custom.EggplantFeastBlock;
import com.baisylia.culturaldelights.block.custom.EggplantsBlock;
import com.baisylia.culturaldelights.block.custom.ExoticRollMedleyBlock;
import com.baisylia.culturaldelights.block.custom.FruitingLeaves;
import com.baisylia.culturaldelights.item.ModItems;
import com.baisylia.culturaldelights.refabricated.CDRegUtils;
import com.baisylia.culturaldelights.world.tree.ModTreeGrowers;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    /**
     * Registers a block. 26.1 needs every block to carry its own id inside its properties
     * (used for the implicit loot table lookup), so the id is attached before construction.
     */
    private static <B extends Block> Supplier<B> reg(String name, Function<BlockBehaviour.Properties, B> factory,
                                                     Supplier<BlockBehaviour.Properties> properties) {
        BlockBehaviour.Properties props = properties.get().setId(CDRegUtils.blockKey(name));
        return CDRegUtils.regBlock(name, () -> factory.apply(props));
    }

    private static Supplier<BlockBehaviour.Properties> copy(Block source) {
        return () -> BlockBehaviour.Properties.ofFullCopy(source);
    }

    public static final Supplier<Block> WILD_CORN = reg("wild_corn",
            p -> new WildCropBlock(MobEffects.HUNGER, 6, p), copy(Blocks.TALL_GRASS));

    public static final Supplier<Block> WILD_CUCUMBERS = reg("wild_cucumbers",
            p -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 6, p), copy(Blocks.TALL_GRASS));

    public static final Supplier<Block> WILD_EGGPLANTS = reg("wild_eggplants",
            p -> new WildCropBlock(MobEffects.STRENGTH, 6, p), copy(Blocks.TALL_GRASS));

    public static final Supplier<Block> AVOCADO_PIT = reg("avocado_pit",
            p -> new AvocadoPitBlock(ModTreeGrowers.AVOCADO, p), copy(Blocks.OAK_SAPLING));

    public static final Supplier<Block> AVOCADO_SAPLING = reg("avocado_sapling",
            p -> new SaplingBlock(ModTreeGrowers.AVOCADO, p), copy(Blocks.OAK_SAPLING));

    public static final Supplier<Block> AVOCADO_LOG = reg("avocado_log",
            RotatedPillarBlock::new, copy(Blocks.JUNGLE_LOG));

    public static final Supplier<Block> AVOCADO_WOOD = reg("avocado_wood",
            RotatedPillarBlock::new, copy(Blocks.JUNGLE_WOOD));

    public static final Supplier<Block> AVOCADO_LEAVES = reg("avocado_leaves",
            p -> new TintedParticleLeavesBlock(0.01F, p), copy(Blocks.JUNGLE_LEAVES));

    public static final Supplier<Block> FRUITING_AVOCADO_LEAVES = reg("fruiting_avocado_leaves",
            FruitingLeaves::new, copy(Blocks.JUNGLE_LEAVES));

    public static final Supplier<Block> CUCUMBERS = reg("cucumbers",
            CucumbersBlock::new, ModBlocks::cropProperties);

    public static final Supplier<Block> EGGPLANTS = reg("eggplants",
            EggplantsBlock::new, ModBlocks::cropProperties);

    public static final Supplier<Block> CORN = reg("corn",
            CornBlock::new, ModBlocks::cropProperties);

    public static final Supplier<Block> CORN_UPPER = reg("corn_upper",
            CornUpperBlock::new, ModBlocks::cropProperties);

    public static final Supplier<Block> AVOCADO_CRATE = crate("avocado_crate");
    public static final Supplier<Block> CUCUMBER_CRATE = crate("cucumber_crate");
    public static final Supplier<Block> PICKLE_CRATE = crate("pickle_crate");
    public static final Supplier<Block> EGGPLANT_CRATE = crate("eggplant_crate");
    public static final Supplier<Block> WHITE_EGGPLANT_CRATE = crate("white_eggplant_crate");
    public static final Supplier<Block> CORN_COB_CRATE = crate("corn_cob_crate");

    public static final Supplier<Block> EXOTIC_ROLL_MEDLEY = reg("exotic_roll_medley",
            ExoticRollMedleyBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get())
                    .noOcclusion());

    public static final Supplier<Block> EGGPLANT_PARMESAN_BLOCK = reg("eggplant_parmesan_block",
            p -> new EggplantFeastBlock(p, ModItems::eggplantParmesan, true),
            copy(vectorwing.farmersdelight.common.registry.ModBlocks.ROAST_CHICKEN_BLOCK.get()));

    private static Supplier<Block> crate(String name) {
        return reg(name, Block::new,
                copy(vectorwing.farmersdelight.common.registry.ModBlocks.CARROT_CRATE.get()));
    }

    /**
     * Vanilla wheat's properties, rebuilt explicitly. We deliberately avoid
     * {@code ofFullCopy(Blocks.WHEAT)}: it also copies wheat's dynamic map colour
     * function, which reads {@code CropBlock.AGE} (0..7) and blows up on our
     * 0..3 / 0..7 crop states.
     */
    private static BlockBehaviour.Properties cropProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollision()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY)
                .noOcclusion();
    }

    public static void register() {
        StrippableBlockRegistry.register(AVOCADO_LOG.get(), Blocks.STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(AVOCADO_WOOD.get(), Blocks.STRIPPED_JUNGLE_WOOD);
    }

    /** Fire behaviour, mirroring the 1.21 values. */
    public static void touch() {
        FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();
        flammable.add(AVOCADO_LOG.get(), 5, 5);
        flammable.add(AVOCADO_WOOD.get(), 5, 5);
        flammable.add(AVOCADO_LEAVES.get(), 60, 30);
        flammable.add(FRUITING_AVOCADO_LEAVES.get(), 60, 30);
        flammable.add(WILD_CORN.get(), 100, 60);
        flammable.add(WILD_CUCUMBERS.get(), 100, 60);
        flammable.add(WILD_EGGPLANTS.get(), 100, 60);
    }
}
