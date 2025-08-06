package com.kenikydev.kenikyitems.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    //Crea una lista básica de modificadores para generación de minerales
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement,
                                                       PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, // Controla la frecuencia
                InSquarePlacement.spread(), // Distribuye en área cuadrada
                pHeightRange, // Define el rango vertical donde puede aparecer
                BiomeFilter.biome()); // Filtra por bioma
    }

    //Para minerales que aparecen con frecuencia (como carbón o hierro)
    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(
                CountPlacement.of(pCount), // Número exacto de venas por chunk
                pHeightRange);
    }

    //Para minerales raros (como diamantes o esmeraldas)
    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(
                RarityFilter.onAverageOnceEvery(pChance), // Probabilidad de aparición
                pHeightRange);
    }
}
