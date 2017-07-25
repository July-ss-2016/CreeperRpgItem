package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by July_ on 2017/7/23.
 */
public class EntityDamageByPlayerEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled = false;
    private Player player;
    private Entity entity;
    private EntityDamageByEntityEvent entityDamageByEntityEvent;

    public EntityDamageByPlayerEvent(Player player, Entity entity, EntityDamageByEntityEvent event) {
        this.player = player;
        this.entity = entity;
        this.entityDamageByEntityEvent = event;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public EntityDamageByEntityEvent getEntityDamageByEntityEvent() {
        return this.entityDamageByEntityEvent;
    }

}
