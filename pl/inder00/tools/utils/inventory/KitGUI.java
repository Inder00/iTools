package pl.inder00.tools.utils.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.inder00.tools.basic.Kit;
import pl.inder00.tools.basic.utils.KitUtil;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.KitConfiguration;
import pl.inder00.tools.utils.TimeUtil;

public class KitGUI {
	
	public void show(Player player){
		
		if(player.isOnline()){
			
			KitConfiguration kcfg = KitConfiguration.getInst();
			
			Inventory inv = Bukkit.createInventory(null, 9*3, kcfg.gui$name);
			
			ItemStack szyba = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			
			for(int i=0;i<27;i++){
				inv.setItem(i, szyba);
			}
			
			for(Kit k : KitUtil.getKits()){
			
				ItemStack s = new ItemStack(k.getItemGui(), 1);
				ItemMeta m = s.getItemMeta();
				m.setDisplayName(kcfg.gui$item$name.replace("{KITNAME}", k.getName().toUpperCase()));
				List<String> lore = new ArrayList<String>();
				long millis = TimeUtil.getTime();
				for(String e : kcfg.gui$item$lore){
					lore.add(ChatColor.translateAlternateColorCodes('&', e
							.replace("{AVAILABILITY}", millis >= UserUtil.get(player.getUniqueId()).getKitValidy(k) ? "Teraz" : TimeUtil.convertTime(TimeUtil.remTime(UserUtil.get(player.getUniqueId()).getKitValidy(k))))
							.replace("{KITNAME}", k.getName().toUpperCase())
							.replace("Â", "")
							));
				}
				m.setLore(lore);
				s.setItemMeta(m);
				
				inv.setItem(k.getSlotGui()-1, s);
			}
			
			player.openInventory(inv);
			
		}
		
	}

}
