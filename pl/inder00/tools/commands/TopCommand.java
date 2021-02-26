package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class TopCommand implements CommandExecutor {
	
	Tools plugin;
	
	public TopCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("top").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.top")) {
			
			Location ploc = p.getLocation();
			
			Location tp = new Location(p.getWorld(), ploc.getX(), p.getWorld().getHighestBlockAt(p.getLocation()).getY(), ploc.getZ(), ploc.getYaw(), ploc.getPitch());
			
			p.teleport(tp);
			p.sendMessage(cfg.topCommand);
			p.sendTitle("",cfg.topCommand);
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
		
	}

}
