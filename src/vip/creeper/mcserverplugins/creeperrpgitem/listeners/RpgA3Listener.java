package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/24.
 */
public class RpgA3Listener implements Listener {
    private CreeperRpgItem plugin;
    private final String ITEM_CODE = "RPGITEM_A3";
    private CooldownCounter potionCooldownCounter = new CooldownCounter(60);

    public RpgA3Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 右键给予力量和速度
    @EventHandler
    public void onIncreaseDamageEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        String playerName = player.getName();

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            long cooldown = this.potionCooldownCounter.getWaitSec(playerName);

            if (cooldown != 0) {
                MsgUtil.sendSkillCooldownMsg(player, "加速,力量", cooldown);
                return;
            }

            WarpEffect effect = new WarpEffect(CreeperRpgItem.getEffectManager());

            effect.speed = 1.5f;
            effect.particle = ParticleEffect.SMOKE_LARGE;
            effect.setLocation(player.getLocation());
            effect.start();

            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));

            MsgUtil.sendMsg(player,"感觉自己被打了鸡血!");

            this.potionCooldownCounter.put(playerName);
        }

    }

}
