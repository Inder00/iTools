package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Kit;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.KitUtil;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.TimeUtil;
import pl.inder00.tools.utils.inventory.KitGUI;

public class KitCommand implements CommandExecutor {

	Tools plugin;
	
	public KitCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("kit").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.kit")){
			
			if(args.length == 0){
				
				new KitGUI().show(p);
				return false;
				
			} else if(args.length == 1){
				
				String a = args[0];
				
				Kit kit = KitUtil.get(a);
				User u = UserUtil.get(p.getUniqueId());
				
				if(kit == null){
					p.sendMessage(cfg.not$exist$kit);
					return false;
				}
				if(!p.hasPermission("tools.kit."+kit.getName().toLowerCase())){
					p.sendMessage(cfg.no$permissions);
					return false;
				}
				long millis = TimeUtil.getTime();
				if(millis >= u.getKitValidy(kit)){

					if(!p.hasPermission("tools.kits.bypass")){
						u.setKitValidy(kit, TimeUtil.addTime(kit.getDelay()));
					}
					p.sendMessage(cfg.you$got$kit.replace("{KITNAME}", kit.getName().toUpperCase()));
					kit.give(p);
					return false;
					
				} else {
					p.sendMessage(cfg.not$availability$kit.replace("{KITNAME}", kit.getName().toUpperCase()).replace("{TIME}", TimeUtil.convertTime(TimeUtil.remTime(u.getKitValidy(kit)))));
					return false;
				}
				
			} else {
				
				new KitGUI().show(p);
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
