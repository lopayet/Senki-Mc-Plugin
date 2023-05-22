package me.preggers.senkiplugin.events;


import me.preggers.senkiplugin.SenkiPlugin;
import me.preggers.senkiplugin.system.PlayerManager;
import me.preggers.senkiplugin.system.SenkiArenaClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerInteractListener implements Listener {
    private final SenkiPlugin plugin;

    public PlayerInteractListener(SenkiPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerManager manager = plugin.getPlayerManager(player.getName());
        if (manager == null)
            return ;
        ItemStack item = e.getItem();
        if (item != null) {

            if (item.getType() == Material.WITHER_ROSE) {
                Random rng = new Random();
                SenkiArenaClass gunClass = SenkiArenaClass.SHOTGUN;
                int res = rng.nextInt(3);
                switch(res)
                {
                    case 0:
                        player.sendMessage(ChatColor.GOLD + "You have chosen the "
                                + ChatColor.BLUE + "Shotgun" + ChatColor.GOLD + " class.");
                        break;
                    case 1:
                        gunClass = SenkiArenaClass.RIFLE;
                        player.sendMessage(ChatColor.GOLD + "You have chosen the "
                                + ChatColor.BLUE + "Rifle" + ChatColor.GOLD + " class.");
                        break;
                    case 2:
                        gunClass = SenkiArenaClass.ASSAULT_RIFLE;
                        player.sendMessage(ChatColor.GOLD + "You have chosen the "
                                + ChatColor.BLUE + "Assault Rifle" + ChatColor.GOLD + " class.");
                        break;
                }
                manager.playSenki(gunClass);
                player.playSound((Entity) player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }
            if (item.getType() == Material.CORNFLOWER) {
                manager.playSenki(SenkiArenaClass.ASSAULT_RIFLE);
                player.sendMessage(ChatColor.GOLD + "You have chosen the "
                        + ChatColor.BLUE + "Assault Rifle" + ChatColor.GOLD + " class.");
                player.playSound((Entity) player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }
            if (item.getType() == Material.ALLIUM) {
                manager.playSenki(SenkiArenaClass.RIFLE);
                player.sendMessage(ChatColor.GOLD + "You have chosen the "
                        + ChatColor.BLUE + "Rifle" + ChatColor.GOLD + " class.");
                player.playSound((Entity) player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }
            if (item.getType() == Material.BLUE_ORCHID) {
                manager.playSenki(SenkiArenaClass.SHOTGUN);
                player.sendMessage(ChatColor.GOLD + "You have chosen the "
                        + ChatColor.BLUE + "Shotgun" + ChatColor.GOLD + " class.");
                player.playSound((Entity) player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            }
        }
    }



}

