package com.kenikydev.kenikyitems.block;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.custom.MagicBlock;
import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KenikyItems.MODID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static  final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",() -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore", () -> new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> MAGIC_BLOCK = registerBlock("magic_block", () -> new MagicBlock(BlockBehaviour.Properties.of().strength(6f).noLootTable().sound(SoundType.STONE)));

    public static final RegistryObject<SlabBlock> SAPPHIRE_SLABS = registerBlock("sapphire_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<StairBlock> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs",
            () -> new StairBlock(ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<ButtonBlock> SAPPHIRE_BUTTON = registerBlock("sapphire_button",
            () -> new ButtonBlock(BlockSetType.IRON,1,
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> SAPPHIRE_FENCE = registerBlock("sapphire_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<FenceGateBlock> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA,
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<WallBlock> SAPPHIRE_WALL = registerBlock("sapphire_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> SAPPHIRE_DOOR = registerBlock("sapphire_door",
            () -> new DoorBlock(BlockSetType.ACACIA,
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<TrapDoorBlock> SAPPHIRE_TRAPDOOR = registerBlock("sapphire_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.ACACIA,
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
