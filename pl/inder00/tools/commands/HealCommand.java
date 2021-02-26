package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class HealCommand implements CommandExecutor {

	Tools plugin;
	
	public HealCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("heal").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.heal")) {
			
			p.setHealth(20.0D);
			p.setFoodLevel(20);
			p.setFireTicks(0);
			p.setFallDistance(0F);
			p.sendTitle("§eHI§fMC.PL",cfg.healCommand);
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			return false;
		}
	}

}
