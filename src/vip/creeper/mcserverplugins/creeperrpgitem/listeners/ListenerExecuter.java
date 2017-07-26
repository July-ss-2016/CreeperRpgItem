package vip.creeper.mcserverplugins.creeperrpgitem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerDamageLivingEntityByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.interfaces.IRpgListener;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/25.
 */
public class ListenerExecuter {
    private HashMap<String, IRpgListener> listeners;
    private CreeperRpgItem plugin;

    public ListenerExecuter(CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.listeners = new HashMap<>();
    }

    public void registerListener(String itemCode, IRpgListener listener) {
        listeners.put(itemCode, listener);
    }

    @EventHandler
    public void onPlayerInteractByRpgItemEvent(PlayerInteractByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    @EventHandler
    public void onPlayerDamageLivingEntityByRpgItemEvent(PlayerDamageLivingEntityByRpgItemEvent event) {
        dealEvent(event.getRpgItem().getItemCode(), event);
    }

    private void dealEvent(String itemCode, Event event) {
        if (listeners.containsKey(itemCode)) {
            listeners.get(itemCode).executeEvent(event);
        }
    }
}

