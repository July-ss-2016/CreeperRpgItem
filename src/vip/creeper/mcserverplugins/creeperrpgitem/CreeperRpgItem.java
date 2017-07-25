package vip.creeper.mcserverplugins.creeperrpgitem;

import de.slikey.effectlib.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creeperrpgitem.commands.OpCommand;
import vip.creeper.mcserverplugins.creeperrpgitem.items.*;
import vip.creeper.mcserverplugins.creeperrpgitem.listeners.*;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;

/**
 * Created by July_ on 2017/7/21.
 */

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */
public class CreeperRpgItem extends JavaPlugin {
    private final PluginManager PLUGIN_MANAGER = Bukkit.getPluginManager();
    private Settings settings;
    private static CreeperRpgItem instance;
    private static EffectManager effectManager;

    public void onEnable() {
        instance = this;
        settings = new Settings();
        getCommand("cri").setExecutor(new OpCommand());
        registerItems();
        registerListeners();
        effectManager = new EffectManager(this);
    }

    private void registerItems() {
        RpgItemManager.registerRpgItem(new RpgA0Item());
        RpgItemManager.registerRpgItem(new RpgA1tem());
        RpgItemManager.registerRpgItem(new RpgA2Item());
        RpgItemManager.registerRpgItem(new RpgA3Item());
        RpgItemManager.registerRpgItem(new RpgA4Item());
        RpgItemManager.registerRpgItem(new RpgA5Item());
    }

    private void registerListeners() {
        PLUGIN_MANAGER.registerEvents(new ServerListener(), this);
        PLUGIN_MANAGER.registerEvents(new RpgA0Listener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgA1Listener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgA2Listener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgA3Listener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgA4Listener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgA5Listener(this), this);
    }

    public Settings getSettings() {
        return this.settings;
    }

    public static EffectManager getEffectManager() {
        return effectManager;
    }
}
