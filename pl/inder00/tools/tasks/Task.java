package pl.inder00.tools.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Teleport;
import pl.inder00.tools.basic.utils.TeleportUtil;
import pl.inder00.tools.files.Config;

public class Task extends BukkitRunnable {

	Tools plugin;
	Config cfg;
	
	public Task(Tools plugin) {
		this.plugin = plugin;
		this.cfg = Config.getInst();
	}
	
	@Override
	public void run() {
		
		for(Teleport s : TeleportUtil.getTeleports()) {
			s.setDelay(s.getDelay()-1);
			Player player = s.getPlayer();
			if(s.getDelay() <= 0) {
				if(player.isOnline() == false) {
					TeleportUtil.delTeleport(s);
					return;
				}
				
				Location where = s.getWhere();
				if(s.isIgnoringHighestBlock() == true){
					player.teleport(where);
					player.sendTitle("",cfg.teleported);
					player.sendMessage(cfg.teleported);
					TeleportUtil.delTeleport(s);
					return;
				}
				Location tp = new Location(where.getWorld(), where.getBlockX()+0.5, s.getWhere().getWorld().getHighestBlockAt(where).getY()+1, where.getBlockZ()+0.5);
				player.teleport(tp);
				player.sendTitle("",cfg.teleported);
				player.sendMessage(cfg.teleported);
				TeleportUtil.delTeleport(s);
				return;
			} else {
				if(player.isOnline() == false) {
					TeleportUtil.delTeleport(s);
					return;
				}
				
				Location a = player.getLocation();
				Location b = s.getLocation();
				
				if(a.getBlockX() != b.getBlockX() || a.getBlockY() != b.getBlockY() || a.getBlockZ() != b.getBlockZ()) {
					TeleportUtil.delTeleport(s);
					player.sendTitle("",cfg.you$move);
					player.sendMessage(cfg.you$move);
					return;
				}
			}
		}
		
	}

}
