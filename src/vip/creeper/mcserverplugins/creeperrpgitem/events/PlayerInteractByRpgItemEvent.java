package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.interfaces.IRpgItem;

/**
 * Created by July_ on 2017/7/26.
 */
public class PlayerInteractByRpgItemEvent extends Event implements Cancellable {
    private static HandlerList handlerList;
    private IRpgItem rpgItem;
    private Player player;
    private PlayerInteractEvent playerInteractEvent;
    private boolean cancellable;

    public PlayerInteractByRpgItemEvent(IRpgItem rpgItem, Player player, PlayerInteractEvent playerInteractEvent) {
        handlerList = new HandlerList();
        this.rpgItem = rpgItem;
        this.player = player;
        this.playerInteractEvent = playerInteractEvent;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public IRpgItem getRpgItem() {
        return this.rpgItem;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerInteractEvent getPlayerInteractEvent() {
        return this.playerInteractEvent;
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
