package com.kenikydev.kenikyitems.item;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.item.custom.ChiselItem;
import com.kenikydev.kenikyitems.item.custom.FuelItem;
import com.kenikydev.kenikyitems.util.ShiftTooltipSupport;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlags) {
                    ShiftTooltipSupport.addToolTip(pTooltipComponents, "tooltip.items.custom.kfood", "tooltip.items.custom.shift_up");
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlags);
                }
            });

    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(), 1200) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlags) {
                    ShiftTooltipSupport.addToolTip(pTooltipComponents, "tooltip.items.custom.aurora_ashes", "tooltip.items.custom.shift_up");
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlags);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
