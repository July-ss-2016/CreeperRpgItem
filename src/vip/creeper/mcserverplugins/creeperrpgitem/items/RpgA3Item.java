package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.interfaces.IRpgItem;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgA3Item implements IRpgItem {
    private ItemStack item;

    public RpgA3Item() {
        this.item = new ItemStack(Material.CLAY_BRICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d板砖");
        meta.setLore(Arrays.asList(new String[] {"§7- §f代码 §b> §f" + getItemCode(), "§7- §e你给我把头伸过来","§7- §eShift+右键查看详细信息"}));
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A3";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 15;
    }

    @Override
    public boolean canPlace() {
        return true;
    }
}
