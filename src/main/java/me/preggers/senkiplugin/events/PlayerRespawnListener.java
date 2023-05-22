package me.preggers.senkiplugin.events;

import me.preggers.senkiplugin.SenkiPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerRespawnListener implements Listener {

    SenkiPlugin plugin;
    public PlayerRespawnListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        plugin.updatePlayerManager(player);
    }
}