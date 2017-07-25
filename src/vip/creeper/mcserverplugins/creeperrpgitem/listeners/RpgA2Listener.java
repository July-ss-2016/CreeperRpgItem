package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.EntityDamageByPlayerEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

/**
 *  法杖
 * Created by July_ on 2017/7/23.
 */
public class RpgA2Listener implements Listener {
    private CreeperRpgItem plugin;
    private String ITEM_CODE = "RPGITEM_A2";
    private CooldownCounter throwCooldownCounter = new CooldownCounter(30);

    public RpgA2Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 右键上天
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFlyEvent(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        Location playerLoc = player.getLocation();
        Entity target = event.getRightClicked();

        if (!RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            return;
        }

        if (!(target instanceof LivingEntity)) {
            return;
        }

        long cooldown = this.throwCooldownCounter.getWaitSec(playerName);

        if (cooldown != 0) {
            MsgUtil.sendSkillCooldownMsg(player, "上天", cooldown);
            return;
        }

        // 判断是否在领地
        if (Util.isCanAttackResidence(playerLoc, playerName)) {
            target.teleport(player.getLocation().add(0, 10, 0));

            WarpEffect effect = new WarpEffect(CreeperRpgItem.getEffectManager());

            effect.speed = 1.5f;
            effect.particle = ParticleEffect.ENCHANTMENT_TABLE;
            effect.setLocation(playerLoc);
            effect.start();

            MsgUtil.sendMsg(player, "上天吧~");

            if (target instanceof  Player) {
                MsgUtil.sendSkillDamageMsg(target, playerName, "上天");
            }

            this.throwCooldownCounter.put(playerName);
        } else {
            MsgUtil.sendMsg(player, MsgUtil.NO_ATTACK_MSG);
        }
    }

    // 实现攻击致目标缓慢
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onSlowEvent(final EntityDamageByPlayerEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();
        Entity entity = event.getEntity();

        if (RpgItemManager.isSameRpgItem(this.ITEM_CODE, handItem)) {
            if (!(entity instanceof LivingEntity)) {
                return;
            }

            LivingEntity livingEntity = (LivingEntity) entity;

            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2)); // 缓慢 Lv.2 60s
        }

    }

}
