package pl.inder00.tools.files;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.tools.Tools;

public class Config {
	
	//=========================================================================
	private static Config inst;
	public FileConfiguration cfg = Tools.getInstance().getConfig();
	
	public int teleport$delay;
	
	public List<String> whoisCommand;
	
	public String chat$current$off;
	
	public String you$kicked;
	
	public String no$permissions;
	public String player$offline;
	public String error;
	public String teleported;
	public String you$move;
	
	public String admin$chat;
	
	public String incorrect$use$tp;
	public String teleported$tp;
	public String other$teleported$tp;
	
	public String topCommand;
	
	public String cooldown$teleport$spawnComamnd;
	
	public String setspawnCommand;
	
	public String incorrect$use$invsee;
	
	public List<String> request$for$teleportation$tpa;
	public String request$sended$tpa;
	public String request$accepted$tpa;
	public String you$accepted$request$tpa;
	public String request$deny$tpa;
	public String you$deny$request$tpa;
	public String nothing$requests$tpa;
	public String incorrect$use$tpa;
	public String cooldown$teleport$tpa;
	
	public String incorrect$use$gm;
	public String gamemode$changed$gm;
	public String gamemode$changed$other$gm;
	
	public String healCommand;
	
	public String godCommand;
	
	public String style$broadcast;
	public String incorrect$use$broadcast;
	
	public String new$hat$hat;
	public String incorrect$item$hat;
	
	public String feedCommand;
	
	public String incorrect$use$endersee;
	
	public String style$helpop;
	public String incorrect$use$helpop;
	public String sended$helpop;
	
	public String style$msg;
	public String incorrect$use$msg;
	public String nobody$reply;
	public String incorrect$use$reply;
	
	public String listCommand;
	
	public String status$changed$fly;
	public String status$changed$other$fly;
	
	public String new$enchant$enchant;
	public String incorrect$use$enchant;
	public String incorrect$item$enchant;
	public String enchant$not$found$enchant;
	
	public String not$availability$kit;
	public String you$got$kit;
	public String not$exist$kit;
	
	public List<String> clear$chat;
	public List<String> on$chat;
	public List<String> off$chat;
	public String current$chat;
	public String incorrect$use$chat;
	
	public String onlineComamnd;
	
	public String incorrect$use$kick;
	public String kick$boradcast$kick;
	
	public String incorrect$use$kickall;
	
	public String repairCommand;
	
	public String incorrect$use$tphere;
	public String teleported$tphere;
	public String player$teleported$tphere;
	
	public String actionbar$alert;
	public String incorrect$use$alert;
	
	public int chat$cooldown;
	public String next$message;
	
	public String incorrect$use$mute;
	public String incorrect$time$mute;
	public String muted$mute;
	public String unmuted$mute;
	public String you$muted;

	public String home$notset;
	public String home$cooldown;

	public String sethome$newhome;
	
	//=========================================================================
	
	public Config() {
		if(inst == null) inst = this;
	}
	
	//=========================================================================
	//Load
	public void load(){
		
		this.you$muted = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.you-muted").replace("Â", ""));
		
		this.teleport$delay = cfg.getInt("teleport-delay");
		
		this.whoisCommand = cfg.getStringList("messages.commands.whois");
		
		this.chat$cooldown = cfg.getInt("chat-cooldown");
		this.next$message = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.next-message").replace("Â", ""));
		
		this.chat$current$off = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.chat-off").replace("Â", ""));
		
		this.you$kicked = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.you-kicked").replace("Â", ""));
		
		this.no$permissions = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.no-permissions").replace("Â", ""));
		this.player$offline = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.player-offline").replace("Â", ""));
		this.error = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.error").replace("Â", ""));
		this.teleported = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.teleported").replace("Â", ""));
		this.you$move = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.you-move").replace("Â", ""));
		
		this.admin$chat = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.admin-chat").replace("Â", ""));
		
		this.incorrect$use$tp = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tp.incorrect-use").replace("Â", ""));
		this.teleported$tp = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tp.teleported").replace("Â", ""));
		this.other$teleported$tp = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tp.other-teleported").replace("Â", ""));
		
		this.topCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.top").replace("Â", ""));
	
		this.cooldown$teleport$spawnComamnd = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.spawn.cooldown-teleport").replace("Â", ""));
	
		this.setspawnCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.setspawn").replace("Â", ""));
		
		this.incorrect$use$invsee = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.invsee.incorrect-use").replace("Â", ""));
	
		this.request$for$teleportation$tpa = cfg.getStringList("messages.commands.tpa.request-for-teleportation");
		this.request$sended$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.request-sended").replace("Â", ""));
		this.request$accepted$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.request-accepted").replace("Â", ""));
		this.you$accepted$request$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.you-accepted-request").replace("Â", ""));
		this.request$deny$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.request-deny").replace("Â", ""));
		this.you$deny$request$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.you-deny-request").replace("Â", ""));
		this.nothing$requests$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.nothing-requests").replace("Â", ""));
		this.incorrect$use$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.incorrect-use").replace("Â", ""));
		this.cooldown$teleport$tpa = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tpa.cooldown-teleport").replace("Â", ""));
		
		this.incorrect$use$gm = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.gamemode.incorrect-use").replace("Â", ""));
		this.gamemode$changed$gm = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.gamemode.gamemode-changed").replace("Â", ""));
		this.gamemode$changed$other$gm = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.gamemode.gamemode-changed-other").replace("Â", ""));
	
		this.healCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.heal").replace("Â", ""));
	
		this.godCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.god").replace("Â", ""));
	
		this.incorrect$use$broadcast = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.broadcast.incorrect-use").replace("Â", ""));
		this.style$broadcast = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.broadcast.style").replace("Â", ""));
	
		this.new$hat$hat = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.hat.new-hat").replace("Â", ""));
		this.incorrect$item$hat = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.hat.incorrect-item").replace("Â", ""));
	
		this.feedCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.feed").replace("Â", ""));
	
		this.incorrect$use$endersee = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.endersee.incorrect-use").replace("Â", ""));
	
		this.incorrect$use$helpop = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.helpop.incorrect-use").replace("Â", ""));
		this.style$helpop = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.helpop.style").replace("Â", ""));
		this.sended$helpop = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.helpop.sended").replace("Â", ""));
	
		this.incorrect$use$msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.msg.incorrect-use").replace("Â", ""));
		this.style$msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.msg.style").replace("Â", ""));
		this.nobody$reply = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.reply.nobody").replace("Â", ""));
		this.incorrect$use$reply = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.reply.incorrect-use").replace("Â", ""));
	
		this.listCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.list").replace("Â", ""));
	
		this.status$changed$fly = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.fly.status-changed").replace("Â", ""));
		this.status$changed$other$fly = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.fly.status-changed-other").replace("Â", ""));
	
		this.new$enchant$enchant = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.enchant.new-enchant").replace("Â", ""));
		this.incorrect$use$enchant = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.enchant.incorrect-use").replace("Â", ""));
		this.incorrect$item$enchant = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.enchant.incorrect-item").replace("Â", ""));
	
		this.enchant$not$found$enchant = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.enchant.enchant-not-found").replace("Â", ""));
	
		this.not$availability$kit = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kit.not-availability").replace("Â", ""));
		this.you$got$kit = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kit.you-got").replace("Â", ""));
		this.not$exist$kit = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kit.not-exist").replace("Â", ""));
	
		this.clear$chat = cfg.getStringList("messages.commands.chat.clear-list");
		this.off$chat = cfg.getStringList("messages.commands.chat.off-list");
		this.on$chat = cfg.getStringList("messages.commands.chat.on-list");
		this.current$chat = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.chat.current").replace("Â", ""));
		this.incorrect$use$chat = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.chat.incorrect-use").replace("Â", ""));
	
		this.onlineComamnd = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.online").replace("Â", ""));
	
		this.incorrect$use$kick = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kick.incorrect-use").replace("Â", ""));
		this.kick$boradcast$kick = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kick.kick-broadcast").replace("Â", ""));
		
		this.incorrect$use$kickall = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.kickall.incorrect-use").replace("Â", ""));
	
		this.repairCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.repair").replace("Â", ""));
	
		this.incorrect$use$tphere = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tphere.incorrect-use").replace("Â", ""));
		this.teleported$tphere = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tphere.teleported").replace("Â", ""));
		this.player$teleported$tphere = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.tphere.player-teleported").replace("Â", ""));
	
		this.actionbar$alert = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.alert.actionbar.style").replace("Â", ""));
		this.incorrect$use$alert = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.alert.incorrect-use").replace("Â", ""));
	
		this.incorrect$use$mute = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.mute.incorrect-use").replace("Â", ""));
		this.incorrect$time$mute = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.mute.incorrect-time").replace("Â", ""));
		this.muted$mute = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.mute.muted").replace("Â", ""));
		this.unmuted$mute = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.mute.unmuted").replace("Â", ""));

		this.home$cooldown = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.home.cooldown").replace("Â", ""));
		this.home$notset = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.home.not-set").replace("Â", ""));

		this.sethome$newhome = ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.commands.sethome.new-home").replace("Â", ""));
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static Config getInst(){
		return inst;
	}
	//=========================================================================

}
