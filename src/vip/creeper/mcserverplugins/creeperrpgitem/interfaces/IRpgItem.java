package vip.creeper.mcserverplugins.creeperrpgitem.interfaces;

import org.bukkit.inventory.ItemStack;

/**
 * Created by July_ on 2017/7/21.
 */
public interface IRpgItem {
    ItemStack getItemStack();

    String getItemCode();

    String getVersion();

    double getAdditionDamage();

    boolean canPlace();
}
