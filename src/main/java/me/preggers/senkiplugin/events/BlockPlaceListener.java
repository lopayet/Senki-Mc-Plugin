package me.preggers.senkiplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("quad.place"))
            return ;

        e.setCancelled(true);
        player.sendMessage(ChatColor.GOLD + "You can't do this");
    }
}
