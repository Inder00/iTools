package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.TimeUtil;

public class TpaCommand implements CommandExecutor {
	
	Tools plugin;
	
	public TpaCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("tpa").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.tpa")) {
			
			if(args.length == 0) {
				p.sendMessage(cfg.incorrect$use$tpa);
				return false;
				
			} else if(args.length > 0) {
				
				User u = UserUtil.get(p.getUniqueId());
				
				long time = TimeUtil.getTime();
				
				String a = args[0];
				
				if(a.equalsIgnoreCase(p.getName())) {
					p.sendMessage(cfg.error);
					return false;
				}
				
				if(a.equalsIgnoreCase("accept")) {
					
					if(u.getTpa() != null) {
						
						if(u.getTpa().getValidy() >= time) {
							
							if(!u.getTpa().accept(p)) {
								p.sendTitle("§eHI§fMC.PL",cfg.nothing$requests$tpa);
								return false;
							}
							
							Player b = u.getTpa().getPlayerA();
							if(b.isOnline() == false) {
								u.setTpa(null);
								p.sendTitle("",cfg.player$offline);
								p.sendMessage(cfg.player$offline);
								return false;
							}
							
							u.getTpa().teleport();
							
							
							p.sendMessage(cfg.you$accepted$request$tpa.replace("{REQUEST-PLAYER}", b.getName()));
							b.sendMessage(cfg.request$accepted$tpa.replace("{REQUEST-PLAYER}", p.getName()));
							b.sendMessage(cfg.cooldown$teleport$tpa.replace("{REQUEST-PLAYER}", p.getName()));
							u.setTpa(null);
							return false;
							
						} else {
							u.setTpa(null);
							p.sendMessage(cfg.nothing$requests$tpa);
							return false;
						}
						
					} else {
						p.sendMessage(cfg.nothing$requests$tpa);
						return false;
					}
					
				} else if(a.equalsIgnoreCase("deny")) {
					
					if(u.getTpa() != null) {
						
						if(u.getTpa().getValidy() >= time) {
							
							if(!u.getTpa().accept(p)) {
								p.sendMessage(cfg.nothing$requests$tpa);
								return false;
							}
							
							Player b = u.getTpa().getPlayerA();
							if(b.isOnline() == false) {
								u.setTpa(null);
								p.sendMessage(cfg.player$offline);
								return false;
							}
							
							u.setTpa(null);
							
							p.sendMessage(cfg.you$deny$request$tpa.replace("{REQUEST-PLAYER}", b.getName()));
							b.sendMessage(cfg.request$deny$tpa.replace("{REQUEST-PLAYER}", p.getName()));
							return false;
							
						} else {
							u.setTpa(null);
							p.sendMessage(cfg.nothing$requests$tpa);
							return false;
						}
						
					} else {
						p.sendMessage(cfg.nothing$requests$tpa);
						return false;
					}
					
				} else {
					
					Player player = Bukkit.getPlayer(a);
					if(player == null) {
						p.sendTitle("",cfg.player$offline);
						p.sendMessage(cfg.player$offline);
						return false;
					}
					UserUtil.requestTpa(p, player);
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
