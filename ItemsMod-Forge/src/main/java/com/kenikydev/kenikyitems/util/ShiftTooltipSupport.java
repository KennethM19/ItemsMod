package com.kenikydev.kenikyitems.util;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ShiftTooltipSupport {
    //Función para mostrar información del item
    public static void addToolTip(List<Component> tooltip, String shiftKey, String defaultKey) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(shiftKey));
        } else {
            tooltip.add(Component.translatable(defaultKey));
        }
    }
}
