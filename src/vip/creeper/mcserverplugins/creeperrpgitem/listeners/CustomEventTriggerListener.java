package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.LivingEntityDamageByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.ProjectileHitByRpgItemEvent;

/**
 * Created by July_ on 2017/7/24.
 */
public class CustomEventTriggerListener implements Listener {
    private CreeperRpgItem plugin;

    public CustomEventTriggerListener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    //生命体被玩家使用RPG道具击中事件
    @EventHandler
    public void callLivingEntityDamageByRpgItemEvent(final EntityDamageByEntityEvent event) {
        Entity target = event.getEntity();
        Entity damager = event.getDamager();

        if (target instanceof LivingEntity && damager.getType() == EntityType.PLAYER) {
            Player player = (Player) damager;
            RpgItem rpgItem = this.plugin.getRpgItemManager().normalItemToRpgItem(player.getItemInHand());

            if (rpgItem != null) {
                Bukkit.getPluginManager().callEvent(new LivingEntityDamageByRpgItemEvent(player, (LivingEntity) target, rpgItem, event));
            }
        }
    }

    //玩家使用RPG道具交互事件
    @EventHandler
    public void callPlayerInteractByRpgItemEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = this.plugin.getRpgItemManager().normalItemToRpgItem(player.getItemInHand());

        if (rpgItem != null) {
            Bukkit.getPluginManager().callEvent(new PlayerInteractByRpgItemEvent(player, rpgItem, event));
        }
    }

    //玩家使用RPG道具交互实体事件
    @EventHandler
    public void callPlayerInteractLivingEntityByRpgItemEvent(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = this.plugin.getRpgItemManager().normalItemToRpgItem(player.getItemInHand());
        Entity entity = event.getRightClicked();

        if (entity instanceof LivingEntity && rpgItem != null) {
            Bukkit.getPluginManager().callEvent(new PlayerInteractLivingEntityByRpgItemEvent(player, (LivingEntity) entity, rpgItem, event));
        }
    }

    //玩家使用RPG道具射出抛射物事件
    @EventHandler
    public void callProjectileHitByRpgItemEvent(final ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();

        if (shooter instanceof Player) {
            Player player = (Player) shooter;
            RpgItem rpgItem = plugin.getRpgItemManager().normalItemToRpgItem(player.getItemInHand());

            if (rpgItem != null) {
                Bukkit.getPluginManager().callEvent(new ProjectileHitByRpgItemEvent(player, projectile, rpgItem, event));
            }
        }
    }
}
