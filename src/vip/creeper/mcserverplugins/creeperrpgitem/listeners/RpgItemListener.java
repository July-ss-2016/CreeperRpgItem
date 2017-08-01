package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.EntityDamageByPlayerEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerDamageLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgItemListener implements Listener {
    private CreeperRpgItem plugin;

    public RpgItemListener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractByRpgItemEvent(final PlayerInteractByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDamageLivingEntityByRpgItemEvent(final PlayerDamageLivingEntityByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent event) {
        dealEventForAllRpgItems(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByPlayerEvent(final EntityDamageByPlayerEvent event) {
        dealEvent(plugin.getRpgItemManager().getRpgItemCode(event.getPlayer().getItemInHand()), event);
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

