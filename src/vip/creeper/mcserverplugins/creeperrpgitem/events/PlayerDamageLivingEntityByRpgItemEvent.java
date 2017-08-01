package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/7/26.
 */
public class PlayerDamageLivingEntityByRpgItemEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private Player player;
    private LivingEntity livingEntity;
    private RpgItem rpgItem;
    private EntityDamageByEntityEvent entityDamageByEntityEvent;
    private boolean cancellable;

    public PlayerDamageLivingEntityByRpgItemEvent(Player player, LivingEntity livingEntity, RpgItem rpgItem, EntityDamageByEntityEvent entityDamageByEntityEvent) {
        this.player = player;
        this.livingEntity = livingEntity;
        this.rpgItem = rpgItem;
        this.entityDamageByEntityEvent = entityDamageByEntityEvent;
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
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return this.cancellable;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancellable = b;
    }
}
