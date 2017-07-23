package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by July_ on 2017/7/21.
 */
public class Util {

    public static boolean isPlayer(CommandSender cs) {
        return (cs instanceof Player);
    }

    public static boolean isAdmin(CommandSender cs) {
        return cs.hasPermission("cri.admin");
    }
}
