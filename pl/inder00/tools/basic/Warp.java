package pl.inder00.tools.basic;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.inder00.tools.basic.utils.WarpsUtil;
import pl.inder00.tools.files.Config;

public class Warp {

    private String name;
    private Location location;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
        WarpsUtil.addWarp(this);
    }

    public void teleport(Player player){
        new Teleport(player, this.location, Config.getInst().teleport$delay,true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
