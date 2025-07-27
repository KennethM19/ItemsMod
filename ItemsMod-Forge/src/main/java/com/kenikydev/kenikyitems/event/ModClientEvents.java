package com.kenikydev.kenikyitems.event;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//Efecto visual especial cuando se carga el arco
@Mod.EventBusSubscriber(modid = KenikyItems.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onComputerFovModifierEvent(ComputeFovModifierEvent event) { //Se ejecuta cuando el juego calcula el modificador de campo de visión (FOV)
        //Comprueba que el jugador esté usando un ítem Arco de sapphiro - este formato es valido si no hay una calse propia para el item
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.SAPPHIRE_BOW.get()) {
            float fovModifier = 1f; //alor base del modificador de campo de visión (FOV)
            int ticksUsingItem = event.getPlayer().getTicksUsingItem(); //Cuánto tiempo lleva el jugador cargando el arco
            float deltaTicks = (float) ticksUsingItem / 20f; //Normaliza el valor entre 0 y 1 (20 ticks = 1 segundo)

            if (deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks; //Aplica una curva cuadrática para que el zoom no sea lineal
            }

            fovModifier *= 1f -  deltaTicks * 0.15f; //Reduce el FOV en un 15% máximo
            event.setNewFovModifier(fovModifier); //Aplica el nuevo valor calculado
        }
    }

}
