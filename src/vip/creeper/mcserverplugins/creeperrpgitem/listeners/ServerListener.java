package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.LivingEntityDamageByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;

/**
 * Created by July_ on 2017/7/23.
 */
public class ServerListener implements Listener {
    private final CreeperRpgItem PLUGIN;

    public ServerListener(CreeperRpgItem plugin) {
        this.PLUGIN = plugin;
    }

    // 提升攻击力
    @EventHandler(ignoreCancelled = true)
    public void onIncreaseDamageEvent(final LivingEntityDamageByRpgItemEvent event) {
        event.getEntityDamageByEntityEvent().setDamage(event.getRpgItem().getAdditionDamage());
    }

    // 禁止不能放置的物品放置
    @EventHandler
    public void onAntiCannotPlaceBloackEvent(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = PLUGIN.getRpgItemManager().normalItemToRpgItem(event.getItemInHand());

        if (rpgItem != null) {
            if (!rpgItem.canPlace()) {
                event.setCancelled(true);
                MsgUtil.sendMsg(player, "§c该物品不允许被放置!");
            }
        }
    }
}
