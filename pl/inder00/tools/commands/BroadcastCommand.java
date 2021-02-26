package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class BroadcastCommand implements CommandExecutor {

	Tools plugin;
	
	public BroadcastCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("broadcast").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		
		Config cfg = Config.getInst();
		if(sender.hasPermission("tools.broadcast")) {
			
			if(args.length == 0) {
				sender.sendMessage(cfg.incorrect$use$broadcast);
				return false;
			}
			
			String s = "";
			for(int i=0;i<args.length; i++) {
				s+= args[i]+" ";
			}
			
			Bukkit.broadcastMessage(cfg.style$broadcast.replace("{TEXT}", s));
			return false;
			
		} else {
			sender.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
