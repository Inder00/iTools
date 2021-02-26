package pl.inder00.tools.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.inder00.tools.basic.utils.KitUtil;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.utils.TimeUtil;

public class User {
	
	private UUID uuid;
	private String nickname;
	private Player player;
	
	private Player lastReceivedMessage;
	
	private long lastSendedMessage;
	private long jointime;
	private long quittime;
	private long mutetime;
	
	private int online;
	
	private RequestTeleport tpa;
	
	private boolean godMode;
	
	private HashMap<Kit, Long> kit$data;

	private Location home;

	private Location deathLocation;
	
	public User(String uuid, String nick, long quittime, long jointime, List<String> kit$data, int onlinetime, Location home) {
		
		this.uuid = UUID.fromString(uuid);
		this.nickname = nick;
		this.player = null;
		this.godMode = false;
		this.tpa = null;
		this.jointime = jointime;
		this.quittime = quittime;
		this.lastSendedMessage = 0L;
		this.lastReceivedMessage = null;
		this.kit$data = new HashMap<Kit, Long>();
		this.online = onlinetime;
		this.inputKitData(kit$data);
		this.mutetime = 0;
		this.home = home;
		
		UserUtil.addUser(this);
		
	}

	public void calc(){
		
		this.setOnline(this.getOnline()+TimeUtil.sumTime(this.jointime, this.quittime));
		
	}

	public List<String> outputKitData(){
		
		List<String> out = new ArrayList<String>();
		
		Iterator<Entry<Kit, Long>> ine = this.kit$data.entrySet().iterator();
		
		while(ine.hasNext()){
			
			Entry<Kit, Long> e = ine.next();
			
			out.add(e.getKey().getName().toLowerCase()+"@"+e.getValue());
			
		}
		
		return out;
		
	}

	public Location getDeathLocation() {
		return deathLocation;
	}

	public void setDeathLocation(Location deathLocation) {
		this.deathLocation = deathLocation;
	}

	public void inputKitData(List<String> data){
		
		for(String s : data){
			
			String[] a = s.split("@");
			
			kit$data.put(KitUtil.get(a[0].toLowerCase()), Long.parseLong(a[1]));
			
		}
		
	}
	
	public Long getKitValidy(Kit kit){
		
		long output = 0L;
		Iterator<Entry<Kit, Long>> ine = this.kit$data.entrySet().iterator();
		
		while(ine.hasNext()){
			
			Entry<Kit, Long> e = ine.next();
			if(e.getKey().equals(kit)){
				output = e.getValue();
			}
			
		}
		return output;
		
	}
	
	public void setKitValidy(Kit kit, Long validy){
		
		this.kit$data.remove(kit);
		this.kit$data.put(kit, validy);
		
	}

	public Location getHome() {
		return home;
	}

	public void setHome(Location home) {
		this.home = home;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean getGodMode() {
		return godMode;
	}

	public void setGodMode(boolean godMode) {
		this.godMode = godMode;
	}

	public Player getLastReceivedMessage() {
		return lastReceivedMessage;
	}

	public void setLastReceivedMessage(Player lastReceivedMessage) {
		this.lastReceivedMessage = lastReceivedMessage;
	}

	public RequestTeleport getTpa() {
		return tpa;
	}

	public void setTpa(RequestTeleport tpa) {
		this.tpa = tpa;
	}

	public long getJointime() {
		return jointime;
	}

	public void setJointime(long jointime) {
		this.jointime = jointime;
	}

	public long getQuitTime() {
		return quittime;
	}

	public void setQuitTime(long quittime) {
		this.quittime = quittime;
	}

	public HashMap<Kit, Long> getKitData() {
		return kit$data;
	}

	public void setKitData(HashMap<Kit, Long> kit$data) {
		this.kit$data = kit$data;
	}

	public String getNickName() {
		return nickname;
	}

	public void setNickName(String nickname) {
		this.nickname = nickname;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public long getLastSendedMessage() {
		return lastSendedMessage;
	}

	public void setLastSendedMessage(long lastSendedMessage) {
		this.lastSendedMessage = lastSendedMessage;
	}

	public long getMutetime() {
		return mutetime;
	}

	public void setMutetime(long mutetime) {
		this.mutetime = mutetime;
	}
	

}
