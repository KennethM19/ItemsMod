package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.item.ModItems;
import com.kenikydev.kenikyitems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper fileHelper) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, KenikyItems.MODID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        //Grupoe de items que puedes ser transformados con el MAGIC_BLOCK
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.RAW_SAPPHIRE.get())
                .add(ModItems.SAPPHIRE.get())
                .add(Items.COAL);

        //Grpo de armaduras que pueden tener más diseños
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SAPPHIRE_BOOTS.get())
                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get());
    }
}
