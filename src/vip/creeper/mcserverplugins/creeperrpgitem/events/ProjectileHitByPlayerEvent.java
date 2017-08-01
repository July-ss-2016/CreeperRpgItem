package vip.creeper.mcserverplugins.creeperrpgitem.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Created by July_ on 2017/8/2.
 */
public class ProjectileHitByPlayerEvent extends Event implements Cancellable {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private Player shooter;
    private Projectile projectile;
    private ProjectileHitEvent projectileHitEvent;

    public ProjectileHitByPlayerEvent(Player shooter, Projectile projectile, ProjectileHitEvent projectileHitEvent) {
        this.shooter = shooter;
        this.projectile = projectile;
        this.projectileHitEvent = projectileHitEvent;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
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

    public Player getShooter() {
        return this.shooter;
    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public ProjectileHitEvent getProjectileHitEvent() {
        return this.projectileHitEvent;
    }
}
