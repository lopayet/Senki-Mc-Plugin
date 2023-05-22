package me.preggers.senkiplugin;

import me.preggers.senkiplugin.events.*;
import me.preggers.senkiplugin.system.PlayerManager;
import me.preggers.senkiplugin.system.senkiGunEvents.SenkiEntityShootBowListener;
import me.preggers.senkiplugin.system.senkiGunEvents.SenkiInventoryClickListener;
import me.preggers.senkiplugin.system.senkiGunEvents.SenkiPlayerSwapHandItemsListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class SenkiPlugin extends JavaPlugin {

    private final HashMap<String, PlayerManager> playerManagerMap = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting up");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleFlightListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerPickUpArrowListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);

        getServer().getPluginManager().registerEvents(new SenkiEntityShootBowListener(this), this);
        getServer().getPluginManager().registerEvents(new SenkiInventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new SenkiPlayerSwapHandItemsListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down");
    }
    public PlayerManager getPlayerManager(String playerName) {
        return playerManagerMap.get(playerName);
    }
    public void updatePlayerManager(Player p) {
        PlayerManager manager = getPlayerManager(p.getName());
        if (manager == null)
            playerManagerMap.put(p.getName(), new PlayerManager(this, p));
        else {
            manager.setPlayer(p);
            manager.sendToLobby();
        }
    }
    public static Location getLobbyLocation() {
        return new Location(
                Bukkit.getWorld("world"),
                0.0, 83.0, 0.0,
                0, 0);
    }
}
