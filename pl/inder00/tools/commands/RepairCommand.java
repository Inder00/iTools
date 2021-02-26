package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class RepairCommand implements CommandExecutor {

	Tools plugin;
	
	public RepairCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("repair").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		
		if(p.hasPermission("tools.repair")){
			
			@SuppressWarnings("deprecation")
			ItemStack hand = p.getItemInHand();
			
			hand.setDurability((short) 0);
			p.sendMessage(cfg.repairCommand.replace("{TOOL}", hand.getType().name()));
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
		return false;
	}

}
