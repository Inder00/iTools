package pl.inder00.tools.utils.chat;

import org.bukkit.Bukkit;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Group;
import pl.inder00.tools.basic.enums.PermissionEnum;
import pl.inder00.tools.basic.utils.GroupUtil;
import pl.inder00.tools.files.ChatConfiguration;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionPlugin {
	
	static Tools plugin;
	static ChatConfiguration chat = ChatConfiguration.getInst();
	
	
	public PermissionPlugin(Tools main){
		
		plugin = main;
		
		plugin.setPermissionEnum(PermissionEnum.NONE);
		
		if(Bukkit.getPluginManager().getPlugin("PermissionsEx") != null){
			
			plugin.setPermissionEnum(PermissionEnum.PERMISSIONSEX);
			
		}
		
	}
	
	public static boolean chat(){
		
		if(plugin.getPermissionEnum().equals(PermissionEnum.NONE) || plugin.getPermissionEnum() == null){
			return false;
		}
		return true;
		
	}
	
	public static String format(String nickname){
		
		if(plugin.getPermissionEnum().equals(PermissionEnum.PERMISSIONSEX)){
			
			PermissionUser user = PermissionsEx.getUser(nickname);
			
			if(user == null){
				return chat.default$group;
			}
			
			Group main = null;
			for(Group g : GroupUtil.getGroups()){
				if(user.inGroup(g.getName())){
					main = g;
				}
			}
			
			if(main == null){
				return chat.default$group;
			}
			return main.getFormat().replace("{GROUP}", main.getName());
			
			
			
		}
		return null;
		
	}

}
