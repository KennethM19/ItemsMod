package com.kenikydev.kenikyitems.worldgen;

import com.kenikydev.kenikyitems.KenikyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

//Maneja los modificadores de bioma, que determinan en qué biomas aparecen las características.
public class ModBiomeModifiers {

    // Obtiene referencias a los registros necesarios
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
    }

    // Crea una clave de recurso para un modificador de bioma
    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, name));
    }
}
