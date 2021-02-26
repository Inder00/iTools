package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;

public class HatCommand implements CommandExecutor {

	Tools plugin;
	
	public HatCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("hat").setExecutor(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest obslugiwana przez gre!");
			return false;
		}
		
		Player p = (Player) sender;
		Config cfg = Config.getInst();
		if(p.hasPermission("tools.hat")) {
			
			ItemStack hand = p.getItemInHand();
			ItemStack old = p.getInventory().getHelmet();
			
			if(hand == null || hand.getType().equals(Material.AIR)) {
				p.sendMessage(cfg.incorrect$item$hat);
				return false;
			}
			p.getInventory().setHelmet(hand);
			p.sendMessage(cfg.new$hat$hat.replace("{ITEM}", hand.getType().name()));
			p.setItemInHand(old);
			return false;
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
