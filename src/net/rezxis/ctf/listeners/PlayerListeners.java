package net.rezxis.ctf.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.runnables.ReSpawnRunnable;
import net.rezxis.ctf.utils.BossBar;
import net.rezxis.ctf.utils.TitleUtils;

public class PlayerListeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Scoreboard sb = CTFMain.instance.getScoreBoardManager().board;
		if (sb != null) {
			event.getPlayer().setScoreboard(sb);
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		BossBar.removeBar(event.getPlayer());
	}
	
	@EventHandler
	public void onDead(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if((player.getHealth()-event.getDamage()) <= 0) {
				if (CTFMain.instance.getTeamManager().getTeamUUID(player.getUniqueId()) == null)
					return;
				event.setCancelled(true);
				player.setGameMode(GameMode.SURVIVAL);
				player.setAllowFlight(true);
				player.sendMessage(ChatColor.GOLD+"You are died! Respawn in 10 seconds");
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*10, 10));
				player.getInventory().clear();
				TitleUtils.sendTitle("Respawn in 10 seconds", ChatColor.GOLD, player,10 , 20*5, 10);
				Bukkit.getScheduler().runTaskLater(CTFMain.instance, new ReSpawnRunnable(player), 20*10);
			}
		}
	}
}
