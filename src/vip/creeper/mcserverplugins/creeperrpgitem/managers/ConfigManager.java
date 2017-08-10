package vip.creeper.mcserverplugins.creeperrpgitem.managers;

import org.bukkit.Bukkit;
import vip.creeper.mcserverplugins.creeperrpgitem.Config;
import vip.creeper.mcserverplugins.creeperrpgitem.ConfigType;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by July_ on 2017/8/11.
 */
public class ConfigManager {
    private CreeperRpgItem plugin;
    private HashMap<ConfigType, Config> configs;

    public ConfigManager(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.configs = new HashMap<>();
    }

    public void loadConfig(final ConfigType configType) {
        Config config = configs.get(configType);

        if (config != null) {
            config.loadConfig();
        }
    }

    public void loadAllConfig() {
        for (Map.Entry<ConfigType, Config> entry : configs.entrySet()) {
            Bukkit.getScheduler().runTask(plugin, () -> entry.getValue().loadConfig());
        }
    }

    public void registerConfig(final ConfigType configType, final Config config) {
        configs.put(configType, config);
    }
}
