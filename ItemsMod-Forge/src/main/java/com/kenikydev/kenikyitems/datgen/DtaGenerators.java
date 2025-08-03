package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = KenikyItems.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DtaGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);

        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        gen.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        gen.addProvider(event.includeServer(), new ModDataPackEntries(packOutput, lookupProvider));
    }

}
