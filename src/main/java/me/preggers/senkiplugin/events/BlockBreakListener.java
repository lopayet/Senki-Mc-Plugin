package me.preggers.senkiplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("quad.break"))
            return ;

        e.setCancelled(true);
        player.sendMessage(ChatColor.GOLD + "You can't do this");
    }
}
