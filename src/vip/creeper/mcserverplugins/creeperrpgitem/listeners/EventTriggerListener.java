package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.EntityDamageByPlayerEvent;

/**
 * Created by July_ on 2017/7/24.
 */
public class EventTriggerListener {

    // _触发事件_EntityDamageByPlayerEvent
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Bukkit.getPluginManager().callEvent(new EntityDamageByPlayerEvent((Player) event.getDamager(), event.getEntity(), event));
        }
    }
}
