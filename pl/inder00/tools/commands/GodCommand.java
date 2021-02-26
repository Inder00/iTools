package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;

public class GodCommand implements CommandExecutor {

	Tools plugin;
	
	public GodCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("god").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.god")) {
			
			User u = UserUtil.get(p.getUniqueId());
			
			if(u == null) {
				p.sendMessage(cfg.error);
				return false;
			}
			
			if(u.getGodMode() == false) {
				u.setGodMode(true);
			} else {
				u.setGodMode(false);
			}
			
			p.sendMessage(cfg.godCommand.replace("{STATUS}", u.getGodMode() == false ? "OFF" : "ON"));
			p.sendTitle("",cfg.godCommand.replace("{STATUS}", u.getGodMode() == false ? "OFF" : "ON"));
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
