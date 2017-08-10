package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/8/2.
 */
public class PlayerInteractLivingEntityByRpgItemEvent extends org.bukkit.event.Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private Player player;
    private LivingEntity livingEntity;
    private RpgItem rpgItem;
    private PlayerInteractEntityEvent playerInteractEntityEvent;

    public PlayerInteractLivingEntityByRpgItemEvent(final Player player, final LivingEntity livingEntity, final RpgItem rpgItem, final PlayerInteractEntityEvent playerInteractEntityEvent) {
        this.player = player;
        this.livingEntity = livingEntity;
        this.rpgItem = rpgItem;
        this.playerInteractEntityEvent = playerInteractEntityEvent;
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

    public PlayerInteractEntityEvent getPlayerInteractEntityEvent() {
        return this.playerInteractEntityEvent;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean b) {
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
