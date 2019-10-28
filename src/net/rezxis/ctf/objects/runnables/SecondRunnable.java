package net.rezxis.ctf.objects.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.FlagObject;
import net.rezxis.ctf.objects.TeamObject;
import net.rezxis.ctf.utils.BossBar;

public class SecondRunnable extends BukkitRunnable {

	private static int winP = 150;
	
	@Override
	public void run() {
		TeamObject blue = CTFMain.instance.getTeamManager().getBlue();
		TeamObject red = CTFMain.instance.getTeamManager().getRed();
		//BossBar
		for(String s : BossBar.getPlayers()) {
            Player o = Bukkit.getPlayer(s);
            if(o != null) {
            	BossBar.teleportBar(o);
            	BossBar.updateBar(o, CTFMain.instance.getGameManager().getBar(), CTFMain.instance.getGameManager().getBarHealth());
            } else {
            	BossBar.setBar(o, CTFMain.instance.getGameManager().getBar(), CTFMain.instance.getGameManager().getBarHealth());
            }
        }
		//player tick
		for (Player player : Bukkit.getOnlinePlayers()) {
			TeamObject team = CTFMain.instance.getTeamManager().getTeamUUID(player.getUniqueId());
			for (FlagObject flag : CTFMain.instance.getFlagManager().getFlags()) {
				if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					if (flag.isIN(player.getLocation())) {
						if (team != null) {
							flag.update(team);
						}
					}
				}
			}
		}
		//update
		for (FlagObject flag : CTFMain.instance.getFlagManager().getFlags()) {
			flag.getHologram().update();
			if (flag.getScore() == 49) {
				if (flag.getTeam() == ChatColor.BLUE) {
					blue.setPoint(blue.getPoint()+1);
				} else {
					red.setPoint(red.getPoint()+1);
				}
			}
		}
		if (red.getPoint() >= winP) {
			CTFMain.instance.getGameManager().won(red);
		}
		if (blue.getPoint() >= winP) {
			CTFMain.instance.getGameManager().won(blue);
		}
		CTFMain.instance.getScoreBoardManager().update();
	}
}
