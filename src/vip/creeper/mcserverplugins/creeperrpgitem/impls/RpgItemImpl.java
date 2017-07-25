package vip.creeper.mcserverplugins.creeperrpgitem.impls;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by July_ on 2017/7/21.
 */
public interface RpgItemImpl {
    ItemStack getItemStack();

    String getItemCode();

    String getVersion();

    double getAdditionDamage();

    boolean canPlace();
}
