package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class ListCommand implements CommandExecutor {

	Tools plugin;
	
	public ListCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("list").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		
		Config cfg = Config.getInst();
		
		if(sender.hasPermission("tools.list")){
			
			int max = Bukkit.getMaxPlayers();
			int online = Bukkit.getOnlinePlayers().size();
			
			sender.sendMessage(cfg.listCommand.replace("{ONLINE}", online+"").replace("{MAXONLINE}", ""+max));
			return false;
			
		} else {
			sender.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
