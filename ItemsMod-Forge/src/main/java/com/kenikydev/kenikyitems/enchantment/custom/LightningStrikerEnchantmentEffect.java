package com.kenikydev.kenikyitems.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect { //Interfaz para efectos de encantamiento personalizados
    //Sistema de serialización/deserialización para guardar el efecto
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);

    //Se ejecuta cuando el ítem encantado es usado
    @Override
    public void apply(ServerLevel serverLevel, //Mundo donde ocurre
                      int enchantmentLevel, //Nivel del encantamiento
                      EnchantedItemInUse enchantedItemInUse, //Ítem que activó el efecto
                      Entity entity, //Entidad que usó el ítem
                      Vec3 vec3) { //Posición del efecto
        //Si el nivel de encantamiento es 1, invoca 1 ryo, si es 2 invoca 2 rayos
        int bolts = enchantmentLevel == 1 ? 1 : 2;
        for (int i = 0; i < bolts; i++) {
            EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED);
        }
    }

    //Devuelve el codec para serializar este efecto
    @Override
    public MapCodec<?extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
