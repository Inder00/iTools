package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class WorkbenchCommand implements CommandExecutor {

	Tools plugin;

	public WorkbenchCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("workbench").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}

		Player p = (Player) sender;
		Config cfg = Config.getInst();

		if(p.hasPermission("tools.workbench")) {
			p.openWorkbench(p.getLocation(), true);
			return false;
		} else {
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
