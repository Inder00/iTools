package pl.inder00.tools.listeners.player;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.utils.TimeUtil;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(!p.hasPlayedBefore()){
			
			p.teleport(p.getWorld().getSpawnLocation());
			
		}
		
		User u = UserUtil.get(p.getUniqueId());
		if(u == null){
			u = new User(p.getUniqueId().toString(), p.getName(), TimeUtil.getTime(), TimeUtil.getTime(),  new ArrayList<String>(), 0,null);
		}
		u.setJointime(TimeUtil.getTime());
		
		}
		
	}
