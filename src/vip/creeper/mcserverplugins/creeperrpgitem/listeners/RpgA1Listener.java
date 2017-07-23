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
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgsystem.events.RpgMobKilledByPlayerEvent;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/22.
 */
public class RpgA1Listener implements Listener {
    private String itemCode;
    private JavaPlugin plugin;
    private BukkitAPIHelper mythicMobApi = MythicMobs.inst().getAPIHelper();
    private HashMap<Integer, String> tnts = new HashMap<>();


    public RpgA1Listener(JavaPlugin plugin, String itemCode) {
        this.plugin = plugin;
        this.itemCode = itemCode;
    }

    //事件_箭击中实体_发射TNT
    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if (shooter == null || shooter.getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) shooter;

        if (RpgItemManager.isSameItem(itemCode, player.getItemInHand())) {
            Location playerLocation = player.getLocation();
            TNTPrimed tnt = projectile.getWorld().spawn(playerLocation.add(0, 1, 0), TNTPrimed.class);
            tnt.setFuseTicks(20);
            tnt.setVelocity(player.getLocation().getDirection().multiply(2.0D)); //向量
            tnt.setTicksLived(200); //设置生命周期
            event.getEntity().remove(); //防止被res等插件禁止而无限触发
            tnts.put(tnt.getEntityId(), player.getName());
        }
    }

    //_触发事件_RpgMobKillByPlayerEvent
    @EventHandler(ignoreCancelled  = true, priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        int damagerId = damager.getEntityId();

        if (tnts.containsKey(damagerId)) {
            Entity target = event.getEntity();

            if (mythicMobApi.isMythicMob(target)) {
                MythicMob mob = mythicMobApi.getMythicMobInstance(target).getType();

                if (mob.getHealth() - event.getFinalDamage() <= 0) {
                    Player player = Bukkit.getPlayer(tnts.get(damagerId));

                    if (player != null) {
                        Bukkit.getPluginManager().callEvent(new RpgMobKilledByPlayerEvent(Bukkit.getPlayer(tnts.get(damagerId)), mob));
                    }

                    //考虑一个TNT可能会伤害>1个怪的可能
                    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> Bukkit.getScheduler().runTask(plugin, () -> tnts.remove(damagerId)), 100L); //因为是hashmap所以必须转换为同步线程删除，5s后删除
                }
            }
        }

    }
}
