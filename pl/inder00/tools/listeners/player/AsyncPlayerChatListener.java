package pl.inder00.tools.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.inder00.tools.basic.Chat;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.enums.ChatEnum;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.TimeUtil;
import pl.inder00.tools.utils.chat.PermissionPlugin;

public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		String message = e.getMessage();
		Config cfg = Config.getInst();

		if(p.hasPermission("tools.admin.chat")) {
			if(message.startsWith(".")) {
				message = message.replaceFirst(".", "");
				for(Player admins : Bukkit.getOnlinePlayers()) {
					if(admins.hasPermission("tools.admin.chat")) {
						admins.sendMessage(cfg.admin$chat.replace("{NICKNAME}", p.getName()).replace("{MESSAGE}", message));	
					}
				}
				e.setCancelled(true);
				return;
			}
		}

		if(message.contains("%")){
			p.sendMessage("§cSorki, ale znak % wiazal sie z bugiem, a nie wiedzialem jak go poprawic, sorki :/");
			e.setCancelled(true);
			return;
		}

		if(p.hasPermission("tools.chat.color")) {
			message = ChatColor.translateAlternateColorCodes('&', message);
			e.setMessage(message);
		}
		
		if(Chat.getStatus().equals(ChatEnum.OFF)){
			if(!p.hasPermission("tools.chat.bypass")){
				p.sendMessage(cfg.chat$current$off);
				p.sendTitle("",cfg.chat$current$off);
				e.setCancelled(true);
				return;
			}
		}
		
		if(PermissionPlugin.chat() == true){
			
			String format = PermissionPlugin.format(p.getName());
			
			if(format != null){

				e.setFormat(ChatColor.translateAlternateColorCodes('&', format).replace("{NICKNAME}", p.getDisplayName()).replace("{MESSAGE}", message.replace("Â", "")).replace("{DISPLAYNAME}", p.getDisplayName()));
				
			}
			
		}
		
		User u = UserUtil.get(p.getUniqueId());
		long now = TimeUtil.getTime();
		
		if(u.getMutetime() >= now){
			p.sendMessage(cfg.you$muted.replace("{TIME}", TimeUtil.convertTime(TimeUtil.remTime(u.getMutetime()))));
			p.sendTitle("",cfg.you$muted.replace("{TIME}", TimeUtil.convertTime(TimeUtil.remTime(u.getMutetime()))));
			e.setCancelled(true);
			return;
		}
		
		if(u.getLastSendedMessage() <= now){

			if(!p.hasPermission("tools.chat.cooldown.bypass")) {
				u.setLastSendedMessage(TimeUtil.addTime(cfg.chat$cooldown));
			}

		} else {
			p.sendTitle("",cfg.next$message.replace("{COOLDOWN}", ""+TimeUtil.outTime(TimeUtil.fromTime(u.getLastSendedMessage()))));
			p.sendMessage(cfg.next$message.replace("{COOLDOWN}", ""+TimeUtil.outTime(TimeUtil.fromTime(u.getLastSendedMessage()))));
			e.setCancelled(true);
		}
		
	}

}
