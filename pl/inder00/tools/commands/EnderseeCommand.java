package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class EnderseeCommand implements CommandExecutor {
	
	Tools plugin;
	
	public EnderseeCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("endersee").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.endersee")) {
			
			if(args.length == 0) {
				p.sendMessage(cfg.incorrect$use$endersee);
				return false;
			} else {
				
				Player playertosee = Bukkit.getPlayer(args[0]);
				
				if(playertosee == null) {
					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
				}
				p.openInventory(playertosee.getEnderChest());
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
