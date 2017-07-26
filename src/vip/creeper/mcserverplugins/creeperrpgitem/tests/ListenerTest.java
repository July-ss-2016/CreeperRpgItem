package vip.creeper.mcserverplugins.creeperrpgitem.tests;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;

/**
 * Created by July_ on 2017/7/24.
 */
public class ListenerTest implements Listener {

    @EventHandler
    public void ont(PlayerInteractEvent event) {
        long s = System.currentTimeMillis();
        System.out.println(RpgItemManager.getRpgItemCode(event.getPlayer().getItemInHand()));
        System.out.println(System.currentTimeMillis() - s);
    }
}
