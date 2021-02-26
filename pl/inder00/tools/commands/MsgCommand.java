package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;

public class MsgCommand implements CommandExecutor {

	Tools plugin;
	
	public MsgCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("msg").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.msg")){
			
			if(args.length == 0){
				 
				p.sendMessage(cfg.incorrect$use$msg);
				return false;
				
			} else {
				
				Player to = Bukkit.getPlayer(args[0]);
				
				if(to == null){

					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
					
				}
				
				String s = "";
				for(int i=1;i<args.length; i++) {
					s+= args[i]+" ";
				}
				
				if(p.equals(to)){
					p.sendMessage(cfg.error);
					return false;
				}
				
				User playerUser = UserUtil.get(p.getUniqueId());
				User toPlayerUser = UserUtil.get(to.getUniqueId());
				
				playerUser.setLastReceivedMessage(to);
				
				p.sendMessage(cfg.style$msg.replace("{TEXT}", s).replace("{FROM-PLAYER}", "Ty").replace("{TO-PLAYER}", to.getName()));
				to.sendMessage(cfg.style$msg.replace("{TEXT}", s).replace("{FROM-PLAYER}", p.getName()).replace("{TO-PLAYER}", "Ty"));
				toPlayerUser.setLastReceivedMessage(p);
				return false;
			}
			
		} else {
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
