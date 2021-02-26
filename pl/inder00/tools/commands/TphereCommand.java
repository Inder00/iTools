package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class TphereCommand implements CommandExecutor {

	Tools plugin;
	
	public TphereCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("tphere").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.tphere")){
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$tphere);
				return false;
				
			} else if(args.length == 1){
				
				Player a = Bukkit.getPlayer(args[0]);
				if(a == null){
					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
				}
				Location loc = p.getLocation();
				a.teleport(loc);
				a.sendMessage(cfg.teleported$tp.replace("{X}", ""+loc.getBlockX()).replace("{Y}", ""+loc.getBlockY()).replace("{Z}", ""+loc.getBlockZ()));
				p.sendMessage(cfg.player$teleported$tphere.replace("{PLAYER}", a.getName()));
				return false;
				
			} else {
				
				p.sendMessage(cfg.incorrect$use$tphere);
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
