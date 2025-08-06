package com.kenikydev.kenikyitems.event;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.entity.ModEntities;
import com.kenikydev.kenikyitems.entity.client.TriceratopsModel;
import com.kenikydev.kenikyitems.entity.custom.TriceratopsEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KenikyItems.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /*
    Registra la definición de capas para el modelo 3D del Triceratops
    Necesario para que el renderizador sepa cómo construir el modelo 3D
    Se ejecuta durante la inicialización del cliente
     */
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TriceratopsModel.LAYER_LOCATION, TriceratopsModel::createBodyLayer);
    }

    /*
    Registra los atributos base para la entidad Triceratops
    Sin esto, la entidad no tendría atributos y podría comportarse incorrectamente
    Se ejecuta durante la inicialización del servidor
     */
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.TRICERATOPS.get(), //Entidad
                SpawnPlacementTypes.ON_GROUND, //Debe spawnear en tierra sólida
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, //Ignora las hojas
                Animal::checkAnimalSpawnRules, //Usa la reglas estandar de animales
                SpawnPlacementRegisterEvent.Operation.REPLACE); //Reemplaza cualquier registro previo
    }
}
