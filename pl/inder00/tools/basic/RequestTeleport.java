package pl.inder00.tools.basic;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.inder00.tools.utils.TimeUtil;

public class RequestTeleport {
	
	private Player a;
	private Player b;
	private Location location;
	private int delay;
	private long validy;
	
	public RequestTeleport(Player a, Player b, int delay, int validy) {
		this.a = a;
		this.b = b;
		this.location = b.getLocation();
		this.delay = delay;
		this.validy = TimeUtil.addTime(validy);
	}
	public void teleport(){
		
		if(a.isOnline() && b.isOnline()){
			
			new Teleport(this.a, this.location, this.delay).setIgnoreHighestBlock(true);
			
		}
	}
	public boolean accept(Player p){
		
		if(this.b.equals(p)){
			return true;
		}
		return false;
		
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Player getPlayerA() {
		return a;
	}

	public void setPlayerA(Player a) {
		this.a = a;
	}

	public Player getPlayerB() {
		return b;
	}

	public void setPlayerB(Player b) {
		this.b = b;
	}

	public long getValidy() {
		return validy;
	}

	public void setValidy(long validy) {
		this.validy = validy;
	}

}
