package com.kenikydev.kenikyitems.enchantment;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.enchantment.custom.LightningStrikerEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    //Registro para efectos de encantamiento, los asocia al mod
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, KenikyItems.MODID);

    //Registra el codec de tu efecto personalizado
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER = ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker",
            () -> LightningStrikerEnchantmentEffect.CODEC);


    //Método que debes llamar desde tu clase principal
    public static void register(IEventBus bus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(bus);
    }
}
