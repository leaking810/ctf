package net.rezxis.ctf;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.rezxis.ctf.listeners.CommandListener;
import net.rezxis.ctf.listeners.PlayerListeners;
import net.rezxis.ctf.managers.FlagManager;
import net.rezxis.ctf.managers.GameManager;
import net.rezxis.ctf.managers.SBManager;
import net.rezxis.ctf.managers.TeamManager;
import net.rezxis.ctf.objects.MapConfig;
import net.rezxis.ctf.objects.runnables.SecondRunnable;
import net.rezxis.ctf.utils.Hologram;

public class CTFMain extends JavaPlugin {

	public static CTFMain instance;
	private TeamManager teamsManager;
	private GameManager gameManager;
	private FlagManager flagManager;
	private SBManager sbManager;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		super.onEnable();
		instance = this;
		Hologram.removeAll();
		sbManager = new SBManager();
		this.teamsManager = new TeamManager();
		this.gameManager = new GameManager(new MapConfig("test"));
		this.flagManager = new FlagManager();
		Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new SecondRunnable(), 0, 20);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		Hologram.removeAll();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return CommandListener.onCommand(sender, cmd, commandLabel, args);
	}
	
	public SBManager getScoreBoardManager() {
		return this.sbManager;
	}
	
	public FlagManager getFlagManager() {
		return this.flagManager;
	}
	
	public TeamManager getTeamManager() {
		return this.teamsManager;
	}
	
	public GameManager getGameManager() {
		return this.gameManager;
	}
}
