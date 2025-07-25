package com.kenikydev.kenikyitems.datgen;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import com.kenikydev.kenikyitems.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture) {
        super(packOutput, providerCompletableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.SAPPHIRE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.MAGIC_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get())).save(recipeOutput, KenikyItems.MODID + ":sapphire_from_magic_block");

        oreSmelting(recipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.20f, 200, "sapphire");

        oreBlasting(recipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.30f, 100, "sapphire");

        stairBuilder(ModBlocks.SAPPHIRE_STAIRS.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        buttonBuilder(ModBlocks.SAPPHIRE_BUTTON.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        fenceBuilder(ModBlocks.SAPPHIRE_FENCE.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        fenceGateBuilder(ModBlocks.SAPPHIRE_FENCE_GATE.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        doorBuilder(ModBlocks.SAPPHIRE_DOOR.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        trapdoorBuilder(ModBlocks.SAPPHIRE_TRAPDOOR.get(), Ingredient.of(ModItems.SAPPHIRE.get())).group("sapphire")
                        .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_WALL.get(), ModItems.SAPPHIRE.get());

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_SLABS.get(), ModItems.SAPPHIRE.get());

        pressurePlate(recipeOutput, ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), ModItems.SAPPHIRE.get());
    }
}
