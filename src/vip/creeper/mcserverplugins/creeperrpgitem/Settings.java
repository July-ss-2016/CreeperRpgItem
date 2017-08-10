package vip.creeper.mcserverplugins.creeperrpgitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by July_ on 2017/7/23.
 */
public class Settings {
    private List<String> noRpgItemWorlds;

    public void setNoRpgItemWorlds(final List<String> worlds) {
        this.noRpgItemWorlds = worlds;
    }

    public List<String> getNoRpgItemWorlds() {
        return this.noRpgItemWorlds;
    }
}
