package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.LivingEntityDamageByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

/**
 * Created by July_ on 2017/7/23.
 */
public class ServerListener implements Listener {
    private CreeperRpgItem plugin;

    public ServerListener(CreeperRpgItem plugin) {
        this.plugin = plugin;
    }

    //提升攻击力
    @EventHandler(ignoreCancelled = true)
    public void onIncreaseDamageEvent(final LivingEntityDamageByRpgItemEvent event) {
        event.getEntityDamageByEntityEvent().setDamage(event.getRpgItem().getAdditionDamage());
    }

    //禁止不能放置的物品放置
    @EventHandler
    public void onAntiCannotPlaceBlockEvent(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = plugin.getRpgItemManager().normalItemToRpgItem(event.getItemInHand());

        if (rpgItem != null && !rpgItem.canPlace()) {
            event.setCancelled(true);
            MsgUtil.sendMsg(player, "§c该物品不允许被放置!");
        }
    }

    //世界保护
    @EventHandler
    public void onPlayerInteractByRpgItemEvent(final PlayerInteractByRpgItemEvent event) {
        Player player = event.getPlayer();

        if (Util.isNoRpgItemWorld(player.getWorld().getName())) {
            MsgUtil.sendMsg(player, "§c你不能在这个世界使用RPG装备!");
            event.setCancelled(true);
        }
    }

    //世界保护
    @EventHandler
    public void onPlayerInteractLivingEntityByRpgItemEvent(final PlayerInteractLivingEntityByRpgItemEvent event) {
        Player player = event.getPlayer();

        if (Util.isNoRpgItemWorld(player.getWorld().getName())) {
            event.setCancelled(true);
        }
    }
}
