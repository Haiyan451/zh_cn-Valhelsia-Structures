package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Big Tree Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.BigTreeStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-05-14
 */

public class BigTreeStructure extends AbstractValhelsiaStructure {

    public BigTreeStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "big_tree", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 35122018;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.BIG_TREE;
    }
}