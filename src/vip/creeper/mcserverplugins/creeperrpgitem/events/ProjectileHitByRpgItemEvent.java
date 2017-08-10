package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;

/**
 * Created by July_ on 2017/8/2.
 */
public class ProjectileHitByRpgItemEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private Player shooter;
    private Projectile projectile;
    private RpgItem rpgItem;
    private ProjectileHitEvent projectileHitEvent;

    public ProjectileHitByRpgItemEvent(final Player shooter, final Projectile projectile, final RpgItem rpgItem, final ProjectileHitEvent projectileHitEvent) {
        this.shooter = shooter;
        this.projectile = projectile;
        this.rpgItem = rpgItem;
        this.projectileHitEvent = projectileHitEvent;
    }

    public Player getShooter() {
        return this.shooter;
    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public RpgItem getRpgItem() {
        return this.rpgItem;
    }

    public ProjectileHitEvent getProjectileHitEvent() {
        return this.projectileHitEvent;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
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
