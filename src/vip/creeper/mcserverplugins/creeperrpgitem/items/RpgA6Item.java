package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.Arrays;

/**
 * Created by July_ on 2017/8/3.
 */
public class RpgA6Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;

    public RpgA6Item(CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.POISONOUS_POTATO);
        this.item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d巨雷");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(), "§7- §e尝尝我的炸弹!","§7- §eShift + 右键 查看详细信息"));
        this.item.setItemMeta(meta);

    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A6";
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
    public double getAdditionProtection() {
        return 0;
    }

    @Override
    public boolean canPlace() {
        return false;
    }

    @Override
    public void executeEvent(Event event) {
        if (event instanceof PlayerInteractByRpgItemEvent) {
            onReleaseTntEvent((PlayerInteractByRpgItemEvent) event);
        }
    }

    public void onReleaseTntEvent(PlayerInteractByRpgItemEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            player.setItemInHand(Util.consumeItem(player.getItemInHand()));

            for (int i = 0; i < 6; i++) {
                player.getWorld().spawnEntity(event.getPlayerInteractEvent().getClickedBlock().getLocation(), EntityType.PRIMED_TNT);
            }
        }
    }
}
