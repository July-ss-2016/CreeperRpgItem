package vip.creeper.mcserverplugins.creeperrpgitem.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgA4Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private CooldownCounter flyCooldownCounter = new CooldownCounter(30);

    public RpgA4Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d御弓");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(), "§7- §e飞天大法好","§7- §eShift+右键查看详细信息"));
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

    @Override
    public void executeEvent(final Event event) {
        if (event instanceof PlayerInteractByRpgItemEvent) {
            onPlayerFlyEvent((PlayerInteractByRpgItemEvent) event);
        }
    }

    private void onPlayerFlyEvent(final PlayerInteractByRpgItemEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        long cooldown = this.flyCooldownCounter.getWaitSec(playerName);

        if (cooldown != 0) {
            MsgUtil.sendSkillCooldownMsg(player, "飞天", cooldown);
            return;
        }

        // 排除已经有飞行的玩家
        if (player.isFlying()) {
            return;
        }

        player.setFlying(true);
        MsgUtil.sendMsg(player, "你可以飞上天了~");

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> Bukkit.getScheduler().runTask(plugin, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
            player.setFlying(false);
        }), 200);

        this.flyCooldownCounter.put(playerName);
    }

}
