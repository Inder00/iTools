package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class KickCommand implements CommandExecutor {

	Tools plugin;
	
	public KickCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("kick").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.kick")){
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$kick);
				return false;
				
			} else {
				
				String powod = "Admin ma zawsze racje";
				Player g = Bukkit.getPlayer(args[0]);
				
				if(g == null){

					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
					
				}
				
				if(args.length == 1){
					
					g.kickPlayer(cfg.you$kicked.replace("{WHY}", powod).replace("%nl%", "\n").replace("{ADMIN}", p.getName()));
					
				} else {
					
					String s = "";
					for(int i=1;i<args.length; i++) {
						s+= args[i]+" ";
					}
					
					powod = s;
					
					g.kickPlayer(cfg.you$kicked.replace("{WHY}", powod).replace("%nl%", "\n").replace("{ADMIN}", p.getName()));
					
				}
				
				Bukkit.broadcastMessage(cfg.kick$boradcast$kick.replace("{NICKNAME}", g.getName()).replace("{ADMIN}", p.getName()).replace("{WHY}", powod));
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
