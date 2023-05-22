package me.preggers.senkiplugin.events;
import me.preggers.senkiplugin.SenkiPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class PlayerPickUpArrowListener implements Listener {

    public PlayerPickUpArrowListener(SenkiPlugin plugin) {}
    @EventHandler
    public void onPlayerPickUpArrow(PlayerPickupArrowEvent e) {
        e.setCancelled(true);
    }
}
