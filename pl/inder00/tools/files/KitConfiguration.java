package pl.inder00.tools.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Enchantments;
import pl.inder00.tools.basic.Kit;
import pl.inder00.tools.utils.IntegerUtil;

public class KitConfiguration {

	//=========================================================================
	private static KitConfiguration inst;
	public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Tools.getInstance().kits);
	
	public String gui$name;
	public String gui$item$name;
	public List<String> gui$item$lore;
	
	//=========================================================================
	
	public KitConfiguration() {
		if(inst == null) inst = this;
	}
	
	//=========================================================================
	//Load
	@SuppressWarnings({ "deprecation", "unused" })
	public void load(){

		this.gui$name = ChatColor.translateAlternateColorCodes('&', cfg.getString("config.gui.name").replace("Â", ""));
		this.gui$item$name = ChatColor.translateAlternateColorCodes('&', cfg.getString("config.gui.item.name").replace("Â", ""));
		this.gui$item$lore = cfg.getStringList("config.gui.item.lore");
		
		int id = 1;
		
		for(String s : cfg.getConfigurationSection("config.kits").getKeys(false)){
			
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			int delay = cfg.getInt("config.kits."+s+".delay");
			String kitname = s;
			Material item$in$gui = null;
			
			int slot = cfg.getInt("config.kits."+s+".slot");
			
			if(slot == 0){
				
				slot = id;
				
			}
			id++;
			
			for(String i : cfg.getConfigurationSection("config.kits."+s+".items").getKeys(false)){

				if(i.equalsIgnoreCase("koxy")){
					int amount = cfg.getInt("config.kits."+s+".items."+i+".amount");
					items.add(new ItemStack(Material.GOLDEN_APPLE, amount, (short)1));
				} else {
					int amount = 1;
					String name = null;
					short data = 0;
					List<String> lore = new ArrayList<String>();
					HashMap<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
					Material material = null;

					if(cfg.getStringList("config.kits."+s+".items."+i+".enchantments") != null){
						for(String h : cfg.getStringList("config.kits."+s+".items."+i+".enchantments")){

							String[] ench = h.split(":");

							if(Enchantments.getEnchantment(ench[0]) == null){
								Tools.getInstance().getLogger().severe("Invalid enchantment name: "+ench[0]+" in selection '"+i+"' in kit: "+kitname);
							} else {
								enchants.put(Enchantments.getEnchantment(ench[0]), Integer.parseInt(ench[1]));
							}

						}
					}
					boolean id_is_int2 = IntegerUtil.isInteger(cfg.getString("config.kits."+s+".item-in-gui"));
					if(cfg.getString("config.kits."+s+".item-in-gui") != null){

						if(id_is_int2 == true){

							item$in$gui = Material.getMaterial(Integer.parseInt(cfg.getString("config.kits."+s+".item-in-gui")));

						} else {

							item$in$gui = Material.getMaterial(cfg.getString("config.kits."+s+".item-in-gui").toUpperCase());
						}

					} else {
						Tools.getInstance().getLogger().severe("Invalid gui-material: "+cfg.getString("config.kits."+s+".item-in-gui")+" in kit: "+kitname);
						return;
					}

					for(String h : cfg.getConfigurationSection("config.kits."+s+".items."+i).getKeys(false)){

						boolean id_is_int = IntegerUtil.isInteger(cfg.getString("config.kits."+s+".items."+i+".id"));
						if(cfg.getString("config.kits."+s+".items."+i+".id") != null){

							if(id_is_int == true){

								material = Material.getMaterial(Integer.parseInt(cfg.getString("config.kits."+s+".items."+i+".id")));

							} else {

								material = Material.getMaterial(cfg.getString("config.kits."+s+".items."+i+".id").toUpperCase());
							}

						} else {
							Tools.getInstance().getLogger().severe("Invalid material: "+cfg.getString("config.kits."+s+".items."+i+".id")+" in kit: "+kitname);
							return;
						}

						if(cfg.getString("config.kits."+s+".items."+i+".data") != null){

							data = ((short) cfg.getInt("config.kits."+s+".items."+i+".data"));

						}

						if(cfg.getString("config.kits."+s+".items."+i+".name") != null){

							name = cfg.getString("config.kits."+s+".items."+i+".name");

						}

						if(cfg.getStringList("config.kits."+s+".items."+i+".lore") != null && cfg.getStringList("config.kits."+s+".items."+i+".lore").size() > 0){

							lore = cfg.getStringList("config.kits."+s+".items."+i+".lore");

						}

						if(cfg.getString("config.kits."+s+".items."+i+".amount") != null){

							amount = Integer.parseInt(cfg.getString("config.kits."+s+".items."+i+".amount"));

						}

					}

					if(material == null){
						Tools.getInstance().getLogger().severe("Invalid material in selection '"+i+"' in kit: "+kitname);
						return;
					}

					ItemStack item = null;
					if(data == 0){
						item = new ItemStack(material);
					} else {
						item = new ItemStack(material, data);
					}
					item.setAmount(amount);
					ItemMeta itemmeta = item.getItemMeta();
					if(name != null){
						itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name.replace("Â", "")));
					}
					if(lore != null && lore.isEmpty() == false){

						List<String> lore2 = new ArrayList<String>();
						for(String a : lore){

							lore2.add(ChatColor.translateAlternateColorCodes('&', a.replace("Â", "")));

						}
						itemmeta.setLore(lore2);
					}
					if(enchants.size() > 0){
						Iterator<Entry<Enchantment, Integer>> ite = enchants.entrySet().iterator();
						while(ite.hasNext()){

							Entry<Enchantment, Integer> ench = ite.next();

							itemmeta.addEnchant(ench.getKey(), ench.getValue(), false);

						}
					}

					item.setItemMeta(itemmeta);

					items.add(item);
				}
				
			}
			
			new Kit(kitname.toLowerCase(), items, item$in$gui, delay,slot);
			
		}
		
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static KitConfiguration getInst(){
		return inst;
	}
	//=========================================================================
	
}
