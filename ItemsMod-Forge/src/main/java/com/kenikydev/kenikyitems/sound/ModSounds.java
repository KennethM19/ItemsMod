package com.kenikydev.kenikyitems.sound;

import com.kenikydev.kenikyitems.KenikyItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KenikyItems.MODID); //Asocia los sonidos al namespace del mod

    //Registra sonidos para diferentes acciones/ítems/bloques
    public static final RegistryObject<SoundEvent> CHISEL_USE = registerSoundEvent("chisel_use");
    public static final RegistryObject<SoundEvent> MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break");
    public static final RegistryObject<SoundEvent> MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step");
    public static final RegistryObject<SoundEvent> MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place");
    public static final RegistryObject<SoundEvent> MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit");
    public static final RegistryObject<SoundEvent> MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall");

    //Crea un SoundType completo para bloques
    public static final ForgeSoundType MAGIC_BLOCK_SOUNDS = new ForgeSoundType(1f,1f,
            ModSounds.MAGIC_BLOCK_BREAK, ModSounds.MAGIC_BLOCK_STEP, ModSounds.MAGIC_BLOCK_PLACE,
            ModSounds.MAGIC_BLOCK_HIT, ModSounds.MAGIC_BLOCK_FALL);

    //Registra un nuevo sonido con nombre
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, name)));
    }

    //Metodo para registrar todos los sonidos en el bus de eventos principal
    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }

}
