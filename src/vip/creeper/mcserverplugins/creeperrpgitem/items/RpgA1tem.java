package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.dc.pr.PRError;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.impls.RpgItemImpl;
import vip.creeper.mcserverplugins.creeperrpgsystem.CreeperRpgSystem;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/21.
 */
public class RpgA1tem implements RpgItemImpl {
    private ItemStack itemStack;

    public RpgA1tem() {
        this.itemStack = new ItemStack(Material.BOW);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§b[ORI] §d爆炸弓");
        meta.setLore(Arrays.asList(new String[] {"§7- §f代码 §b> §f" + getItemCode(), "§7- §eShift+右键查看详细信息"}));
        meta.spigot().setUnbreakable(true);
        this.itemStack.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A1";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public double getAdditionDamage() {
        return 0;
    }

    @Override
    public boolean canPlace() {
        return true;
    }

}
