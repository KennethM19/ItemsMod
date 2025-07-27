package com.kenikydev.kenikyitems.util;


import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

//Configura propiedades personalizadas para los items en el lado del cliente
public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeCustomBow(ModItems.SAPPHIRE_BOW.get());
    }

    private static void makeCustomBow(Item item) {
        ItemProperties.register(
                item, //Selecciona el item a modificar
                ResourceLocation.withDefaultNamespace("pull"), //Nombre de la propiedad (pull)
                (itemStack, clientLevel, livingEntity, i) -> { //Lambda determina el valor
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration(livingEntity) - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F
        );
    }
}
