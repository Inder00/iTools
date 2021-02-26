package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Enchantments;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.IntegerUtil;

public class EnchantCommand implements CommandExecutor {

	Tools plugin;
	
	public EnchantCommand(Tools plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("enchant").setExecutor(this);
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
		if(p.hasPermission("tools.enchant")) {
			
			if(args.length == 0){
				
				p.sendMessage(cfg.incorrect$use$enchant);
				return false;
				
			} else if(args.length == 2) {
				
				boolean integer = IntegerUtil.isInteger(args[1]);
				String enchantment = args[0];
				
				if(!integer){
					p.sendMessage(cfg.incorrect$use$enchant);
					return false;
				}
				
				ItemStack hand = p.getItemInHand();
				
				if(hand == null || hand.getType().equals(Material.AIR)) {
					p.sendMessage(cfg.incorrect$item$enchant);
					return false;
				}
				Enchantment ench = Enchantments.getEnchantment(enchantment);
				
				if(ench == null){
					p.sendMessage(cfg.enchant$not$found$enchant);
					return false;
				}
				
				ItemMeta toolmeta = hand.getItemMeta();
				toolmeta.addEnchant(ench, Integer.parseInt(args[1]), false);
				hand.setItemMeta(toolmeta);
				
				p.sendMessage(cfg.new$enchant$enchant.replace("{TOOL}", hand.getType().name()).replace("{ENCHANT}", ench.getName()));
				return false;	
			} else {
				p.sendMessage(cfg.incorrect$use$enchant);
				return false;
			}
			
		} else {
			p.sendTitle("",cfg.no$permissions);
			p.sendMessage(cfg.no$permissions);
			return false;
		}
	}

}
