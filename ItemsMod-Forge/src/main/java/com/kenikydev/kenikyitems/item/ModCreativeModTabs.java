package com.kenikydev.kenikyitems.item;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KenikyItems.MODID);

    public static final RegistryObject<CreativeModeTab> KENIKY_ITEMS_TAB = CREATIVE_MODE_TABS.register("keniky_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
            .title(Component.translatable("creativetab.keniky_items_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.SAPPHIRE.get());
                pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                pOutput.accept(ModItems.CHISSEL.get());
                pOutput.accept(ModItems.KFOOD.get());
                pOutput.accept(ModItems.AURORA_ASHES.get());

                pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                pOutput.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                pOutput.accept(ModBlocks.MAGIC_BLOCK.get());
                pOutput.accept(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                pOutput.accept(ModBlocks.SAPPHIRE_FENCE.get());
                pOutput.accept(ModBlocks.SAPPHIRE_FENCE_GATE.get());
                pOutput.accept(ModBlocks.SAPPHIRE_DOOR.get());
                pOutput.accept(ModBlocks.SAPPHIRE_TRAPDOOR.get());
                pOutput.accept(ModBlocks.SAPPHIRE_BUTTON.get());
                pOutput.accept(ModBlocks.SAPPHIRE_WALL.get());
                pOutput.accept(ModBlocks.SAPPHIRE_SLABS.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
