package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
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
    public void onIncreaseDamageEvent(final EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();

        if (entity.getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) entity;
        ItemStack handItem = player.getItemInHand();
        RpgItem rpgItem = PLUGIN.getRpgItemManager().normalItemToRpgItem(handItem);

        if (rpgItem == null) {
            return;
        }

        event.setDamage(event.getFinalDamage() + rpgItem.getAdditionDamage());
    }

    // 现禁止不能放置的物品放置
    @EventHandler
    public void onAntiCannotPlaceBloackEvent(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        RpgItem rpgItem = PLUGIN.getRpgItemManager().normalItemToRpgItem(event.getItemInHand());

        if (rpgItem == null) {
            return;
        }

        if (rpgItem.canPlace() == false) {
            event.setCancelled(true);
            MsgUtil.sendMsg(player, "§c该物品不允许被放置!");
        }
    }

}
