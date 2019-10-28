package net.rezxis.ctf.objects;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;

import net.md_5.bungee.api.ChatColor;

public class TeamObject {

	private ChatColor team;
	private ArrayList<UUID> players;
	private Location spawn;
	private int point = 0;
	
	public TeamObject(ChatColor team) {
		this.team = team;
		this.players = new ArrayList<>();
	}
	
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}
	
	public Location getSpawn() {
		return this.spawn;
	}
	
	public int getPoint() {
		return this.point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getPlayers() {
		return players.size();
	}
	
	public ChatColor getTeam() {
		return this.team;
	}
	
	public ArrayList<UUID> getMembers() {
		return this.players;
	}
	
	public void addPlayer(UUID p) {
		players.add(p);
	}
	
	public void leavePlayer(UUID p) {
		players.remove(p);
	}
	
	public boolean isInTeam(UUID p) {
		return players.contains(p);
	}
}
