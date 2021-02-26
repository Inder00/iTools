package pl.inder00.tools;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.inder00.tools.basic.Teleport;
import pl.inder00.tools.basic.enums.PermissionEnum;
import pl.inder00.tools.basic.utils.KitUtil;
import pl.inder00.tools.basic.utils.TeleportUtil;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.utils.chat.PermissionPlugin;

public class ToolsAPI {
	
	public static void teleport(Player player, Location loc, int delay){
		
		Teleport tp = TeleportUtil.get(player);
		if(tp != null){
			tp.setDelay(delay);
		} else {
			new Teleport(player,loc,delay);
		}
		
	}
	
	public static PermissionEnum permissionPlugin(){
		return Tools.getInstance().getPermissionEnum();
	}
	
	public static void requestTeleport(Player a, Player b){
		UserUtil.requestTpa(a, b);
	}
	public static String getFormat(Player a){
		return PermissionPlugin.format(a.getName());
	}
	public static void giveKit(String a, Player b){
		KitUtil.get(a).give(b);
	}
	
}
