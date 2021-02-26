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

public class ReplyCommand implements CommandExecutor {

	Tools plugin;
	
	public ReplyCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("reply").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.reply")){
			
			User u = UserUtil.get(p.getUniqueId());
			if(u == null){
				
				p.sendMessage(cfg.error);
				return false;
				
			}
			if(u.getLastReceivedMessage() == null){
				
				p.sendMessage(cfg.nobody$reply);
				return false;
				
			}
			if(u.getLastReceivedMessage().isOnline() == false){

				p.sendTitle("",cfg.player$offline);
				p.sendMessage(cfg.player$offline);
				return false;
				
			}
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$reply);
				return false;
				
			} else {
				
				String s = "";
				for(int i=0;i<args.length; i++) {
					s+= args[i]+" ";
				}
				
				p.sendMessage(cfg.style$msg.replace("{TEXT}", s).replace("{FROM-PLAYER}", "Ty").replace("{TO-PLAYER}", u.getLastReceivedMessage().getName()));
				u.getLastReceivedMessage().sendMessage(cfg.style$msg.replace("{TEXT}", s).replace("{FROM-PLAYER}", p.getName()).replace("{TO-PLAYER}", "Ty"));
				return false;
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
