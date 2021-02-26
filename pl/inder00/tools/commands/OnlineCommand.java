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
import pl.inder00.tools.utils.TimeUtil;

public class OnlineCommand implements CommandExecutor {

	Tools plugin;
	
	public OnlineCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("online").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.online")){
			
			User u = UserUtil.get(p.getUniqueId());
			int online = (u.getOnline())+TimeUtil.sumTime(u.getJointime(), TimeUtil.getTime());
			
			p.sendMessage(cfg.onlineComamnd.replace("{TIME}", TimeUtil.convertTime(online)));
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
