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
import pl.inder00.tools.utils.IntegerUtil;
import pl.inder00.tools.utils.TimeUtil;

public class MuteCommand implements CommandExecutor {

	Tools plugin;
	
	public MuteCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("mute").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.mute")){
			
			if(args.length == 0){
				
				p.sendTitle("§eHI§fMC.PL", cfg.incorrect$use$mute);
				return false;
				
			} else if(args.length == 2){
				
				String gracz = args[0];
				String czas = args[1];
				if(!IntegerUtil.isInteger(czas)){
					
					p.sendTitle("§eHI§fMC.PL", cfg.incorrect$time$mute);
					return false;
					
				}
				int time = Integer.parseInt(czas);
				Player g = Bukkit.getPlayer(gracz);
				if(g == null){
					p.sendTitle("",cfg.player$offline);
					p.sendMessage(cfg.player$offline);
					return false;
				}
				User a = UserUtil.get(g.getUniqueId());
				if(a == null){
					p.sendMessage(cfg.error);
					return false;
				}
				if(a.getMutetime() > TimeUtil.getTime()){
					a.setMutetime(0);
					Bukkit.broadcastMessage(cfg.unmuted$mute.replace("{PLAYER}", g.getName()).replace("{ADMIN}", p.getName()));
					return false;
				}
				a.setMutetime(TimeUtil.addTime(time));
				Bukkit.broadcastMessage(cfg.muted$mute.replace("{PLAYER}", g.getName()).replace("{ADMIN}", p.getName()).replace("{TIME}", TimeUtil.convertTime(TimeUtil.remTime(a.getMutetime()))));
				return false;
			} else {
				
				p.sendMessage(cfg.incorrect$use$mute);
				return false;
				
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}
}
