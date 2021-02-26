package pl.inder00.tools.basic.utils;

import java.util.ArrayList;

import pl.inder00.tools.basic.Group;

public class GroupUtil {
	
	
	private static ArrayList<Group> groups = new ArrayList<Group>();

	public static Group get(String name) {
		
		for(Group s : groups) {
			if(s.getName().equalsIgnoreCase(name)){
				return s;
			}
		}
		return null;
		
	}
	
	public static void addGroup(Group g) {
		
		if(get(g.getName()) == null) {
			groups.add(g);
		}
		
	}
	public static void delGroup(Group g) {
		
		if(get(g.getName()) != null) {
			groups.remove(g);
		}
		
	}
	
	public static ArrayList<Group> getGroups() {
		return groups;
	}

}
