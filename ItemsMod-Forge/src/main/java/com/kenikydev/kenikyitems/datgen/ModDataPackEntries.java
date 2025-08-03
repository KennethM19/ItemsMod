package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.enchantment.ModEnchantmets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackEntries extends DatapackBuiltinEntriesProvider {

    //Añade registros personalizados al sistema, registra encantamientos usando el metodo bootstrap de ModEnchantmets
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantmets::bootstrap);


    public ModDataPackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(KenikyItems.MODID));
    }
}
