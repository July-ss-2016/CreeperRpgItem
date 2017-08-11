package vip.creeper.mcserverplugins.creeperrpgitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by July_ on 2017/7/23.
 */
public class Settings {
    private List<String> noRpgItemWorlds;
    private List<String> noPvpWorlds;

    public void setNoRpgItemWorlds(final List<String> worlds) {
        this.noRpgItemWorlds = worlds;
    }

    public List<String> getNoRpgItemWorlds() {
        return this.noRpgItemWorlds;
    }

    public void setNoPvpWorlds(final List<String> worlds) {
        this.noPvpWorlds = worlds;
    }

    public List<String> getNoPvpWorlds() {
        return this.noPvpWorlds;
    }
}
