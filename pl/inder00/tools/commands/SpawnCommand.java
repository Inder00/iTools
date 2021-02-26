package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Teleport;
import pl.inder00.tools.files.Config;

public class SpawnCommand implements CommandExecutor {

	Tools plugin;
	
	public SpawnCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("spawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.spawn")) {
			
			if(p.hasPermission("tools.spawn.bypass")) {
				new Teleport(p,p.getWorld().getSpawnLocation(),0);
				return false;
			} else {
				p.sendMessage(cfg.cooldown$teleport$spawnComamnd);
				new Teleport(p,p.getWorld().getSpawnLocation(),cfg.teleport$delay,true);
				return false;
			}
			
		} else {
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
