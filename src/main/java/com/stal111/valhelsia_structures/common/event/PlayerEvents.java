package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Player Events <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.PlayerEvents
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2022-04-03
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        BlockPos pos = event.getNewSpawn();

        if (pos == null) {
            return;
        }

        if (event.getEntityLiving().getLevel().getBlockState(pos).is(ModTags.Blocks.SLEEPING_BAGS)) {
            event.setCanceled(true);
        }
    }
}