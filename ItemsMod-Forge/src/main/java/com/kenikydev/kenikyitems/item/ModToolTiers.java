package com.kenikydev.kenikyitems.item;

import com.kenikydev.kenikyitems.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier SAPPHIRE = new ForgeTier(1400, 4, 3f, 20,
            ModTags.Blocks.NEEDS_SAPPHIRE_TOOL, //Bloques que requieren este material en la herramienta
            () -> Ingredient.of(ModItems.SAPPHIRE.get()), //Item para reparar la herramienta
            ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL); //BLoques ineficientes para el tipo de herramienta
}
