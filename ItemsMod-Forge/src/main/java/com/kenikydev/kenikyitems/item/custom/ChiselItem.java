package com.kenikydev.kenikyitems.item.custom;

import com.kenikydev.kenikyitems.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.IRON_BLOCK, ModBlocks.SAPPHIRE_BLOCK.get()
    );

    public ChiselItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        Level level = p_41427_.getLevel();
        BlockPos pos = p_41427_.getClickedPos();
        BlockState originalState = level.getBlockState(pos);
        Block originalBlock = originalState.getBlock();
        ItemStack tool = p_41427_.getItemInHand();
        Player player = p_41427_.getPlayer();

        if (CHISEL_MAP.containsKey(originalBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pos, CHISEL_MAP.get(originalBlock).defaultBlockState());

                tool.hurtAndBreak(1,((ServerLevel) level),((ServerPlayer) player), item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
