package pl.inder00.tools.listeners.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;

public class EntityDamageListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.isCancelled()) return;
		
		Entity ent = e.getEntity();
		
		if(ent instanceof Player) {
			
			Player p = (Player) ent;
			
			User u = UserUtil.get(p.getUniqueId());
			
			if(u != null && u.getGodMode()) {
				e.setDamage(0);
				e.setCancelled(true);
				return;
			}
			
		}
	}

}
