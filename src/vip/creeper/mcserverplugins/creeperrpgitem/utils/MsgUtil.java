package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;

import java.util.logging.Logger;

/**
 * Created by July_ on 2017/7/21.
 */
public class MsgUtil {
    private static CreeperRpgItem plugin = CreeperRpgItem.getInstance();
    private static final Logger LOGGER = plugin.getLogger();
    public static final String HEAD_MSG = "§a[CreeperRpgItem] §b";
    public static final String NO_ATTACK_MSG = "§c你不能在这里攻击生物!";

    public static void sendMsg(final CommandSender cs, final String msg) {
        cs.sendMessage(HEAD_MSG + ChatColor.translateAlternateColorCodes('&',  msg));
    }

    public static void sendSkillCooldownMsg(final CommandSender cs, final String skillName, final long cooldownSec) {
        sendMsg(cs, "&c你必须等待 &e" + cooldownSec + "秒 &c才能再次使用 &e" + skillName + " §c技能!");
    }

    @SuppressWarnings("SameParameterValue")
    public static void sendSkillDamageMsg(final CommandSender target, final String damagerName, final String skillName) {
        sendMsg(target, "&e" + damagerName + " &c对你使用了 &e" + skillName + " &c技能.");
    }

    public static void info(final String msg) {
        LOGGER.info(msg);
    }

    public static void warring(final String msg) {
        LOGGER.warning(msg);
    }
}
