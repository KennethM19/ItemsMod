package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KenikyItems.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.SAPPHIRE_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.SAPPHIRE_WALL.get());
    }
}
