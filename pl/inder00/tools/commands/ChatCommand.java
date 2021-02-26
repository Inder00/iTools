package pl.inder00.tools.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Chat;
import pl.inder00.tools.basic.enums.ChatEnum;
import pl.inder00.tools.files.Config;

public class ChatCommand implements CommandExecutor {

	Tools plugin;
	
	public ChatCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("chat").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.chat")){
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$chat);
				return false;
				
			} else if(args.length == 1){
				
				String a = args[0];
				
				if(a.equalsIgnoreCase("off")){
					
					ChatEnum status = Chat.getStatus();
					
					if(status.equals(ChatEnum.OFF)){
						
						p.sendMessage(cfg.current$chat.replace("{STATUS}", "OFF"));
						return false;
						
					}
					
					List<String> list = new ArrayList<String>();
					for(String e : cfg.off$chat){
						
						list.add(ChatColor.translateAlternateColorCodes('&', e.replace("Â", "")));
						
					}
					for(String g : list){
						
						Bukkit.broadcastMessage(g);
							
					}
					Chat.disableChat();
					return false;
					
				} else if(a.equalsIgnoreCase("on")){
					
					ChatEnum status = Chat.getStatus();
					
					if(status.equals(ChatEnum.ON)){
						
						p.sendMessage(cfg.current$chat.replace("{STATUS}", "ON"));
						return false;
						
					}
					
					List<String> list = new ArrayList<String>();
					for(String e : cfg.on$chat){
						
						list.add(ChatColor.translateAlternateColorCodes('&', e.replace("Â", "")));
						
					}
						
					for(String g : list){
							
						Bukkit.broadcastMessage(g);
							
					}
						
					Chat.enableChat();
					return false;
					
				} else if(a.equalsIgnoreCase("clear")){
					
					Chat.clearChat();
					List<String> list = new ArrayList<String>();
					for(String e : cfg.clear$chat){
						
						list.add(ChatColor.translateAlternateColorCodes('&', e.replace("Â", "")));
						
					}
					for(Player player : Bukkit.getOnlinePlayers()){
						
						for(String g : list){
							
							player.sendMessage(g);
							
						}
						
					}
					return false;
					
				} else {
					p.sendMessage(cfg.incorrect$use$chat);
					return false;
				}
				
			} else {
				
				p.sendMessage(cfg.incorrect$use$chat);
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
