package ink.ziip.hammer.hammercore.listener;

import ink.ziip.hammer.hammercore.api.listener.BaseListener;
import ink.ziip.hammer.hammercore.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

public class QuickActionListener extends BaseListener {

    @EventHandler
    public void onPlayerSwapItem(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            event.setCancelled(true);
            if (player.hasPermission("group.player")) {
                player.performCommand("dm open main_menu");
            } else {
                player.performCommand("tutorial 5");
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            event.setCancelled(true);
            player.performCommand("bend toggle");
        }
    }

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (event.getItem() == null) {
            return;
        }
        if (event.getItem().getType() != Material.CLOCK) {
            return;
        }
        Player player = event.getPlayer();
        player.performCommand("dm open main_menu");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (ConfigManager.UTIL_DISABLE_JOIN_MESSAGE)
            event.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (ConfigManager.UTIL_DISABLE_QUIT_MESSAGE)
            event.setQuitMessage(null);
    }
}