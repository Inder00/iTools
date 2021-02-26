package pl.inder00.tools.basic.utils;

import java.util.ArrayList;

import org.bukkit.Material;

import pl.inder00.tools.basic.Kit;

public class KitUtil {
	
	
	private static ArrayList<Kit> kits = new ArrayList<Kit>();

	public static Kit get(String name) {
		
		for(Kit s : kits) {
			if(s.getName().equalsIgnoreCase(name)){
				return s;
			}
		}
		return null;
		
	}
	public static Kit get(Material item) {
		
		for(Kit s : kits) {
			if(s.getItemGui().equals(item)){
				return s;
			}
		}
		return null;
		
	}
	
	public static void addKit(Kit kit) {
		
		if(get(kit.getName()) == null) {
			kits.add(kit);
		}
		
	}
	public static void delKit(Kit kit) {
		
		if(get(kit.getName()) != null) {
			kits.remove(kit);
		}
		
	}
	
	public static ArrayList<Kit> getKits() {
		return kits;
	}

}
