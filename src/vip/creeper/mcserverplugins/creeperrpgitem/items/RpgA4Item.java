package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.impls.RpgItemImpl;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgA4Item implements RpgItemImpl {
    private ItemStack item;

    public RpgA4Item() {
        this.item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d御弓");
        meta.setLore(Arrays.asList(new String[] {"§7- §f代码 §b> §f" + getItemCode(), "§7- §e飞天大法好","§7- §eShift+右键查看详细信息"}));
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A4";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 10;
    }

    @Override
    public boolean canPlace() {
        return true;
    }
}
