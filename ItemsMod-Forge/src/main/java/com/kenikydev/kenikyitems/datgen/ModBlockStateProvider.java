package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import com.kenikydev.kenikyitems.block.custom.KohlrabiCropBLock;
import com.kenikydev.kenikyitems.block.custom.SapphireLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        customLamp();

        makeCrop(((CropBlock) ModBlocks.KOHLRABI_CROP.get()), "kohlrabi_crop_stage", "kohlrabi_crop_stage");
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

    private void customLamp() {
        getVariantBuilder(ModBlocks.SAPPHIRE_LAMP.get()).forAllStates(state ->
        {
            if (state.getValue(SapphireLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("sapphire_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "block/sapphire_lamp_on")))};
            }  else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("sapphire_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "block/sapphire_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.SAPPHIRE_LAMP.get(), models().cubeAll("sapphire_lamp_on",
                ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "block/sapphire_lamp_on")));
    }

    private void makeCrop(Block block, String modelName, String textureName) {
        //Crea una función lambda que toma un BlockState y devuelve un array de ConfiguredModel
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        //Usa getVariantBuilder para configurar los modelos variantes para cada estado posible del bloque
        getVariantBuilder(block).forAllStates(function);

    }

    private ConfiguredModel[] states(BlockState state, Block block, String modelName, String textureName) {
        int age = state.getValue(((KohlrabiCropBLock) block).getAgeProperty()); //Obtiene la edad del cultivo del estado actual del bloque
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath( //Crea una ubicación de recurso (textura)
                KenikyItems.MODID, //El ID del mod
                "block/" + textureName + age //La ruta base de la textura + la edad actual
        );

        return new ConfiguredModel[] {
                new ConfiguredModel(
                        models().crop( //Usa el sistema de modelos para crear un modelo de tipo "crop"
                                modelName + age, //Asigna un nombre único al modelo basado en la edad
                                texture //Especifica la textura correspondiente
                        ).renderType("cutout") //Aplica el render type "cutout"
                )
        };
    }
}
