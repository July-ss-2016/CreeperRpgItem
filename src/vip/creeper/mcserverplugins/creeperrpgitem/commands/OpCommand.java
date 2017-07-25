package vip.creeper.mcserverplugins.creeperrpgitem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import vip.creeper.mcserverplugins.creeperrpgitem.managers.RpgItemManager;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

/**
 * Created by July_ on 2017/7/21.
 */
public class OpCommand implements CommandExecutor {


    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {

        if (!Util.isAdmin(cs)) {
            MsgUtil.sendMsg(cs, "&c你没有权限.");
            return true;
        }


        if (args.length == 2) {
            switch (args[0]) {
                case "get":
                    if (!Util.isPlayer(cs)) {
                        MsgUtil.sendMsg(cs, "&c该命令必须由玩家执行.");
                        return true;
                    }

                    Player player = (Player)cs;
                    PlayerInventory playerInventory = player.getInventory();

                    if (playerInventory.firstEmpty() == -1) {
                        MsgUtil.sendMsg(cs, "&c背包没有空余的空间.");
                        return true;
                    }

                    if (!RpgItemManager.isExistsRpgItem(args[1])) {
                        MsgUtil.sendMsg(cs, "&c不存在这个物品.");
                        return true;
                    }

                    playerInventory.addItem(RpgItemManager.getRpgItem(args[1]).getItemStack());
                    MsgUtil.sendMsg(cs, "已添加到背包!");
                    return true;
            }
            return false;
        }
        return false;
    }
}
