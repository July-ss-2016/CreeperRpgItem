package vip.creeper.mcserverplugins.creeperrpgitem.configs;

import org.bukkit.configuration.file.FileConfiguration;
import vip.creeper.mcserverplugins.creeperrpgitem.Config;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.Settings;

/**
 * Created by July_ on 2017/8/11.
 */
public class PluginConfig implements Config {
    private CreeperRpgItem plugin;
    private Settings settings;

    public PluginConfig(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.settings = plugin.getSettings();
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        settings.setNoRpgItemWorlds(config.getStringList("no_rpgitem_worlds"));
        settings.setNoPvpWorlds(config.getStringList("no_pvp_worlds"));
    }
}
