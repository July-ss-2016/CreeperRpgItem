package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import vip.creeper.mcserverplugins.creeperrpgitem.impls.RpgItemImpl;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;

/**
 * Created by July_ on 2017/7/23.
 */
public class BaseListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();

        if (entity.getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) entity;
        ItemStack handItem = player.getItemInHand();
        String itemCode = RpgItemManager.getItemCode(handItem);

        if (itemCode == null) {
            return;
        }

        RpgItemImpl rpgItem = RpgItemManager.getItem(itemCode);

        event.setDamage(event.getFinalDamage() + rpgItem.getAdditionDamage());
    }
}
