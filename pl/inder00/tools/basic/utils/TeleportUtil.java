package pl.inder00.tools.basic.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import pl.inder00.tools.basic.Teleport;

public class TeleportUtil {
	
	private static ArrayList<Teleport> teleports = new ArrayList<Teleport>();

	public static Teleport get(Player player) {
		
		for(Teleport s : teleports) {
			if(s.getPlayer().equals(player)) {
				return s;
			}
		}
		return null;
		
	}
	public static Teleport get(UUID uuid) {
		
		for(Teleport s : teleports) {
			if(s.getPlayer().getUniqueId().equals(uuid)) {
				return s;
			}
		}
		return null;
		
	}
	
	public static void addTeleport(Teleport teleport) {
		
		if(get(teleport.getPlayer()) == null) {
			teleports.add(teleport);
		}
		
	}
	public static void delTeleport(Teleport teleport) {
		
		if(get(teleport.getPlayer()) != null) {
			teleports.remove(teleport);
		}
		
	}
	
	public static ArrayList<Teleport> getTeleports() {
		return teleports;
	}

}
