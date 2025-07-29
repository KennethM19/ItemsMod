package com.kenikydev.kenikyitems.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SlimmyEffect extends MobEffect {

    protected SlimmyEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int i) {
        if (livingEntity.horizontalCollision) { //Detecta colisiones laterales
            Vec3 initialVec = livingEntity.getDeltaMovement(); //Crea un nuevo vector de movimiento
            Vec3 climbVec = new Vec3(initialVec.x, 0.20, initialVec.z); //Añade impulso vertical fijo (Y=0.20)
            livingEntity.setDeltaMovement(climbVec.scale(0.970)); //Reduce ligeramente la velocidad (×0.97)
            return true;
        }

        return super.applyEffectTick(livingEntity, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int i) {
        return true;
    }
}
