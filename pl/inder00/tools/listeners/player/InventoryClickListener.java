package pl.inder00.tools.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import pl.inder00.tools.basic.Kit;
import pl.inder00.tools.basic.utils.KitUtil;
import pl.inder00.tools.files.KitConfiguration;

public class InventoryClickListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onClick(InventoryClickEvent e){
		if(e.isCancelled()) return;
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType().equals(Material.AIR)) return;
		
		KitConfiguration kcfg = KitConfiguration.getInst();
		Player p = (Player) e.getWhoClicked();
		ItemStack select = e.getCurrentItem();
		
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(kcfg.gui$name)){
			
			Kit kit = KitUtil.get(select.getType());
			if(kit == null){
				e.setCancelled(true);
				p.closeInventory();
				return;
			}
			Bukkit.dispatchCommand(p, "kit "+kit.getName().toLowerCase());
			e.setCancelled(true);
			p.closeInventory();
			return;
		}
	}

}
