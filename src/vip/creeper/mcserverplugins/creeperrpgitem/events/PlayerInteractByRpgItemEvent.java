package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/7/26.
 */
public class PlayerInteractByRpgItemEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private RpgItem rpgItem;
    private Player player;
    private PlayerInteractEvent playerInteractEvent;
    private boolean cancellable;

    public PlayerInteractByRpgItemEvent(Player player, RpgItem rpgItem, PlayerInteractEvent playerInteractEvent) {
        this.rpgItem = rpgItem;
        this.player = player;
        this.playerInteractEvent = playerInteractEvent;
    }

    public RpgItem getRpgItem() {
        return this.rpgItem;
    }

    public Player getPlayer() {
        return this.player;
    }

    public org.bukkit.event.block.Action getAction() {
        return this.playerInteractEvent.getAction();
    }

    public PlayerInteractEvent getPlayerInteractEvent() {
        return this.playerInteractEvent;
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
