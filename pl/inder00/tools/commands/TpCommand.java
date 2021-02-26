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
import pl.inder00.tools.utils.IntegerUtil;

public class TpCommand implements CommandExecutor {
	
	Tools plugin;
	
	public TpCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("tp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.tp")) {
			
			if(args.length == 0) {
				p.sendMessage(cfg.incorrect$use$tp);
				return false;
			} else if(args.length > 0) {
				
				boolean x = IntegerUtil.isInteger(args[0]);
				
				if(args.length <= 1) {
					
					if(!x) {
						Player tp = Bukkit.getPlayer(args[0]);
						if(tp == null) {
							p.sendTitle("",cfg.player$offline);
							p.sendMessage(cfg.player$offline);
							return false;
						}
						
						Location loc = tp.getLocation();
						p.sendMessage(cfg.teleported$tp.replace("{X}", ""+loc.getBlockX()).replace("{Y}", ""+loc.getBlockY()).replace("{Z}", ""+loc.getBlockZ()));
						p.teleport(loc);
						return false;
					} else {
						p.sendMessage(cfg.incorrect$use$tp);
						return false;
					}
				} else if(args.length <= 2){
					
					if(!x && !IntegerUtil.isInteger(args[1])) {
						
						Player tp = Bukkit.getPlayer(args[0]);
						if(tp == null) {
							p.sendMessage(cfg.player$offline);
							return false;
						}
						Player tp2 = Bukkit.getPlayer(args[1]);
						if(tp2 == null) {
							p.sendMessage(cfg.player$offline);
							return false;
						}
						
						Location loc = tp2.getLocation();
						tp.teleport(loc);
						tp.sendMessage(cfg.teleported$tp.replace("{X}", ""+loc.getBlockX()).replace("{Y}", ""+loc.getBlockY()).replace("{Z}", ""+loc.getBlockZ()));
						p.sendMessage(cfg.other$teleported$tp.replace("{PLAYER-FROM}", tp.getName()).replace("{PLAYER-TO}", tp2.getName()));
						return false;
						
					} else {
						p.sendMessage(cfg.incorrect$use$tp);
						return false;
					}
					
				} else if(args.length <= 3) {
					
					boolean y = IntegerUtil.isInteger(args[1]);
					boolean z = IntegerUtil.isInteger(args[2]);
					
					if((!x) || (!y) || (!z)) {
						p.sendMessage(cfg.incorrect$use$tp);
						return false;
					}
					
					Location loc = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					
					p.sendMessage(cfg.teleported$tp.replace("{X}", ""+loc.getBlockX()).replace("{Y}", ""+loc.getBlockY()).replace("{Z}", ""+loc.getBlockZ()));
					p.teleport(loc);
					return false;
				} else {
					p.sendMessage(cfg.incorrect$use$tp);
					return false;
				}
			}
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
