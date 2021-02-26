package pl.inder00.tools.basic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.inder00.tools.basic.enums.ChatEnum;

public class Chat {
	
	private static ChatEnum status = ChatEnum.ON;
	
	public static void enableChat(){
		
		status = ChatEnum.ON;
		
	}
	
	public static void disableChat(){
		
		status = ChatEnum.OFF;
		
	}
	
	public static void clearChat(){
		
		for(int i = 0; i<101;i++){
			
			for(Player player : Bukkit.getOnlinePlayers()){
				
				player.sendMessage(" ");
				
			}
			
		}
		
	}

	public static ChatEnum getStatus() {
		return status;
	}

}
