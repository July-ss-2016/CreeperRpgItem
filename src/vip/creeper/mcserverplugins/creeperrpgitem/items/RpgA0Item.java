package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.impls.RpgItemImpl;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/22.
 */
public class RpgA0Item implements RpgItemImpl {
    @Override
    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d燃烧棒");
        meta.setLore(Arrays.asList(new String[] {"§7- §f代码 §b> §f" + getItemCode(), "§7- §f版本 §b> §f" + getVersion(), "§7- §e右键发射火球", "§7- §eShift+右键查看详细信息"}));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A0";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 6;
    }
}
