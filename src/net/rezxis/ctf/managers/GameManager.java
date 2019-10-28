package net.rezxis.ctf.managers;

import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.MapConfig;
import net.rezxis.ctf.objects.TeamObject;
import net.rezxis.ctf.utils.TitleUtils;

public class GameManager {

	/*
	 * 0 = waiting
	 * 1 = preparation
	 * 2 = war
	 */
	//1phase = 10min / 600s
	private int phase = 0;
	private long time = 0;
	private MapConfig conf;
	
	public GameManager(MapConfig conf) {
		this.conf = conf;
		try {
			conf.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		CTFMain.instance.getTeamManager().getBlue().setSpawn(conf.getSpawnBlue());
		CTFMain.instance.getTeamManager().getRed().setSpawn(conf.getSpawnRed());
		CTFMain.instance.getFlagManager().initMap(conf);
	}
	
	public void won(TeamObject team) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			TitleUtils.sendTitle(team.getTeam().name()+" WON!", team.getTeam(), p, 10, 40, 10);
			p.setAllowFlight(true);
		}
	}
	
	public void setPhase(int i) {
		this.phase = i;
	}
	
	public int getPhase() {
		return this.phase;
	}
	
	public String getBar() {
		String text = "Phase "+phase;
		return text;
	}
	
	public int getBarHealth() {
		return (int) ((600 - time%600) / 600 * 100 );
	}
}
