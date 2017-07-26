package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;

/**
 * Created by July_ on 2017/7/24.
 */
public class RpgA4Listener implements Listener {
    private CreeperRpgItem plugin;
    private final String ITEM_CODE = "RPGITEM_A4";
    private CooldownCounter flyCooldownCounter = new CooldownCounter(30);

    public RpgA4Listener(final CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    // 右键飞行
    @EventHandler
    public void onPlayerFlyEvent(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        String playerName = player.getName();

        if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && RpgItemManager.isSameRpgItem(this.ITEM_CODE, player.getItemInHand())) {
            long cooldown = this.flyCooldownCounter.getWaitSec(playerName);

            if (cooldown != 0) {
                MsgUtil.sendSkillCooldownMsg(player, "飞天", cooldown);
                return;
            }

            // 排除已经有飞行的玩家
            if (player.isFlying()) {
                return;
            }

            player.setFlying(true);
            MsgUtil.sendMsg(player, "你可以飞上天了~");

            Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> Bukkit.getScheduler().runTask(plugin, () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
                player.setFlying(false);
            }), 200);

            this.flyCooldownCounter.put(playerName);
        }
    }
}
