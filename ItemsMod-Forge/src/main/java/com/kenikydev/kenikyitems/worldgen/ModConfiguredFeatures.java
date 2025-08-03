package com.kenikydev.kenikyitems.worldgen;

import com.kenikydev.kenikyitems.KenikyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

//Maneja las características configuradas (Configured Features), que son instancias específicas de características con una configuración determinada.
public class ModConfiguredFeatures {

    // Metodo de inicialización (actualmente vacío)
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

    }

    // Crea una clave de recurso para una característica configurada
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, name));
    }

    // Registra una característica configurada
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
