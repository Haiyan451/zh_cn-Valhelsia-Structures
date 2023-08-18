package com.stal111.valhelsia_structures.core.data;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.data.client.ModBlockStateProvider;
import com.stal111.valhelsia_structures.core.data.client.ModItemModelProvider;
import com.stal111.valhelsia_structures.core.data.server.ModBiomeTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.ModBlockTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.ModItemTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.loot.ModBlockLootTables;
import com.stal111.valhelsia_structures.core.data.server.loot.ModLootModifierProvider;
import com.stal111.valhelsia_structures.core.data.server.loot.ModLootTableProvider;
import com.stal111.valhelsia_structures.data.ModSoundsProvider;
import com.stal111.valhelsia_structures.data.recipes.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.data.recipes.ValhelsiaRecipeProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Data Generators <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.DataGenerators
 *
 * @author Valhelsia Team
 * @since 2020-11-13
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        DataProviderInfo info = DataProviderInfo.of(event, ValhelsiaStructures.REGISTRY_MANAGER);

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModSoundsProvider(generator, existingFileHelper));

        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(info);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
       // generator.addProvider(event.includeServer(), new ModStructureTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK))));
        generator.addProvider(event.includeServer(), new ModLootModifierProvider(output));

        generator.addProvider(event.includeServer(), new ValhelsiaRecipeProvider(info, ModRecipeProvider::new));

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER.buildRegistrySet(info), Set.of(ValhelsiaStructures.MOD_ID)));
    }
}
