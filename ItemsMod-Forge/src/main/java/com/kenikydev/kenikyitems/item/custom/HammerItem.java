package com.kenikydev.kenikyitems.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos posInitial, Player player) {
        List<BlockPos> positions = new ArrayList<>();

        //Calcula la dirección del rayo de la mirada
        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        //Si el rayo no choca devuelve vacio
        if (traceResult.getType() == BlockHitResult.Type.MISS) {
            return positions;
        }

        //Crea un area de destrucción en el plano XZ
        if (traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -range; x <= range; x++) {
                for(int z = -range; z <= range; z++) {
                    positions.add(new BlockPos(posInitial.getX() + x, posInitial.getY(), posInitial.getZ() + z));
                }
            }
        }

        //Crea un area de destrucción en el plano XY
        if (traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(posInitial.getX() + x, posInitial.getY() + y, posInitial.getZ()));
                }
            }
        }

        //Crea un area de destrucción en el plano ZY
        if (traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int z = -range; z <= range; z++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(posInitial.getX(), posInitial.getY() + y, posInitial.getZ() + z));
                }
            }
        }

        return  positions;
    }
}
