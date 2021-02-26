package pl.inder00.tools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import bstats.bukkit.Metrics;
import pl.inder00.tools.basic.Enchantments;
import pl.inder00.tools.basic.enums.PermissionEnum;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.basic.utils.WarpsUtil;
import pl.inder00.tools.commands.*;
import pl.inder00.tools.files.ChatConfiguration;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.files.KitConfiguration;
import pl.inder00.tools.listeners.entity.EntityDamageListener;
import pl.inder00.tools.listeners.player.*;
import pl.inder00.tools.tasks.Task;
import pl.inder00.tools.utils.chat.PermissionPlugin;

public class Tools extends JavaPlugin {
	
	private static Tools instance;
	public static String NMS_VERSION;
	
	private static PermissionEnum PERMISSION_ENUM = PermissionEnum.NONE;
	public PermissionPlugin PERMISSION_PLUGIN;
	
	private static File mainDir;
	public File cfgFile = new File(this.getDataFolder(), "config.yml");
	public File users = new File(this.getDataFolder(), "data");
	public File warps = new File(this.getDataFolder(), "warps");
	public File kits = new File(this.getDataFolder(), "kits.yml");
	public File chat = new File(this.getDataFolder(), "chat.yml");
		
	public boolean save = true;
	
	@Override
	public void onEnable() {
		instance = this;
		
		new Metrics(this);
		
		mainDir = this.getDataFolder();
		if(!mainDir.exists()){
			mainDir.mkdir();
		}
		if(!cfgFile.exists()){
			this.saveDefaultConfig();
		}
		if(!kits.exists()){
			this.saveResource("kits.yml", true);
		}
		if(!chat.exists()){
			this.saveResource("chat.yml", true);
		}
		if(!users.exists()){
			users.mkdir();
		}
		if(!warps.exists()){
			warps.mkdir();
		}
		
		//ENCHANTY
		new Enchantments(this);
		
		//FUNKCJE STALE
		new Config().load();
		new KitConfiguration().load();
		new ChatConfiguration().load();
		new PermissionPlugin(this);
		
		//GLOWNY TASK
		Task task = new Task(this);
		task.runTaskTimerAsynchronously(this, 0, 20);
		
		//KOMENDY
		new WhoisCommand(this);
		new TpCommand(this);
		new TopCommand(this);
		new SpawnCommand(this);
		new SetSpawnCommand(this);
		new InvseeCommand(this);
		new TpaCommand(this);
		new GamemodeCommand(this);
		new HealCommand(this);
		new GodCommand(this);
		new BroadcastCommand(this);
		new HatCommand(this);
		new FeedCommand(this);
		new EnderseeCommand(this);
		new EnderchestCommand(this);
		new MsgCommand(this);
		new ReplyCommand(this);
		new ListCommand(this);
		new FlyCommand(this);
		new EnchantCommand(this);
		new KitCommand(this);
		new ChatCommand(this);
		new OnlineCommand(this);
		new KickCommand(this);
		new KickallCommand(this);
		new RepairCommand(this);
		new TphereCommand(this);
		new MuteCommand(this);
		new HomeCommand(this);
		new SetHomeCommand(this);
		new SetWarpCommand(this);
		new DelWarpCommand(this);
		new WarpCommand(this);
		new WorkbenchCommand(this);
		new NameCommand(this);
		new BackCommand(this);
		
		//LISTENERY
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new AsyncPlayerChatListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		
		//SPRAWDZANIE
		UserUtil.load();
		UserUtil.checkPlayers();
		WarpsUtil.load();

	}
	
	@Override
	public void onDisable(){
		UserUtil.save();
	}

	public static Tools getInstance() {
		return instance;
	}

	public File getUsers() {
		return users;
	}

	public PermissionEnum getPermissionEnum() {
		return PERMISSION_ENUM;
	}

	public void setPermissionEnum(PermissionEnum permissionenum) {
		PERMISSION_ENUM = permissionenum;
	}
}
