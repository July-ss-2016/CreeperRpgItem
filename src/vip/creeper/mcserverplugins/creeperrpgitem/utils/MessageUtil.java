package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by July_ on 2017/7/21.
 */
public class MessageUtil {
    public static final String HEAD_MSG = "§a[CreeperRpgItem] §b";
    public static void sendMsg(CommandSender cs, String msg) {
        cs.sendMessage(HEAD_MSG + ChatColor.translateAlternateColorCodes('&',  msg));
    }
}
