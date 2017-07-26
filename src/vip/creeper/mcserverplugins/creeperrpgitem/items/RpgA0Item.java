package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.interfaces.IRpgItem;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/22.
 */
public class RpgA0Item implements IRpgItem {
    private ItemStack item;

    public RpgA0Item() {
        this.item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d燃烧棒");
        meta.setLore(Arrays.asList(new String[] {"§7- §f代码 §b> §f" + getItemCode(),"§7- §e火球大法好", "§7- §eShift+右键查看详细信息"}));
        meta.spigot().setUnbreakable(true);
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
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
        return 10;
    }

    @Override
    public boolean canPlace() {
        return true;
    }
}
