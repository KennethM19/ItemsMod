package com.kenikydev.kenikyitems.entity;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.entity.custom.TriceratopsEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//responsable del registro de entidades personalizadas en el mod
public class ModEntities {

    //Crea un sistema de registro diferido para tipos de entidades
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITY_TYPES, //El registro de tipos de entidades de Forge
            KenikyItems.MODID);

    //Registra una nueva entidad
    public static final RegistryObject<EntityType<TriceratopsEntity>> TRICERATOPS = ENTITY_TYPES.register("triceratops", //Nombre interno de la entidad
            () -> EntityType.Builder.of(TriceratopsEntity::new, MobCategory.CREATURE).sized(1.5f,1.5f).build("triceratops"));

    //Registra todas las entidades con el bus de eventos principal
    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
