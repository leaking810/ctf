package net.rezxis.ctf.managers;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.objects.TeamObject;

public class TeamManager {

	private HashMap<ChatColor,TeamObject> teams;
	
	public TeamManager() {
		teams = new HashMap<>();
		teams.put(ChatColor.RED, new TeamObject(ChatColor.RED));
		teams.put(ChatColor.BLUE, new TeamObject(ChatColor.BLUE));
	}
	
	public TeamObject getBlue() {
		return teams.get(ChatColor.BLUE);
	}
	
	public TeamObject getRed() {
		return teams.get(ChatColor.RED);
	}
	
	public void leaveFromTeam(UUID uuid) {
		for (TeamObject obj : teams.values()) {
			obj.leavePlayer(uuid);
		}
	}

	public TeamObject getTeamUUID(UUID uuid) {
		if (this.getRed().getMembers().contains(uuid))
			return this.getRed();
		if (this.getBlue().getMembers().contains(uuid))
			return this.getBlue();
		return null;
	}
	
	public ChatColor getColorUUID(UUID uuid) {
		if (teams.get(ChatColor.RED).getMembers().contains(uuid))
			return ChatColor.RED;
		if (teams.get(ChatColor.BLUE).getMembers().contains(uuid))
			return ChatColor.BLUE;
		return null;
	}
}
