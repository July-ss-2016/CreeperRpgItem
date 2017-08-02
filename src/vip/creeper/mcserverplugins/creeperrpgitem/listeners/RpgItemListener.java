package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.LivingEntityDamageByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.ProjectileHitByRpgItemEvent;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgItemListener implements Listener {
    private CreeperRpgItem plugin;

    public RpgItemListener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLivingEntityDamageByRpgItemEvent(final LivingEntityDamageByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractByRpgItemEvent(final PlayerInteractByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractLivingEntityByRpgItemEvent(final PlayerInteractLivingEntityByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onProjectileHitByRpgItemEvent(final ProjectileHitByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent event) {
        dealEventForAllRpgItems(event);
    }

    private boolean dealEvent(String itemCode, Event event) {
        RpgItem rpgItem = plugin.getRpgItemManager().getRpgItem(itemCode);

        if (rpgItem == null) {
            return false;
        }

        rpgItem.executeEvent(event);
        return true;
    }

    private void dealEventForAllRpgItems(Event event) {
        for (RpgItem rpgItem : plugin.getRpgItemManager().getRpgItems()) {
            rpgItem.executeEvent(event);
        }
    }
}

