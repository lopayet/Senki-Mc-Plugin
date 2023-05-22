package me.preggers.senkiplugin.system;

import me.preggers.senkiplugin.SenkiPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SenkiGun {
    private final SenkiPlugin plugin;
    Player owner;
    private SenkiArenaClass gunClass;
    private int magazine;
    public final int SHOTGUN_MAG_MAX = 2;
    public final int ASSAULT_RIFLE_MAG_MAX = 40;
    public final int RIFLE_MAG_MAX = 8;
    public final int SHOTGUN_COOLDOWN = 16;
    public final int ASSAULT_RIFLE_COOLDOWN = 4;
    public final int RIFLE_COOLDOWN = 12;
    public final long RELOAD_TIME = 30L;

    public SenkiGun(SenkiPlugin plugin, Player owner) {
        this.plugin = plugin;
        this.owner = owner;
    }
    public int getMagazineMax() {
        int res = 0;
        switch(getGunClass()) {
            case SHOTGUN:
                res = SHOTGUN_MAG_MAX;
                break;
            case ASSAULT_RIFLE:
                res = ASSAULT_RIFLE_MAG_MAX;
                break;
            case RIFLE:
                res = RIFLE_MAG_MAX;
                break;
        }
        return res;
    }
    public int getGunCoolDown() {
        int res = 0;
        switch(getGunClass()) {
            case SHOTGUN:
                res = SHOTGUN_COOLDOWN;
                break;
            case ASSAULT_RIFLE:
                res = ASSAULT_RIFLE_COOLDOWN;
                break;
            case RIFLE:
                res = RIFLE_COOLDOWN;
                break;
        }
        return res;
    }
    public void fillMagazine() {
        setMagazine(getMagazineMax());
    }
    public SenkiArenaClass getGunClass() {
        return gunClass;
    }
    public void setGunClass(SenkiArenaClass gunClass) {
        this.gunClass = gunClass;
    }
    public int getMagazine() {
        return magazine;
    }
    public void setMagazine(int magazine) {
        this.magazine = magazine;
    }
    private void makeShotgunShot() {
        for (int i = 0; i < 8; i++) {
            Arrow arrow = (Arrow) owner.getWorld().spawnEntity(owner.getEyeLocation(), EntityType.ARROW);
            arrow.setCritical(true);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
            arrow.setShotFromCrossbow(true);
            Vector velocity = owner.getLocation().getDirection().multiply(6.0);
            Random rng = new Random();
            velocity.add(new Vector((rng.nextInt(100) - 50) / 200.0,
                    (rng.nextInt(100) - 50) / 200.0,
                    (rng.nextInt(100) - 50) / 200.0));
            arrow.setVelocity(velocity);
            arrow.setDamage(1.0);
            arrow.setTicksLived(1120 + (rng.nextInt(8) - 4));
            arrow.setShooter(owner);
        }
        Vector kickback = owner.getLocation().getDirection().multiply(-0.125);
        if (owner.isSneaking())
            kickback = owner.getLocation().getDirection().multiply(-0.0125);
        owner.setVelocity(owner.getVelocity().add(kickback));
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 0.8F);
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 0.8F);
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 0.8F);
    }
    private void makeAssaultRifleShot() {
        Arrow arrow = (Arrow) owner.getWorld().spawnEntity(owner.getEyeLocation(), EntityType.ARROW);
        arrow.setCritical(true);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        arrow.setShotFromCrossbow(true);
        Vector velocity = owner.getLocation().getDirection().multiply(6.0);
        Random rng = new Random();
        velocity.add(new Vector((rng.nextInt(100) - 50) / 1500.0,
                (rng.nextInt(100) - 50) / 1500.0,
                (rng.nextInt(100) - 50) / 1500.0));
        arrow.setVelocity(velocity);
        arrow.setDamage(1.1);
        arrow.setTicksLived(1120 + (rng.nextInt(8) - 4));
        arrow.setShooter(owner);
        Vector kickback = owner.getLocation().getDirection().multiply(-0.05);
        if (owner.isSneaking())
            kickback = owner.getLocation().getDirection().multiply(-0.005);
        owner.setVelocity(owner.getVelocity().add(kickback));
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 1.1F);
    }
    private void makeRifleShot() {
        Arrow arrow = (Arrow) owner.getWorld().spawnEntity(owner.getEyeLocation(), EntityType.ARROW);
        arrow.setCritical(true);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        arrow.setShotFromCrossbow(true);
        Vector velocity = owner.getLocation().getDirection().multiply(8.0);
        Random rng = new Random();
        velocity.add(new Vector((rng.nextInt(100) - 50) / 6000.0,
                (rng.nextInt(100) - 50) / 6000.0,
                (rng.nextInt(100) - 50) / 6000.0));
        arrow.setVelocity(velocity);
        arrow.setKnockbackStrength(1);
        arrow.setPierceLevel(1);
        arrow.setDamage(2.5);
        arrow.setTicksLived(1120 + (rng.nextInt(8) - 4));
        arrow.setShooter(owner);

        Vector kickback = owner.getLocation().getDirection().multiply(-0.1);
        if (owner.isSneaking())
            kickback = owner.getLocation().getDirection().multiply(-0.01);
        owner.setVelocity(owner.getVelocity().add(kickback));
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 0.9F);
        owner.getWorld().playSound(owner.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 5F, 0.9F);
    }
    public void shootGun() {
        if (getMagazine() <= 0)
            return ;
        switch (getGunClass()) {
            case SHOTGUN: {
                makeShotgunShot();
            }break;
            case ASSAULT_RIFLE: {
                makeAssaultRifleShot();
            }break;
            case RIFLE: {
                makeRifleShot();
            }break;
        }
        setMagazine(getMagazine() - 1);
        if (getMagazine() <= 0)
            reloadGun(RELOAD_TIME);
        else
            ownerNextAmmo();
        setOwnerInventory(false);
    }

    private void setOwnerShotgunInventory(boolean loaded) {
        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
        {
            CrossbowMeta meta = (CrossbowMeta) crossbow.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
            meta.addEnchant(Enchantment.MULTISHOT, 1, true);
            meta.setUnbreakable(true);
            meta.displayName(Component.text("Shotgun", TextColor.color(0, 0, 255)));
            if (loaded)
                meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
            crossbow.setItemMeta(meta);
        }
        ItemStack candle = new ItemStack(Material.BROWN_CANDLE, 1);
        { ItemMeta meta = candle.getItemMeta();
            meta.displayName(Component.text("Shell", TextColor.color(160,82,45)));
            candle.setItemMeta(meta); }

        PlayerInventory playerInventory = owner.getInventory();
        for(int i = 0; i < 9; i++)
            playerInventory.clear(i);
        int ammo = getMagazine();
        if (ammo >= 1)
            playerInventory.setItem(3, candle);
        if (ammo >= 2)
            playerInventory.setItem(5, candle);
        playerInventory.setItem(4, crossbow);
    }
    private void setOwnerAssaultRifleInventory(boolean loaded) {
        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
        {
            CrossbowMeta meta = (CrossbowMeta) crossbow.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            meta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
            meta.setUnbreakable(true);
            meta.displayName(Component.text("Assault Rifle", TextColor.color(0, 0, 255)));
            if (loaded)
                meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
            crossbow.setItemMeta(meta);
        }
        ItemStack candle = new ItemStack(Material.BLACK_CANDLE, 1);
        { ItemMeta meta = candle.getItemMeta();
            meta.displayName(Component.text("Round", TextColor.color(0, 15, 63)));
            candle.setItemMeta(meta); }
        PlayerInventory playerInventory = owner.getInventory();
        for(int i = 0; i < 9; i++)
            playerInventory.clear(i);
        int ammo = getMagazine();
        if (ammo >= 1) playerInventory.setItem(0, candle);
        if (ammo >= 6) playerInventory.setItem(1, candle);
        if (ammo >= 11) playerInventory.setItem(2, candle);
        if (ammo >= 16) playerInventory.setItem(3, candle);
        if (ammo >= 21) playerInventory.setItem(5, candle);
        if (ammo >= 26) playerInventory.setItem(6, candle);
        if (ammo >= 31) playerInventory.setItem(7, candle);
        if (ammo >= 36) playerInventory.setItem(8, candle);
        playerInventory.setItem(4, crossbow);
    }
    private void setOwnerRifleInventory(boolean loaded) {
        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
        {
            CrossbowMeta meta = (CrossbowMeta) crossbow.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
            meta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
            meta.setUnbreakable(true);
            meta.displayName(Component.text("Rifle", TextColor.color(0, 0, 255)));
            if (loaded)
                meta.addChargedProjectile(new ItemStack(Material.ARROW, 1));
            crossbow.setItemMeta(meta);
        }
        ItemStack candle = new ItemStack(Material.ORANGE_CANDLE, 1);
        { ItemMeta meta = candle.getItemMeta();
            meta.displayName(Component.text("Round", TextColor.color(255,127,0)));
            candle.setItemMeta(meta); }
        PlayerInventory playerInventory = owner.getInventory();
        for(int i = 0; i < 9; i++)
            playerInventory.clear(i);
        int ammo = getMagazine();
        if (ammo >= 1) playerInventory.setItem(0, candle);
        if (ammo >= 2) playerInventory.setItem(1, candle);
        if (ammo >= 3) playerInventory.setItem(2, candle);
        if (ammo >= 4) playerInventory.setItem(3, candle);
        if (ammo >= 5) playerInventory.setItem(5, candle);
        if (ammo >= 6) playerInventory.setItem(6, candle);
        if (ammo >= 7) playerInventory.setItem(7, candle);
        if (ammo >= 8) playerInventory.setItem(8, candle);
        playerInventory.setItem(4, crossbow);
    }
    public void setOwnerInventory(boolean loaded) {
        switch(getGunClass()) {
            case SHOTGUN:
                setOwnerShotgunInventory(loaded);
                break;
            case ASSAULT_RIFLE:
                setOwnerAssaultRifleInventory(loaded);
                break;
            case RIFLE:
                setOwnerRifleInventory(loaded);
                break;
        }
    }
    public void ownerNextAmmo() {
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManager manager = plugin.getPlayerManager(owner.getName());
                if (manager == null || manager.isInLobby())
                    return ;
                setOwnerInventory(true);
            }
        }.runTaskLater(plugin, getGunCoolDown());
    }
    public void reloadGun(long reloadTime) {
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManager manager = plugin.getPlayerManager(owner.getName());
                if (manager == null || manager.isInLobby())
                    return ;
                fillMagazine();
                setOwnerInventory(true);
            }
        }.runTaskLater(plugin, reloadTime);
    }
}
