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

public class SetWarpCommand implements CommandExecutor {

	Tools plugin;

	public SetWarpCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("setwarp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.setwarp")) {
			
			if(args.length == 0) {
				p.sendMessage(Util.fixColor("&cPoprawne uzycie &7/setwarp <nazwa>"));
				return false;
			} else {
				
				String warp = args[0];

				if(WarpsUtil.get(warp) != null){
					p.sendMessage(Util.fixColor("&cPodany warp juz istnieje"));
					return false;
				}
				new Warp(warp, p.getLocation());
				WarpsUtil.save();
				p.sendMessage(Util.fixColor("&7Pomyslnie utworzyles nowy warp &6"+warp));
				return true;

			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
