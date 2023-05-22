package me.preggers.senkiplugin.events;

import me.preggers.senkiplugin.SenkiPlugin;
import me.preggers.senkiplugin.system.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    SenkiPlugin plugin;
    public PlayerDeathListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
    }
}
