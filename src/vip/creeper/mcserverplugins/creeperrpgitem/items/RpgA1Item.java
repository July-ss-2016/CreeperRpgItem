package vip.creeper.mcserverplugins.creeperrpgitem.items;

import de.slikey.effectlib.effect.ShieldEffect;
import de.slikey.effectlib.util.ParticleEffect;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.ProjectileHitByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgsystem.events.RpgMobKilledByPlayerEvent;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by July_ on 2017/7/21.
 */
public class RpgA1Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private HashMap<Integer, String> tnts = new HashMap<>();

    public RpgA1Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.BOW);
        this.item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        this.item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[ORI] §d爆炸弓");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(), "§7- §eShift+右键查看详细信息"));
        meta.spigot().setUnbreakable(true);
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A1";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 0;
    }

    @Override
    public double getAdditionProtection() {
        return 0;
    }

    @Override
    public boolean canPlace() {
        return true;
    }

    @Override
    public void executeEvent(final Event event) {
        if (event instanceof ProjectileHitByRpgItemEvent) {
            onArrowHitEvent((ProjectileHitByRpgItemEvent) event);
        } else if (event instanceof EntityDamageByEntityEvent) {
            onTntDamageEntityEvent((EntityDamageByEntityEvent) event);
        }
    }

    // 箭击中发射TNT
    private void onArrowHitEvent(final ProjectileHitByRpgItemEvent event) {
        Projectile projectile = event.getProjectile();
        Player shooter = event.getShooter();

        Location playerLocation = shooter.getLocation();
        TNTPrimed tnt = projectile.getWorld().spawn(playerLocation.add(0, 1, 0), TNTPrimed.class);

        tnt.setFuseTicks(20);
        tnt.setVelocity(shooter.getLocation().getDirection().multiply(2.0D)); // 向量
        tnt.setTicksLived(200); // 设置生命周期为10s
        projectile.remove(); // 防止被res等插件禁止而无限触发
        this.tnts.put(tnt.getEntityId(), shooter.getName());

        ShieldEffect effect = new ShieldEffect  (plugin.getEffectManager());
        effect.particle = ParticleEffect.REDSTONE;
        effect.color = Color.FUCHSIA;
        effect.duration = 200;
        effect.setLocation(projectile.getLocation());
        effect.start();
    }

    // 爆炸
    private void onTntDamageEntityEvent(final EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        int damagerId = damager.getEntityId();

        if (this.tnts.containsKey(damagerId)) {
            event.setDamage(100);
        }
    }
}
