package vip.creeper.mcserverplugins.creeperrpgitem.utils;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.Settings;
import vip.creeper.mcserverplugins.creeperrpgsystem.CreeperRpgSystem;

import java.io.File;
import java.util.Date;
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

    public static Date getPluginCreationDate() {
        File file = new File(CreeperRpgItem.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        return (new Date(file.lastModified()));
    }

    public static boolean isRightAction(final Action action) {
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            return true;
        }

        return false;
    }

    public static ItemStack consumeItem(final ItemStack item) {
        if (item.getAmount() == 1) {
            item.setType(Material.AIR);
        } else {
            item.setAmount(item.getAmount() - 1);
        }

        return item;
    }
}
