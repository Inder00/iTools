package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.TimeUtil;

public class WhoisCommand implements CommandExecutor {
	
	Tools plugin;
	
	public WhoisCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("whois").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.whois")) {
			
			Player checking = null;
			
			if(args.length == 0) {
				checking = p;
			} else if(args.length > 0){
				
				checking = Bukkit.getPlayer(args[0]);
				
				if(checking == null) {
					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
				}
			}
			if(checking == null) {
				p.sendMessage(cfg.error);
				return false;
			}
			
			Location loc = checking.getLocation();
			
			for(String s : cfg.whoisCommand) {
				
				User u = UserUtil.get(checking.getUniqueId());
				
				s = ChatColor.translateAlternateColorCodes('&', s);
				p.sendMessage(s
						.replace("{NICKNAME}", checking.getName())
						.replace("{WORLD}", loc.getWorld().getName())
						.replace("{X}", ""+loc.getBlockX())
						.replace("{Y}", ""+loc.getBlockY())
						.replace("{Z}", ""+loc.getBlockZ())
						.replace("{IP}", p.hasPermission("tools.whois.ip") == true ? checking.getAddress().getHostName() : "***.***.***.***")
						.replace("{GAMEMODE}", checking.getGameMode().name().toUpperCase())
						.replace("{FLYING}", checking.isFlying() ? "TAK" : "NIE")
						.replace("{ONLINE-TIME}", TimeUtil.convertTime(TimeUtil.sumTime(u.getJointime())))
						.replace("{FIRST-JOIN}", TimeUtil.getDate(checking.getFirstPlayed()))
						.replace("{HEALTH}", ""+checking.getHealth())
						.replace("{FOOD}", ""+checking.getFoodLevel())
						.replace("{TOTAL-ONLINE}", TimeUtil.convertTime(u.getOnline()))
						.replace("Â", ""));
			}	
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
