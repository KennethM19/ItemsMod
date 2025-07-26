package com.kenikydev.kenikyitems.item.custom;

import com.kenikydev.kenikyitems.block.ModBlocks;
import com.kenikydev.kenikyitems.component.ModDataComponentsTypes;
import com.kenikydev.kenikyitems.util.ShiftTooltipSupport;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.IRON_BLOCK, ModBlocks.SAPPHIRE_BLOCK.get()
    );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState originalState = level.getBlockState(pos);
        Block originalBlock = originalState.getBlock();
        ItemStack tool = context.getItemInHand();
        Player player = context.getPlayer();

        if (CHISEL_MAP.containsKey(originalBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pos, CHISEL_MAP.get(originalBlock).defaultBlockState());

                tool.hurtAndBreak(1,((ServerLevel) level),((ServerPlayer) player), item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                tool.set(ModDataComponentsTypes.COORDINATES.get(), context.getClickedPos());
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        ShiftTooltipSupport.addToolTip(components,"tooltip.items.custom.chisel","tooltip.items.custom.shift_up");

        if (itemStack.get(ModDataComponentsTypes.COORDINATES.get()) != null) {
            components.add(Component.literal("Last changed: " + itemStack.get(ModDataComponentsTypes.COORDINATES.get())));
        }

        super.appendHoverText(itemStack, context, components, flag);
    }
}
