package com.kenikydev.kenikyitems.enchantment;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantmets {

    //Crea una clave única para identificar el encantamiento (kenikyitems:lightning_striker)
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "lightning_striker"));

    //Punto de entrada para registrar encantamientos durante el arranque
    public static void bootstrap(BootstrapContext<Enchantment> bootstrapContext) {
        var enchantments = bootstrapContext.lookup(Registries.ENCHANTMENT);
        var items = bootstrapContext.lookup(Registries.ITEM);


        register(bootstrapContext, LIGHTNING_STRIKER, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5, //rarity
                2, //// Max level
                Enchantment.dynamicCost(5,8), // Min cost
                Enchantment.dynamicCost(25,8), // Max cost
                2, // Anvil cost
                EquipmentSlotGroup.MAINHAND)) // Slot
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)) //No puede aparecer junto con otros encantamientos de daño
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, //Se activa después de atacar Afecta al atacante y a la víctima
                        EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect())); //Usa el efecto personalizado (Lightning striker)
    }


    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key,builder.build(key.location()));
    }
}
