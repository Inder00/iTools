package pl.inder00.tools.basic;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.inder00.tools.basic.utils.KitUtil;

public class Kit {
	
	private String name;
	private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	private int delay;
	private Material itemGui;
	private int slotGui;
	private boolean enabled;
	
	public Kit(String name, ArrayList<ItemStack> items, Material item, int delay, int slotGui){
		this.name = name;
		this.items = items;
		this.delay = delay;
		this.itemGui = item;
		this.slotGui = slotGui;
		this.enabled = false;
		KitUtil.addKit(this);
	}
	
	public void give(Player player){
		
		if(player.isOnline()){
			
			HashMap<Integer, ItemStack> inv = new HashMap<Integer, ItemStack>();
			
			for(ItemStack item : this.items){
				inv.putAll(player.getInventory().addItem(item));
			}
			
			Location loc = player.getLocation();
			World world = loc.getWorld();
			
			for (ItemStack l : inv.values())
			{
				
				world.dropItemNaturally(loc, l);
				
			}
			
		}
		
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ItemStack> getItems() {
		return items;
	}
	public void setItems(ArrayList<ItemStack> items) {
		this.items = items;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Material getItemGui() {
		return itemGui;
	}

	public void setItemGui(Material itemGui) {
		this.itemGui = itemGui;
	}

	public int getSlotGui() {
		return slotGui;
	}

	public void setSlotGui(int slotGui) {
		this.slotGui = slotGui;
	}

}
