package pl.inder00.tools.basic.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Warp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WarpsUtil {
	
	private static ArrayList<Warp> warps = new ArrayList<Warp>();

	public static Warp get(String name) {
		
		for(Warp s : warps) {
			if(s.getName().equalsIgnoreCase(name)){
				return s;
			}
		}
		return null;
		
	}
	
	public static void addWarp(Warp warp) {
		
		if(get(warp.getName()) == null) {
			warps.add(warp);
		}
		
	}
	public static void delWarp(Warp warp) {
		
		if(get(warp.getName()) != null) {
			warps.remove(warp);
			File conf = new File(Tools.getInstance().warps, warp.getName()+".yml");
			if(conf.exists()){
				conf.delete();
			}
		}
		
	}
	
	public static ArrayList<Warp> getWarps() {
		return warps;
	}
	
	public static void load(){		
		for(File f : Tools.getInstance().warps.listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			String name = yml.getString("name");
			Location location = new Location(Bukkit.getWorld(yml.getString("world")), yml.getInt("x"), yml.getInt("y"), yml.getInt("z"));
			new Warp(name,location);
		}
		
	}
	
	public static void save(){
		for(Warp u : warps){
			File f = new File(Tools.getInstance().warps, u.getName() + ".yml");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("name", u.getName());
			yml.set("world", u.getLocation().getWorld().getName());
			yml.set("x", u.getLocation().getBlockX());
			yml.set("y", u.getLocation().getBlockY());
			yml.set("z", u.getLocation().getBlockZ());
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
