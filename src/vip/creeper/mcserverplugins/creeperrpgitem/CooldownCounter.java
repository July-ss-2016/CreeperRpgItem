package vip.creeper.mcserverplugins.creeperrpgitem;

import java.util.HashMap;

/**
 * Created by July_ on 2017/7/25.
 */
public class CooldownCounter {
    public long cooldown;
    public HashMap<String, Long> cooldowns;

    public CooldownCounter(final long cooldown) {
        this.cooldown = cooldown;
        this.cooldowns = new HashMap<>();
    }

    public long getWaitSec(final String playerName) {
        if (!cooldowns.containsKey(playerName)) {
            return 0;
        }

        long temp = this.cooldown - (System.currentTimeMillis() - cooldowns.get(playerName)) / 1000;

        return temp < 0 ? 0 : temp;
    }

    public void put(final String playerName) {
        cooldowns.put(playerName, System.currentTimeMillis());
    }
}
