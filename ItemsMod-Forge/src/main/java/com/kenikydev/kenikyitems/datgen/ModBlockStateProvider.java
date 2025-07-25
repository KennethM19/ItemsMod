package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, KenikyItems.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

        stairsBlock(ModBlocks.SAPPHIRE_STAIRS.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        slabBlock(ModBlocks.SAPPHIRE_SLABS.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        buttonBlock(ModBlocks.SAPPHIRE_BUTTON.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceBlock(ModBlocks.SAPPHIRE_FENCE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock(ModBlocks.SAPPHIRE_FENCE_GATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock(ModBlocks.SAPPHIRE_WALL.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.SAPPHIRE_DOOR.get(), modLoc("block/sapphire_door_bottom"), modLoc("block/sapphire_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.SAPPHIRE_TRAPDOOR.get(), modLoc("block/sapphire_trapdoor"), true, "cutout");

        blockItem(ModBlocks.SAPPHIRE_STAIRS);
        blockItem(ModBlocks.SAPPHIRE_SLABS);
        blockItem(ModBlocks.SAPPHIRE_PRESSURE_PLATE);
        blockItem(ModBlocks.SAPPHIRE_FENCE_GATE);
        blockItem(ModBlocks.SAPPHIRE_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("kenikyitems:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("kenikyitems:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
