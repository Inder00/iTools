package pl.inder00.tools.basic;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.inder00.tools.basic.utils.TeleportUtil;

public class Teleport {
	
	private Player player;
	private Location location;
	private Location where;
	private int delay;
	private boolean ignoreHighestBlock;

	public Teleport(Player player, Location where, int delay) {
		this(player,where,delay,false);
	}

	public Teleport(Player player, Location where, int delay, boolean ignoreHighestBlock) {
		this.player = player;
		this.player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, ((20*delay)+40), 2));
		this.location = this.player.getLocation();
		this.where = where;
		this.delay = delay;
		this.ignoreHighestBlock = ignoreHighestBlock;
		TeleportUtil.addTeleport(this);
	}

	public boolean isIgnoringHighestBlock() {
		return ignoreHighestBlock;
	}

	public void setIgnoreHighestBlock(boolean ignoreHighestBlock) {
		this.ignoreHighestBlock = ignoreHighestBlock;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getWhere() {
		return where;
	}

	public void setWhere(Location where) {
		this.where = where;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
