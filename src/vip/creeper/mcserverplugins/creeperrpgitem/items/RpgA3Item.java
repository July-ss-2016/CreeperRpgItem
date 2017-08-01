package vip.creeper.mcserverplugins.creeperrpgitem.items;

import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
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
public class RpgA3Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private CooldownCounter potionCooldownCounter = new CooldownCounter(60);

    public RpgA3Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
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

    @Override
    public void executeEvent(final Event event) {
        if (event instanceof PlayerInteractByRpgItemEvent) {
            onIncreaseDamageEvent((PlayerInteractByRpgItemEvent) event);
        }
    }

    private void onIncreaseDamageEvent(final PlayerInteractByRpgItemEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        long cooldown = this.potionCooldownCounter.getWaitSec(playerName);

        if (cooldown != 0) {
            MsgUtil.sendSkillCooldownMsg(player, "加速,力量", cooldown);
            return;
        }

        WarpEffect effect = new WarpEffect(plugin.getEffectManager());

        effect.speed = 1.5f;
        effect.particle = ParticleEffect.SMOKE_LARGE;
        effect.setLocation(player.getLocation());
        effect.start();

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));

        MsgUtil.sendMsg(player, "感觉自己被打了鸡血!");

        this.potionCooldownCounter.put(playerName);
    }
}
