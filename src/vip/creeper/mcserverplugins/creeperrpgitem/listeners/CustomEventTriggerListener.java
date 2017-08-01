package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerDamageLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/7/24.
 */
public class CustomEventTriggerListener implements Listener {
    private CreeperRpgItem plugin;

    public CustomEventTriggerListener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    //_触发事件_PlayerInteractByRpgItemEvent
    @EventHandler
    public void onPlayerInteractEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = this.plugin.getRpgItemManager().normalItemToRpgItem(player.getItemInHand());

        if (rpgItem != null) {
            Bukkit.getPluginManager().callEvent(new PlayerInteractByRpgItemEvent(rpgItem, player, event));
        }
    }

    //_触发事件_PlayerDamageLivingEntityByRpgItemEvent
    @EventHandler
    public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (damager.getType() != EntityType.PLAYER || !(target instanceof LivingEntity)) {
            return;
        }

        Player playerDamager = (Player) damager;
        RpgItem rpgItem = this.plugin.getRpgItemManager().normalItemToRpgItem(playerDamager.getItemInHand());
        LivingEntity livingEntity = (LivingEntity) event.getEntity();

        if (rpgItem == null) {
            return;
        }

        Bukkit.getPluginManager().callEvent(new PlayerDamageLivingEntityByRpgItemEvent(playerDamager, livingEntity, rpgItem, event));



    }
}