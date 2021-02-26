package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class KickallCommand implements CommandExecutor {

	Tools plugin;
	
	public KickallCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("kickall").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.kickall")){
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$kickall);
				return false;
				
			} else {
					
				String s = "";
				for(int i=0;i<args.length; i++) {
					s+= args[i]+" ";
				}
					
				for(Player g : Bukkit.getOnlinePlayers()){
					
					g.kickPlayer(cfg.you$kicked.replace("{WHY}", s).replace("%nl%", "\n").replace("{ADMIN}", p.getName()));
					
				}
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
