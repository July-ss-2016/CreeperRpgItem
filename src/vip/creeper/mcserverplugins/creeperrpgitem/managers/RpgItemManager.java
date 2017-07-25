package vip.creeper.mcserverplugins.creeperrpgitem.managers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.impls.RpgItemImpl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by July_ on 2017/7/21.
 */
public class RpgItemManager {
    // item_code - rpg_item_impl
    private static HashMap<String, RpgItemImpl> items = new HashMap<String, RpgItemImpl>();


    public static void registerRpgItem(final RpgItemImpl item) {
        items.put(item.getItemCode(), item);
    }

    public static RpgItemImpl getRpgItem(final String itemCode) {
        return items.get(itemCode);
    }

    public static boolean isExistsRpgItem(final String itemCode) {
        return items.containsKey(itemCode);
    }

    public static boolean isSameRpgItem(final String itemCode, ItemStack comparedItem) {
        if (!isExistsRpgItem(itemCode) || comparedItem == null) {
            return false;
        }

        ItemStack standardItem = getRpgItem(itemCode).getItemStack();
        ItemMeta standardMeta = standardItem.getItemMeta();
        ItemMeta comparedMeta = comparedItem.getItemMeta();

        if (standardMeta == null || standardMeta.getLore() == null || comparedMeta == null || comparedMeta.getLore() == null) {
            return false;
        }

        List<String> standardLore = standardMeta.getLore();
        List<String> comparedLore = comparedMeta.getLore();

        if (standardLore.size() == 0 || comparedLore.size() == 0) {
            return false;
        }

        //只比较物品代码
        return standardMeta.getLore().get(0).equals(comparedMeta.getLore().get(0));
    }

    public static String getRpgItemCode(final ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return  null;
        }

        List<String> lores = meta.getLore();

        if (lores == null || lores.size() == 0) {
            return null;
        }

        //物品代码
        return lores.get(0).replace("§7- §f代码 §b> §f", "");
    }

    public static RpgItemImpl normalItemToRpgItem(final ItemStack item) {
        return getRpgItem(getRpgItemCode(item));
    }
}
