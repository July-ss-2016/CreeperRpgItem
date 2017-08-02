package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/7/23.
 */
public class LivingEntityDamageByRpgItemEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled = false;
    private Player player;
    private LivingEntity livingEntity;
    private RpgItem rpgItem;
    private EntityDamageByEntityEvent entityDamageByEntityEvent;

    public LivingEntityDamageByRpgItemEvent(Player player, LivingEntity livingEntity, RpgItem rpgItem, EntityDamageByEntityEvent event) {
        this.player = player;
        this.livingEntity = livingEntity;
        this.rpgItem = rpgItem;
        this.entityDamageByEntityEvent = event;
    }

    public Player getPlayer() {
        return this.player;
    }

    public LivingEntity getLivingEntity() {
        return this.livingEntity;
    }

    public RpgItem getRpgItem() {
        return this.rpgItem;
    }

    public EntityDamageByEntityEvent getEntityDamageByEntityEvent() {
        return this.entityDamageByEntityEvent;
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
}
