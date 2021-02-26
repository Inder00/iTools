package pl.inder00.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class GamemodeCommand implements CommandExecutor {

	Tools plugin;
	
	public GamemodeCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("gamemode").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.gamemode")) {
			
			if(args.length == 0) {
				
				p.sendMessage(cfg.incorrect$use$gm);
				return false;
				
			} else if(args.length > 0 && args.length <= 2) {
				
				String s = args[0];
				
				if(s.equalsIgnoreCase("1") || s.equalsIgnoreCase("creative")) {
					
					GameMode gm = GameMode.CREATIVE;
					
					if(args.length > 1) {
						
						Player b = Bukkit.getPlayer(args[1]);
						
						if(b == null) {
							p.sendTitle("",cfg.player$offline);
							p.sendMessage(cfg.player$offline);
							return false;
						}
						b.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL",cfg.gamemode$changed$other$gm.replace("{GAMEMODE}", gm.name()).replace("{NICKNAME}", b.getName()));
						return false;
					} else {
						
						p.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL", cfg.gamemode$changed$gm.replace("{GAMEMODE}", gm.name()));
						return false;
						
					}
					
				} else if(s.equalsIgnoreCase("0") || s.equalsIgnoreCase("survival")) {
					
					GameMode gm = GameMode.SURVIVAL;
					
					if(args.length > 1) {
						
						Player b = Bukkit.getPlayer(args[1]);
						
						if(b == null) {
							p.sendMessage(cfg.player$offline);
							return false;
						}
						b.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL",cfg.gamemode$changed$other$gm.replace("{GAMEMODE}", gm.name()).replace("{NICKNAME}", b.getName()));
						return false;
					} else {
						
						p.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL", cfg.gamemode$changed$gm.replace("{GAMEMODE}", gm.name()));
						return false;
						
					}
					
				} else if(s.equalsIgnoreCase("2") || s.equalsIgnoreCase("adventure")) {
					
					GameMode gm = GameMode.ADVENTURE;
					
					if(args.length > 1) {
						
						Player b = Bukkit.getPlayer(args[1]);
						
						if(b == null) {
							p.sendMessage(cfg.player$offline);
							return false;
						}
						b.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL", cfg.gamemode$changed$other$gm.replace("{GAMEMODE}", gm.name()).replace("{NICKNAME}", b.getName()));
						return false;
					} else {
						
						p.setGameMode(gm);
						p.sendTitle("§eHI§fMC.PL", cfg.gamemode$changed$gm.replace("{GAMEMODE}", gm.name()));
						return false;
						
					}
					
				} else {
					p.sendMessage(cfg.incorrect$use$gm);
					return false;
				}
			} else {
				
				p.sendMessage(cfg.incorrect$use$gm);
				return false;
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
