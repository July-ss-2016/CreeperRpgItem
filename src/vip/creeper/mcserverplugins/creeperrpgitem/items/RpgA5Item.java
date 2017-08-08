package vip.creeper.mcserverplugins.creeperrpgitem.items;

import de.slikey.effectlib.effect.*;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/25.
 */
public class RpgA5Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private CooldownCounter healCooldownCounter = new CooldownCounter(30);

    public RpgA5Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.BANNER);
        this.item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d肉盾");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(), "§7- §e恢复大法好","§7- §eShift+右键查看详细信息"));
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A5";
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
    public double getAdditionProtection() {
        return 0;
    }

    @Override
    public boolean canPlace() {
        return false;
    }

    @Override
    public void executeEvent(final Event event) {
        if (event instanceof PlayerInteractByRpgItemEvent) {
            onPlayerIncreaseHealthEvent((PlayerInteractByRpgItemEvent) event);
        }
    }

    private void onPlayerIncreaseHealthEvent(final PlayerInteractByRpgItemEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        long cooldown = healCooldownCounter.getWaitSec(playerName);

        if (!Util.isRightAction(event.getAction())) {
            return;
        }

        if (cooldown != 0) {
            MsgUtil.sendSkillCooldownMsg(player, "治疗", cooldown);
            return;
        }

        LoveEffect effect = new LoveEffect (plugin.getEffectManager());
        effect.setLocation(player.getLocation());
        effect.start();

        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 200, 1)); // lv.1 10s
        MsgUtil.sendMsg(player, "你的生命值已提升~");
        healCooldownCounter.put(playerName);
    }
}
