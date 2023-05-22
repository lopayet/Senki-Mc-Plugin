package me.preggers.senkiplugin.system.senkiGunEvents;

import me.preggers.senkiplugin.SenkiPlugin;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SenkiInventoryClickListener implements Listener {

    private SenkiPlugin plugin;

    public SenkiInventoryClickListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        HumanEntity entity = e.getWhoClicked();
        if (entity instanceof Player) {
            e.setCancelled(true);
        }
    }
}
