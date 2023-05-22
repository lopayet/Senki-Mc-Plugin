package me.preggers.senkiplugin.events;

import me.preggers.senkiplugin.SenkiPlugin;
import me.preggers.senkiplugin.system.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlightListener implements Listener {

    SenkiPlugin plugin;
    public PlayerToggleFlightListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return ;
        }
        e.setCancelled(true);
        PlayerManager manager = plugin.getPlayerManager(player.getName());
        if (manager == null)
            return ;
        manager.doubleJump();
    }
}
