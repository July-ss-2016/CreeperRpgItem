package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgA5Listener implements Listener {
    private CreeperRpgItem plugin;
    private final String ITEM_CODE = "RPGITEM_A5";
    private CooldownCounter healCooldownCounter = new CooldownCounter(30);

    public RpgA5Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 实现右键提升血量
    @EventHandler
    public void onPlayerIncreastHealthEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        Action action = event.getAction();

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            long cooldown = healCooldownCounter.getWaitSec(playerName);

            if (cooldown != 0) {
                MsgUtil.sendSkillCooldownMsg(player, "治疗", cooldown);
                return;
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 160, 1));
            MsgUtil.sendMsg(player, "你的生命值已提升~");

            healCooldownCounter.put(playerName);
        }


    }
}
