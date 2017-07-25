package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by July_ on 2017/7/21.
 */
public class MsgUtil {
    public static final String HEAD_MSG = "§a[CreeperRpgItem] §b";
    public static final String NO_ATTACK_MSG = "§c你不能在这里攻击生物!";

    public static void sendMsg(final CommandSender cs, final String msg) {
        cs.sendMessage(HEAD_MSG + ChatColor.translateAlternateColorCodes('&',  msg));
    }

    public static void sendSkillCooldownMsg(final CommandSender cs, final String skillName, final long cooldownSec) {
        sendMsg(cs, "&c你必须等待 &e" + cooldownSec + "秒 &c才能再次使用 &e" + skillName + " §c技能!");
    }

    public static void sendSkillDamageMsg(final CommandSender target, final String damagerName, final String skillName) {
        sendMsg(target, damagerName + " 对你使用了 &e" + skillName + " &c技能.");
    }
}
