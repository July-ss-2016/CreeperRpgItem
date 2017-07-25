package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.Settings;
import vip.creeper.mcserverplugins.creeperrpgitem.events.EntityDamageByPlayerEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/21.
 *  燃烧棒
 */
public class RpgA0Listener implements Listener {
    private CreeperRpgItem plugin;
    private final String ITEM_CODE  = "RPGITEM_A0";
    private HashMap<Integer, String> fireballs = new HashMap<>();
    private CooldownCounter fireballSkillCooldownCounter = new CooldownCounter(30);

    public RpgA0Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 右键发射火球
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFireballEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        String playerName = player.getName();

        if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            long cooldown = this.fireballSkillCooldownCounter.getWaitSec(playerName);

             if (cooldown != 0) {
                 MsgUtil.sendSkillCooldownMsg(player, "火球", cooldown);
                 return;
             }

            Location eyeLocation = player.getEyeLocation();
            Vector eyeVector = eyeLocation.getDirection();
            Projectile projectile = (Projectile) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), EntityType.FIREBALL); // y+1

            projectile.setTicksLived(200);
            projectile.setShooter(player);
            projectile.setVelocity(eyeVector);

            this.fireballs.put(projectile.getEntityId(), playerName);
            this.fireballSkillCooldownCounter.put(playerName);
        }
    }

    // 攻击燃烧
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBurnningEvent(final EntityDamageByPlayerEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getEntity();

        if (RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            target.setFireTicks(80); // 燃烧4s
        }
    }

    // 领地保护，释放爆炸粒子
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFireballEvent(final EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();
        int damagerId = damager.getEntityId();
        Location damagerLoc = damager.getLocation();

        //领地保护
        if (damager.getType() == EntityType.FIREBALL && this.fireballs.containsKey(damagerId)) {
            String shooterPlayerName = this.fireballs.get(damagerId);

            if (!Util.isCanAttackResidence(target.getLocation(), shooterPlayerName)) {
                event.setCancelled(true);
                return;
            }

            event.setDamage(event.getFinalDamage() * 2); // 2倍上海
            damagerLoc.getWorld().spigot().playEffect(damagerLoc, Effect.EXPLOSION_LARGE); //释放爆炸粒子
        }
    }
}
