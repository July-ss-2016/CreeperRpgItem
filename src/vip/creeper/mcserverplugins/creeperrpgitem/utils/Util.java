package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.Settings;
import vip.creeper.mcserverplugins.creeperrpgsystem.CreeperRpgSystem;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/21.
 */
public class Util {

    public static boolean isPlayer(final CommandSender cs) {
        return (cs instanceof Player);
    }

    public static boolean isAdmin(final CommandSender cs) {
        return cs.hasPermission("CreeperRpgItem.admin");
    }

    public static boolean isCanAttackResidence(final Location loc, final String playerName) {
        ClaimedResidence res = ResidenceApi.getResidenceManager().getByLoc(loc);

        if (res != null && (!res.getPermissions().playerHas(playerName, "pvp", false) || !res.getPermissions().playerHas(playerName, "AnimalKilling", false))) {
            return false;
        }

        return true;
    }
}
