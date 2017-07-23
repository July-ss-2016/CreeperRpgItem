package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
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
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgsystem.utils.MsgUtil;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/21.
 */
public class RpgA0Listener implements Listener {
    private JavaPlugin plugin;
    private String itemCode;
    private Settings settings;
    private HashMap<Integer, String> fireballs;
    private HashMap<String, Long> cooldowns;


    public RpgA0Listener(CreeperRpgItem plugin, String itemCode) {
        this.plugin = plugin;
        this.itemCode = itemCode;
        this.settings = plugin.getSettings();
        this.fireballs = new HashMap<>();
        this.cooldowns = new HashMap<>();
    }

    //事件_交互_发射火球
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (RpgItemManager.isSameItem(itemCode, player.getItemInHand()) && (event.getAction() == Action.RIGHT_CLICK_AIR )) {
            long cooldown = (System.currentTimeMillis() - cooldowns.getOrDefault(playerName, 0L)) / 1000; //s

             if (cooldown < settings.rpgA0Cooldowns) {
                 MsgUtil.sendMsg(player, "§c你必须再等待 " + (settings.rpgA0Cooldowns - cooldown) + "秒 才能再次使用该技能!");
                 return;
             }

            Location eyeLocation = player.getEyeLocation();
            Vector eyeVector = eyeLocation.getDirection();
            Projectile projectile = (Projectile) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), EntityType.FIREBALL); // y+1

            projectile.setTicksLived(200);
            projectile.setShooter(player);
            projectile.setVelocity(eyeVector);
            fireballs.put(projectile.getEntityId(), playerName);
            cooldowns.put(playerName, System.currentTimeMillis());
        }
    }

    //攻击事件
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

        Entity damager = event.getDamager();
        Entity target = event.getEntity();
        int damagerId = damager.getEntityId();

        if (damager.getType() == EntityType.PLAYER) {
            Player player = (Player) damager;
            if (RpgItemManager.isSameItem(itemCode, player.getItemInHand())) {
                target.setFireTicks(80);
            }
        }


        //领地防御
        if (damager.getType() == EntityType.FIREBALL && fireballs.containsKey(damagerId)) {
            ClaimedResidence res = ResidenceApi.getResidenceManager().getByLoc(target.getLocation());
            String fireballPlayerName = fireballs.get(damagerId);

            if (res != null && (!res.getPermissions().playerHas(fireballPlayerName, "pvp", false) || !res.getPermissions().playerHas(fireballPlayerName, "AnimalKilling", false))) {
                event.setCancelled(true);
            }
        }

    }

}
