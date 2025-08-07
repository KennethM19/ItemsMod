package com.kenikydev.kenikyitems.entity.custom;

import com.kenikydev.kenikyitems.entity.ModEntities;
import com.kenikydev.kenikyitems.entity.TriceratopsVariant;
import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

/*
Entidad personalizada de Triceratops
Extiende de Animal, lo que le da todas las características base de una criatura pasiva
Hereda sistemas de reproducción, crecimiento y comportamiento animal
*/
public class TriceratopsEntity extends Animal {

    //Almacena el tipo de variante
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(TriceratopsEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.literal("Our Cool Triceratops"),
            BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_20);


    public TriceratopsEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    //Define la IA del Triceratops con prioridades (0 = más importante)
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this)); //Flotar en agua

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0)); //Huir cuando lastimado
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0)); //Reproducción
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25,
                stack -> stack.is(ModItems.KFOOD.get()), false)); //Puede ser tentado con comida

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25)); //Seguir a padres

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0)); //Movimiento aleatorio
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F)); //Mirar a jugadores
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this)); //Mirar alrededor
    }

    //Características físicas del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300) //Vida máxima
                .add(Attributes.MOVEMENT_SPEED, 0.350) //Velocidad media
                .add(Attributes.FOLLOW_RANGE, 240); //Largo alcance de visión
    }

    //Define qué item puede alimentarlo
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModItems.KFOOD.get());
    }

    //Crea nuevas crías al reproducirse
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.TRICERATOPS.get().create(serverLevel);
    }

    //Maneja animaciones
    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) { //Controla la animación de reposo
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    //Se actualiza cada tick en el cliente
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    /* VARIANT */

    //Registra una variable (VARIANT) que se sincroniza automáticamente entre el cliente y el servidor
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    //Obtiene el valor numérico crudo de la variante
    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    //Convierte el número a un enum TriceratopsVariant (usando & 255 para asegurar que sea un byte)
    public TriceratopsVariant getVariant() {
        return TriceratopsVariant.byId(this.getTypeVariant() & 255);
    }

    //Establece una nueva variante (también usando & 255 para seguridad)
    private void setVariant(TriceratopsVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    //Escribe la variante actual en el archivo de guardado
    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getTypeVariant());
    }

    //Lee la variante cuando se carga la entidad
    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.entityData.set(VARIANT, compoundTag.getInt("Variant"));
    }

    //Cuando aparece un nuevo Triceratops (naturalmente o por huevo), se le asigna una variante aleatoria
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData) {
        TriceratopsVariant variant = Util.getRandom(TriceratopsVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, groupData);
    }

    //Cuando dos Triceratops se reproducen, la cría recibe una variante aleatoria (no necesariamente igual a los padres)
    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel level, Animal animal, @Nullable AgeableMob ageableMob) {
        TriceratopsVariant variant = Util.getRandom(TriceratopsVariant.values(), this.random);
        ((TriceratopsEntity) ageableMob).setVariant(variant);
        super.finalizeSpawnChildFromBreeding(level, animal, ageableMob);
    }

    /*SOUNDS*/
    //Usa sonidos vanilla existentes para diferentes situaciones
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.SNIFFER_DEATH;
    }

    /* BOSS BAR */

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
