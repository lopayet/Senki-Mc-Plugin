package me.preggers.senkiplugin.system.senkiGunEvents;
import me.preggers.senkiplugin.SenkiPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SenkiPlayerSwapHandItemsListener implements Listener {

    private SenkiPlugin plugin;

    public SenkiPlayerSwapHandItemsListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }
}
