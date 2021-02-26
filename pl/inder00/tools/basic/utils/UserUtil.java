package pl.inder00.tools.basic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.RequestTeleport;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.TimeUtil;

public class UserUtil {
	
	private static ArrayList<User> players = new ArrayList<User>();

	public static User get(UUID uuid) {
		
		for(User s : players) {
			if(s.getUuid().equals(uuid)) {
				return s;
			}
		}
		return null;
		
	}
	
	public static void addUser(User teleport) {
		
		if(get(teleport.getUuid()) == null) {
			players.add(teleport);
		}
		
	}
	public static void delUser(User teleport) {
		
		if(get(teleport.getUuid()) != null) {
			players.remove(teleport);
		}
		
	}
	
	public static ArrayList<User> getUsers() {
		return players;
	}
	
	public static void checkPlayers() {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			if(get(p.getUniqueId()) == null) {
				new User(p.getUniqueId().toString(), p.getName(), TimeUtil.getTime(), TimeUtil.getTime(), new ArrayList<String>(), 0,null);
			}
			
		}
		
	}
	
	public static void requestTpa(Player a, Player b) {
		
		Config cfg = Config.getInst();
		int validy = 300;
		
		if(a.isOnline() && b.isOnline()) {
			
			User u = get(a.getUniqueId());
			User g = get(b.getUniqueId());
			
			if(u != null && g != null) {
				
				a.sendMessage(cfg.request$sended$tpa.replace("{REQUEST-PLAYER}", b.getName()));
				
				for(String s : cfg.request$for$teleportation$tpa) {
					b.sendMessage(ChatColor.translateAlternateColorCodes('&', s)
							.replace("{REQUEST-PLAYER}", a.getName())
							.replace("Â", "")
							);
				}
				
				RequestTeleport tpa = new RequestTeleport(a,b,cfg.teleport$delay,validy);
				
				u.setTpa(tpa);
				g.setTpa(tpa);
				
			}
			
		}
		
	}
	
	public static void load(){		
		for(File f : Tools.getInstance().getUsers().listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			String uuid = yml.getString("uuid");
			String nick = yml.getString("nickname");
			int online = yml.getInt("time-online");
			long last$join = yml.getLong("last-join");
			long last$quit = yml.getLong("last-quit");
			List<String> kit$data = yml.getStringList("kit-data");
			String world = yml.getString("home.world");
			String x = yml.getString("home.x");
			String y = yml.getString("home.y");
			String z = yml.getString("home.z");
			Location home = null;
			if(world != null && x != null && y != null && z != null){
				home = new Location(Bukkit.getWorld(world),Integer.valueOf(x),Integer.valueOf(y),Integer.valueOf(z)).add(0.5D,0,0.5D);
			}
			new User(uuid,nick,last$quit,last$join,kit$data, online,home);
		}
		
	}
	
	public static void save(){
		for(User u : players){
			File f = new File(Tools.getInstance().getUsers(), u.getUuid() + ".yml");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("uuid", u.getUuid().toString());
			yml.set("nickname", u.getNickName());
			yml.set("time-online", u.getOnline());
			yml.set("last-join", u.getJointime());
			yml.set("last-quit", u.getQuitTime());
			yml.set("kit-data", u.outputKitData());
			if(u.getHome() != null){
				yml.set("home.world", u.getHome().getWorld().getName());
				yml.set("home.x", u.getHome().getBlockX());
				yml.set("home.y", u.getHome().getBlockY());
				yml.set("home.z", u.getHome().getBlockZ());
			}
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
