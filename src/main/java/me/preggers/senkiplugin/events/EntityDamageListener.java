package me.preggers.senkiplugin.events;

import me.preggers.senkiplugin.SenkiPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityDamageListener implements Listener {

    SenkiPlugin plugin;

    public EntityDamageListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL
                || e.getCause() == EntityDamageEvent.DamageCause.CONTACT)
                e.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setArrowsInBody(0);
                }
            }.runTaskLater(plugin, 0);
        }
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setMaximumNoDamageTicks(0);
        }
    }
}
