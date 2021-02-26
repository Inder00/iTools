package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class FlyCommand implements CommandExecutor {

	Tools plugin;
	
	public FlyCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("fly").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.fly")){
			
			Player change = p;
			
			if(args.length == 0) {
				
				if(change.isFlying() == true){
					change.setFlying(false);
					change.setAllowFlight(false);
				} else {
					change.setAllowFlight(true);
					change.setFlying(true);
				}
				
				p.sendTitle("§eHI§fMC.PL", cfg.status$changed$fly.replace("{FLY}", change.isFlying() ? "ON" : "OFF"));
				return false;
				
			} else {
				
				String s = args[0];
				
				change = Bukkit.getPlayer(s);
				
				if(change == null){
					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
				}
				
				if(change.isFlying() == true){
					change.setFlying(false);
					change.setAllowFlight(false);
				} else {
					change.setAllowFlight(true);
					change.setFlying(true);
				}
				
				p.sendTitle("§eHI§fMC.PL", cfg.status$changed$other$fly.replace("{FLY}", change.isFlying() ? "ON" : "OFF").replace("{NICKNAME}", change.getName()));
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
