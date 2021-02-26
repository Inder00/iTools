package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class SetSpawnCommand implements CommandExecutor {
	
	Tools plugin;
	
	public SetSpawnCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("setspawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.setspawn")) {
			
			Location loc = p.getLocation();
			
			p.sendMessage(cfg.setspawnCommand
					.replace("{WORLD}", p.getWorld().getName())
					.replace("{X}", ""+loc.getBlockX())
					.replace("{Y}", ""+loc.getBlockY())
					.replace("{Z}", ""+loc.getBlockZ())
					);
			loc.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
