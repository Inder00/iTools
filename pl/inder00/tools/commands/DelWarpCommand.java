package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Warp;
import pl.inder00.tools.basic.utils.WarpsUtil;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.Util;

public class DelWarpCommand implements CommandExecutor {

	Tools plugin;

	public DelWarpCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("delwarp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.delwarp")) {
			
			if(args.length == 0) {
				p.sendMessage(Util.fixColor("&cPoprawne uzycie &7/delwarp <nazwa>"));
				return false;
			} else {
				
				String warp = args[0];

				Warp w = WarpsUtil.get(warp);
				if(w == null){
					p.sendMessage(Util.fixColor("&cPodany warp nie istnieje"));
					return false;
				}
				WarpsUtil.delWarp(w);
				p.sendMessage(Util.fixColor("&7Pomyslnie usunales warp &6"+warp));
				return true;

			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
