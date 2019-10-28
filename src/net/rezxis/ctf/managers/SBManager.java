package net.rezxis.ctf.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.FlagObject;

public class SBManager {

	public Scoreboard board;

	public void update() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("board", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.AQUA+"Rezxis"+ChatColor.GOLD+" CTF");
		int raw = 1;
		{
			Score score = obj.getScore(" ");
			score.setScore(raw);
			++raw;
		}
		
		for (FlagObject flag : CTFMain.instance.getFlagManager().getFlags()) {
			Score score = null;
			if (flag.getScore() == 49) {
				score = obj.getScore(ChatColor.GOLD+flag.getName()+"    "+flag.getTeam()+"Captured");
			} else {
				score = obj.getScore(ChatColor.GOLD+flag.getName()+"    "+flag.getTeam()+flag.getScore()+"/49");
			}
			score.setScore(raw);
			++raw;
		}
		
		{
			Score score = obj.getScore("   ");
			score.setScore(raw);
			++raw;
		}
		
		{
			Score score = obj.getScore(ChatColor.RED+"RED      "+CTFMain.instance.getTeamManager().getRed().getPoint());
			score.setScore(raw);
			++raw;
		}
		{
			Score score = obj.getScore(ChatColor.BLUE+"BLUE    "+CTFMain.instance.getTeamManager().getBlue().getPoint());
			score.setScore(raw);
			++raw;
		}
		{
			Score score = obj.getScore("  ");
			score.setScore(raw);
			++raw;
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
	}
}
