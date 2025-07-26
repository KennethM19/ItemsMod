package com.kenikydev.kenikyitems.item;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.item.custom.ChiselItem;
import com.kenikydev.kenikyitems.item.custom.FuelItem;
import com.kenikydev.kenikyitems.util.ShiftTooltipSupport;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KenikyItems.MODID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>  RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISSEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> KFOOD = ITEMS.register("kfood",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KFOOD)) {
                @Override //Función para dar una descripción al item
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlags) {
                    ShiftTooltipSupport.addToolTip(pTooltipComponents, "tooltip.items.custom.kfood", "tooltip.items.custom.shift_up");
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlags);
                }
            });

    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(), 1200) {
                @Override //Función para dar una descripción al item
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlags) {
                    ShiftTooltipSupport.addToolTip(pTooltipComponents, "tooltip.items.custom.aurora_ashes", "tooltip.items.custom.shift_up");
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlags);
                }
            });

    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SAPPHIRE, 3, -2.4F))));

    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SAPPHIRE, 2, -2.8F))));

    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.SAPPHIRE, 4, -3.2F))));

    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SAPPHIRE, 1, -2.7F))));

    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.SAPPHIRE, 1, -2.6F))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
