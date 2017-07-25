package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgsystem.events.RpgMobKilledByPlayerEvent;

import java.util.HashMap;

/**
 * 爆炸弓
 * Created by July_ on 2017/7/22.
 */
public class RpgA1Listener implements Listener {
    private CreeperRpgItem plugin;
    private final String ITEM_CODE = "RPGITEM_A1";
    private HashMap<Integer, String> tnts = new HashMap<>();
    private final BukkitAPIHelper MYTHICMOBS_API = MythicMobs.inst().getAPIHelper();


    public RpgA1Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 箭击中发射TNT
    @EventHandler(ignoreCancelled  = true, priority = EventPriority.HIGHEST)
    public void onArrowHitEvent(final ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if (shooter == null || shooter.getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) shooter;

        if (RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            Location playerLocation = player.getLocation();
            TNTPrimed tnt = projectile.getWorld().spawn(playerLocation.add(0, 1, 0), TNTPrimed.class);
            tnt.setFuseTicks(20);
            tnt.setVelocity(player.getLocation().getDirection().multiply(2.0D)); // 向量
            tnt.setTicksLived(200); // 设置生命周期为10s
            event.getEntity().remove(); // 防止被res等插件禁止而无限触发
            this.tnts.put(tnt.getEntityId(), player.getName());
        }
    }

    // 爆炸触发计分器
    @EventHandler(ignoreCancelled  = true, priority = EventPriority.HIGHEST)
    public void onTntDamageEvent(final EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager(); // 实体为TNT
        int damagerId = damager.getEntityId(); // TNT ID

        if (this.tnts.containsKey(damagerId)) {
            Entity target = event.getEntity();
            if (this.MYTHICMOBS_API.isMythicMob(target)) {
                MythicMob mob = this.MYTHICMOBS_API.getMythicMobInstance(target).getType();

                if (mob.getHealth() - event.getFinalDamage() <= 0) {
                    Player player = Bukkit.getPlayer(this.tnts.get(damagerId));

                    if (player != null) {
                        Bukkit.getPluginManager().callEvent(new RpgMobKilledByPlayerEvent(Bukkit.getPlayer(this.tnts.get(damagerId)), mob));
                    }

                    // 考虑一个TNT可能会伤害>1个怪的可能
                    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> Bukkit.getScheduler().runTask(plugin, () -> this.tnts.remove(damagerId)), 100L); // 因为是hashmap所以必须转换为同步线程删除，5s后删除
                }
            }
        }

    }
}
