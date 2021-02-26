package pl.inder00.tools.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.utils.TimeUtil;

public class PlayerQuitListener implements Listener {
		
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();

		User u = UserUtil.get(p.getUniqueId());
		u.setQuitTime(TimeUtil.getTime());
		u.calc();
	}

}
