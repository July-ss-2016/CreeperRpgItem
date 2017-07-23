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


    public static void registerItem(RpgItemImpl item) {
        items.put(item.getItemCode(), item);
    }

    public static RpgItemImpl getItem(String itemCode) {
        return items.get(itemCode);
    }

    public static boolean isExistsItem(String itemCode) {
        return items.containsKey(itemCode);
    }

    public static boolean isSameItem(final String itemCode, final ItemStack item) {
        if (!isExistsItem(itemCode) || item == null) {
            return false;
        }

        ItemStack standardItem = getItem(itemCode).getItemStack();
        ItemMeta standardMeta = standardItem.getItemMeta();
        ItemMeta comparedMeta = item.getItemMeta();

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

    public static String getItemCode(ItemStack item) {
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
}
