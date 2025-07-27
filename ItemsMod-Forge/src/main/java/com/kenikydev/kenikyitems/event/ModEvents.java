package com.kenikydev.kenikyitems.event;

import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = KenikyItems.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)//Permita escuchar eventos de Forge
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>(); //Evita procesar bloques varias veces

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) { //La función se activa al destruir un bloque
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();

        //Comprueba si el jugador usa el martillo - el formato es válido si hay una clase propia par ael item (HammerItem)
        if (stack.getItem() instanceof HammerItem hammerItem && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {//Evita el procesamiento del mismo bloque repetidas veces
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) { //Obtiene los bloques adyacentes con getBlocksToBeDetroyed
                if (!pos.equals(initialBlockPos) && // omite el bloque inicial
                        hammerItem.isCorrectToolForDrops(stack, event.getLevel().getBlockState(pos)) && //verifica que es un bloque que se puede picar con el martillo
                        HARVESTED_BLOCKS.add(pos)) {  // add() devuelve true si no estaba presente
                    serverPlayer.gameMode.destroyBlock(pos);
                }
            }
        }
    }
}
