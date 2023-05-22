package me.preggers.senkiplugin.system;
import me.preggers.senkiplugin.SenkiPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class PlayerManager {
    private final SenkiPlugin plugin;
    private Player player;
    private boolean inLobby;
    private boolean doubleJumpLocked;
    private final long DOUBLE_JUMP_COOLDOWN = 60L;
    private SenkiGun senkiGun;
    public PlayerManager(SenkiPlugin plugin, Player p) {
        this.plugin = plugin;
        player = p;
        senkiGun = new SenkiGun(plugin, p);
        this.sendToLobby();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        this.player = p;
        this.senkiGun.owner = p;
    }
    public boolean isInLobby() {
        return inLobby;
    }

    public boolean canDoubleJump() {
        return (!isInLobby() && !doubleJumpLocked);
    }
    public void setDoubleJumpLocked(boolean locked) {
        player.setAllowFlight(!locked);
        doubleJumpLocked = locked;
    }
    public void doubleJump() {
        if (!canDoubleJump())
            return ;
        setDoubleJumpLocked(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManager manager = plugin.getPlayerManager(player.getName());
                if (manager == null && !isInLobby())
                    return ;
                setDoubleJumpLocked(false);
            }
        }.runTaskLater(plugin, DOUBLE_JUMP_COOLDOWN);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP,
                SoundCategory.PLAYERS, 1F, 0.8F);
        Vector velocity = player.getLocation().getDirection().multiply(1.0);
        velocity.setY(1.0);
        player.setVelocity(velocity);

    }

    public void playSenki(SenkiArenaClass gunClass) {
        if (!this.inLobby)
            return ;
        Random rng = new Random();
        Location start = new Location(Bukkit.getWorld("world"),
                0.0 + (rng.nextInt(200) - 100),
                200.0,
                0.0 + (rng.nextInt(200) - 100),
                0, 0);
        player.teleport(start);
        this.inLobby = false;
        senkiGun.owner = this.player;
        senkiGun.owner.addPotionEffect(
                new PotionEffect(PotionEffectType.NIGHT_VISION,
                        20000000, 0, true, false));
        senkiGun.owner.addPotionEffect(
                new PotionEffect(PotionEffectType.SATURATION,
                        20000000, 0, true, false));
        senkiGun.owner.addPotionEffect(
                new PotionEffect(PotionEffectType.REGENERATION,
                        20000000, 0, true, false));
        senkiGun.owner.addPotionEffect(
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,
                        300, 4, true, false));
        senkiGun.owner.addPotionEffect(
                new PotionEffect(PotionEffectType.SLOW_FALLING,
                        100, 0, true, false));
        senkiGun.setGunClass(gunClass);
        senkiGun.setMagazine(0);
        senkiGun.setOwnerInventory(false);
        this.setSenkiArmor();
        setDoubleJumpLocked(false);
        senkiGun.reloadGun(8 * 20);
    }
    public void setInLobby() {
        this.inLobby = true;
    }
    public void sendToLobby() {
        player.teleport(SenkiPlugin.getLobbyLocation());
        this.setChoosingInventory();
        this.setInLobby();
    }
    public SenkiGun getSenkiGun() {
        return senkiGun;
    }

    public void setChoosingInventory() {
        player.getInventory().clear();
        ItemStack flower = new ItemStack(Material.CORNFLOWER, 1);
        ItemMeta meta = flower.getItemMeta();
        meta.displayName(Component.text("Assault Rifle", TextColor.color(0, 0, 255)));
        flower.setItemMeta(meta);
        player.getInventory().setItem(0, flower);

        flower.setType(Material.BLUE_ORCHID);
        meta.displayName(Component.text("Shotgun", TextColor.color(0, 0, 255)));
        flower.setItemMeta(meta);
        player.getInventory().setItem(1, flower);

        flower.setType(Material.ALLIUM);
        meta.displayName(Component.text("Rifle", TextColor.color(0, 0, 255)));
        flower.setItemMeta(meta);
        player.getInventory().setItem(2, flower);

        flower.setType(Material.WITHER_ROSE);
        meta.displayName(Component.text("Random", TextColor.color(0, 0, 255)));
        flower.setItemMeta(meta);
        player.getInventory().setItem(3, flower);
    }

    public void setSenkiArmor() {
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
        { ItemMeta meta = boots.getItemMeta();
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
            meta.addEnchant(Enchantment.PROTECTION_FALL, 3, true);
            meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
            boots.setItemMeta(meta); }
        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        { ItemMeta meta = leggings.getItemMeta();
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
            leggings.setItemMeta(meta); }
        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        { ItemMeta meta = chestplate.getItemMeta();
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
            chestplate.setItemMeta(meta); }
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET, 1);
        { ItemMeta meta = helmet.getItemMeta();
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
            helmet.setItemMeta(meta); }
        PlayerInventory playerInventory = player.getInventory();
        playerInventory.setBoots(boots);
        playerInventory.setLeggings(leggings);
        playerInventory.setChestplate(chestplate);
        playerInventory.setHelmet(helmet);
    }

}
