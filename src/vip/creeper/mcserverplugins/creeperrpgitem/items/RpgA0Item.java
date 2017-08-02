package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import vip.creeper.mcserverplugins.creeperrpgitem.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.LivingEntityDamageByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by July_ on 2017/7/22.
 */
public class RpgA0Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private HashMap<Integer, String> fireballs = new HashMap<>();
    private CooldownCounter fireballSkillCooldownCounter = new CooldownCounter(30);

    public RpgA0Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.STICK);
        this.item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d燃烧棒");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(),"§7- §e火球大法好", "§7- §eShift + 右键 查看详细信息"));
        meta.spigot().setUnbreakable(true);
        this.item.setItemMeta(meta);
    }

    @Override
    public void executeEvent(final Event event) {
        if (event instanceof PlayerInteractByRpgItemEvent) {
            onShootFireballEvent((PlayerInteractByRpgItemEvent) event);
        } else if (event instanceof LivingEntityDamageByRpgItemEvent) {
            onBurningEvent((LivingEntityDamageByRpgItemEvent) event);
        } else if (event instanceof EntityDamageByEntityEvent) {
            onFireballHitEvent((EntityDamageByEntityEvent) event);
        }
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A0";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 10;
    }

    @Override
    public double getAdditionProtection() {
        return 0;
    }

    @Override
    public boolean canPlace() {
        return true;
    }

    // 右键发射火球
    private void onShootFireballEvent(final PlayerInteractByRpgItemEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        long cooldown = this.fireballSkillCooldownCounter.getWaitSec(playerName);

        if (!Util.isRightAction(event.getAction())) {
            return;
        }

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

    // 攻击燃烧
    private void onBurningEvent(final LivingEntityDamageByRpgItemEvent event) {
        LivingEntity target = event.getLivingEntity();

        target.setFireTicks(80); // 燃烧4s
    }

    // 领地保护，释放爆炸粒子
    private void onFireballHitEvent(final EntityDamageByEntityEvent event) {
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

            event.setDamage(event.getFinalDamage() * 2); // 2倍伤害
            damagerLoc.getWorld().spigot().playEffect(damagerLoc, Effect.EXPLOSION_LARGE); // 释放爆炸粒子
        }
    }
}
