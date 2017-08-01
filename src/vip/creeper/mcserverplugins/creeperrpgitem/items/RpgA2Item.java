package vip.creeper.mcserverplugins.creeperrpgitem.items;

import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creeperrpgitem.CooldownCounter;
import vip.creeper.mcserverplugins.creeperrpgitem.CreeperRpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.RpgItem;
import vip.creeper.mcserverplugins.creeperrpgitem.events.EntityDamageByPlayerEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.events.PlayerInteractByRpgItemEvent;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.MsgUtil;
import vip.creeper.mcserverplugins.creeperrpgitem.utils.Util;

import java.util.Arrays;

/**
 * Created by July_ on 2017/7/23.
 */
public class RpgA2Item implements RpgItem {
    private CreeperRpgItem plugin;
    private ItemStack item;
    private CooldownCounter throwCooldownCounter = new CooldownCounter(30);

    public RpgA2Item(final CreeperRpgItem plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b[RI] §d法杖");
        meta.setLore(Arrays.asList("§7- §f代码 §b> §f" + getItemCode(), "§7- §e上天大法好", "§7- §eShift+右键查看详细信息"));
        this.item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public String getItemCode() {
        return "RPGITEM_A2";
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
        if (event instanceof PlayerInteractEntityEvent) {
            onFlyEvent((PlayerInteractEntityEvent) event);
        } else if (event instanceof EntityDamageByPlayerEvent) {
            onSlowEvent((EntityDamageByPlayerEvent) event);
        }
    }

    // 飞天技能
    private void onFlyEvent(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        Location playerLoc = player.getLocation();
        Entity target = event.getRightClicked();

        if (!(target instanceof LivingEntity)) {
            return;
        }

        long cooldown = this.throwCooldownCounter.getWaitSec(playerName);

        if (cooldown != 0) {
            MsgUtil.sendSkillCooldownMsg(player, "上天", cooldown);
            return;
        }

        // 判断是否在领地
        if (Util.isCanAttackResidence(playerLoc, playerName)) {
            target.teleport(player.getLocation().add(0, 10, 0));

            WarpEffect effect = new WarpEffect(plugin.getEffectManager());

            effect.speed = 1.5f;
            effect.particle = ParticleEffect.ENCHANTMENT_TABLE;
            effect.setLocation(playerLoc);
            effect.start();

            MsgUtil.sendMsg(player, "上天吧~");

            if (target instanceof  Player) {
                MsgUtil.sendSkillDamageMsg(target, playerName, "上天");
            }

            this.throwCooldownCounter.put(playerName);
        } else {
            MsgUtil.sendMsg(player, MsgUtil.NO_ATTACK_MSG);
        }
    }

    // 攻击致目标缓慢
    private void onSlowEvent(final EntityDamageByPlayerEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        LivingEntity livingEntity = (LivingEntity) entity;

        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2)); // 缓慢 Lv.2 60s

    }
}
