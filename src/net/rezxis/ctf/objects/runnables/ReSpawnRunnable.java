package net.rezxis.ctf.objects.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.TeamObject;
import net.rezxis.ctf.utils.TitleUtils;

public class ReSpawnRunnable extends BukkitRunnable {

	private Player player;
	
	public ReSpawnRunnable(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		TeamObject team = CTFMain.instance.getTeamManager().getTeamUUID(player.getUniqueId());
		player.teleport(team.getSpawn());
		player.setAllowFlight(false);
		TitleUtils.sendTitle("Respawned!", ChatColor.GOLD, player, 10 , 20*1, 10);
		player.sendMessage(ChatColor.GOLD+"Respawned!");
	}
}
