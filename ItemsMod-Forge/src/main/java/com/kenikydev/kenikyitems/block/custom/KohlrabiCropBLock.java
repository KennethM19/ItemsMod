package com.kenikydev.kenikyitems.block.custom;

import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class KohlrabiCropBLock extends CropBlock {
    public static final int MAX_AGE = 6; //El número máximo de etapas de crecimiento
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE); //Propiedad de estado que almacena la edad actual (0-6)

    public KohlrabiCropBLock(Properties properties) {
        super(properties);
    }

    //Define qué ítem se usa como semilla
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.KOHLRABI_SEEDS.get();
    }

    //Devuelve la propiedad que controla las etapas de crecimiento
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    //Define cuándo el cultivo está completamente crecido
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    //Registra la propiedad AGE como parte del estado del bloque
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
