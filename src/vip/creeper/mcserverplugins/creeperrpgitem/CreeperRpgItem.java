package vip.creeper.mcserverplugins.creeperrpgitem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creeperrpgitem.commands.OpCommand;
import vip.creeper.mcserverplugins.creeperrpgitem.items.RpgA0Item;
import vip.creeper.mcserverplugins.creeperrpgitem.items.RpgA1tem;
import vip.creeper.mcserverplugins.creeperrpgitem.listeners.BaseListener;
import vip.creeper.mcserverplugins.creeperrpgitem.listeners.RpgA1Listener;
import vip.creeper.mcserverplugins.creeperrpgitem.listeners.RpgA0Listener;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;

/**
 * Created by July_ on 2017/7/21.
 */
public class CreeperRpgItem extends JavaPlugin {
    private final PluginManager PLUGIN_MANAGER = Bukkit.getPluginManager();
    private Settings settings;
    private static CreeperRpgItem instance;

    public void onEnable() {
        instance = this;
        settings = new Settings();
        getCommand("cri").setExecutor(new OpCommand());
        registerItems();
        registerListeners();
    }

    private void registerItems() {
        RpgItemManager.registerItem(new RpgA0Item());
        RpgItemManager.registerItem(new RpgA1tem());
    }

    private void registerListeners() {
        PLUGIN_MANAGER.registerEvents(new BaseListener(), this);
        PLUGIN_MANAGER.registerEvents(new RpgA0Listener(this, "RPGITEM_A0"), this);
        PLUGIN_MANAGER.registerEvents(new RpgA1Listener(this, "RPGITEM_A1"), this);
    }

    public Settings getSettings() {
        return this.settings;
    }
}
