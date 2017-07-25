package vip.creeper.mcserverplugins.creeperrpgitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by July_ on 2017/7/23.
 */
public class Settings {
    public List<String> noSkillWorld;


    public Settings() {
        this.noSkillWorld = new ArrayList<String>();
        this.noSkillWorld.add("w_plot_0");
        this.noSkillWorld.add("w_res_0");
        this.noSkillWorld.add("world");
    }
}
