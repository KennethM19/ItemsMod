package com.kenikydev.kenikyitems.item;

import com.kenikydev.kenikyitems.KenikyItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterial {
    public static final Holder<ArmorMaterial> SAPPHIRE_ARMOR_MATERIAL = register("sapphire", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 3);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.CHESTPLATE, 7);
            }), 15, 4f, 1f, () -> ModItems.SAPPHIRE.get());


    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        //Nombre del material, Protección por pieza, Nivel de encantamiento, Resistencia general, resistencia por retroceso, material de reparación
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, name); //Identificador
        Holder<SoundEvent> sound = SoundEvents.ARMOR_EQUIP_GENERIC; //Sonido al equipar
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get()); //Convierte el item en un ingrediente de reparaciones
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location)); //Capas gráficas de la armadura
        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class); //Asigna valores de protección por pieza

        for (ArmorItem.Type type : ArmorItem.Type.values()) { //Asignación de protección
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, sound, ingredient, layers, toughness, knockbackResistance)); // rRegistra la armadura
    }
}
