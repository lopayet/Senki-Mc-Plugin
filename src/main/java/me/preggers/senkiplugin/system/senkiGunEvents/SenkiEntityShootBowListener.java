package me.preggers.senkiplugin.system.senkiGunEvents;

import me.preggers.senkiplugin.SenkiPlugin;
import me.preggers.senkiplugin.system.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.Objects;

public class SenkiEntityShootBowListener implements Listener {

    SenkiPlugin plugin;

    public SenkiEntityShootBowListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player &&
                Objects.requireNonNull(e.getBow()).getType() == Material.CROSSBOW))
            return ;
        Player player = (Player) entity;
        PlayerManager manager = plugin.getPlayerManager(player.getName());
        if (manager == null || manager.isInLobby())
            return ;
        e.setCancelled(true);
        manager.getSenkiGun().shootGun();
    }
}
