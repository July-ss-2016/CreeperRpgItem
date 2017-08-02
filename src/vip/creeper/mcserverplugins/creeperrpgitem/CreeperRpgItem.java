package vip.creeper.mcserverplugins.creeperrpgitem;

import de.slikey.effectlib.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creeperrpgitem.commands.OpCommand;
import vip.creeper.mcserverplugins.creeperrpgitem.items.*;
import vip.creeper.mcserverplugins.creeperrpgitem.listeners.*;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgsystem.utils.Util;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

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
    private EffectManager effectManager;
    private RpgItemManager rpgItemManager;
    private static CreeperRpgItem instance;
    private boolean isFirstLoad;

    public void onLoad() {
        if (!isFirstLoad) {
            instance = this;
            isFirstLoad = true;
        }
    }

    public void onEnable() {
        effectManager = new EffectManager(this);
        rpgItemManager = new RpgItemManager();

        MsgUtil.info("版本 = " + Bukkit.getPluginManager().getPlugin("CreeperRpgItem").getDescription().getVersion());
        MsgUtil.info("创建时间 = " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(vip.creeper.mcserverplugins.creeperrpgitem.utils.Util.getPluginCreationDate()));
        getCommand("cri").setExecutor(new OpCommand(this));
        MsgUtil.info("命令已注册.");
        registerItems();
        MsgUtil.info("物品已注册.");
        registerListeners();
        MsgUtil.info("监听器已注册.");
        MsgUtil.info("插件初始化完毕!");
    }

    private void registerItems() {
        rpgItemManager.registerRpgItem(new RpgA0Item(this));
        rpgItemManager.registerRpgItem(new RpgA1Item(this));
        rpgItemManager.registerRpgItem(new RpgA2Item(this));
        rpgItemManager.registerRpgItem(new RpgA3Item(this));
        rpgItemManager.registerRpgItem(new RpgA4Item(this));
        rpgItemManager.registerRpgItem(new RpgA5Item(this));
        rpgItemManager.registerRpgItem(new RpgA6Item(this));
    }

    private void registerListeners() {
        PLUGIN_MANAGER.registerEvents(new ServerListener(this), this);
        PLUGIN_MANAGER.registerEvents(new CustomEventTriggerListener(this), this);
        PLUGIN_MANAGER.registerEvents(new RpgItemListener(this), this);
    }

    public EffectManager getEffectManager() {
        return effectManager;
    }

    public RpgItemManager getRpgItemManager() {
        return this.rpgItemManager;
    }

    public static CreeperRpgItem getInstance() {
        return instance;
    }
}
