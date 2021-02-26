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

public class WarpCommand implements CommandExecutor {

	Tools plugin;

	public WarpCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("warp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.warp")) {

			String warps = "brak";

			if(WarpsUtil.getWarps().size() > 0){
				warps = "";
				for(Warp w : WarpsUtil.getWarps()){
					warps += w.getName()+" ";
				}
			}

			if(args.length == 0) {
				p.sendMessage(Util.fixColor("&7Lista dostepnych warpow: &6"+warps));
				return false;
			} else {
				
				String warp = args[0];

				Warp w = WarpsUtil.get(warp);
				if(w == null){
					p.sendMessage(Util.fixColor("&cPodany warp nie istnieje"));
					return false;
				}
				w.teleport(p);
				return true;

			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
