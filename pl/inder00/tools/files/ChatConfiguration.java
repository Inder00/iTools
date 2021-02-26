package pl.inder00.tools.files;

import org.bukkit.configuration.file.YamlConfiguration;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Group;

public class ChatConfiguration {

	//=========================================================================
	private static ChatConfiguration inst;
	public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Tools.getInstance().chat);
	
	public String default$group;
	
	//=========================================================================
	
	public ChatConfiguration() {
		if(inst == null) inst = this;
	}
	
	//=========================================================================
	//Load
	public void load(){

		this.default$group = cfg.getString("config.default");
		
		for(String e : cfg.getConfigurationSection("config.groups").getKeys(false)){
			
			new Group(e,cfg.getString("config.groups."+e));
			
		}
		
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static ChatConfiguration getInst(){
		return inst;
	}
	//=========================================================================
	
}
