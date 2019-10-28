package net.rezxis.ctf.listeners;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.TeamObject;

public class CommandListener {

	public static boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("test")) {
			CTFMain.instance.getGameManager().start();
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("team")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.AQUA+"/team join <Red/Blue>");
				sender.sendMessage(ChatColor.AQUA+"/team leave");
				sender.sendMessage(ChatColor.AQUA+"/team status");
				return true;
			}
			if (args[0].equalsIgnoreCase("status")) {
				sender.sendMessage(ChatColor.GOLD+"======================");
				sender.sendMessage(ChatColor.GOLD+"Blue team : "+CTFMain.instance.getTeamManager().getBlue().getPlayers());
				sender.sendMessage(ChatColor.GOLD+"Red team : "+CTFMain.instance.getTeamManager().getRed().getPlayers());
				sender.sendMessage(ChatColor.GOLD+"======================");
			} else if (args[0].equalsIgnoreCase("join")) {
				if (CTFMain.instance.getTeamManager().getColorUUID(player.getUniqueId()) != null) {
					player.sendMessage(ChatColor.AQUA+"You have already in a team!");
					return true;
				}
				if (args.length != 2) {
					player.sendMessage(ChatColor.AQUA+"/team join <Red/Blue>");
					return true;
				}
				TeamObject team = null;
				if (args[1].equalsIgnoreCase("red")) {
					team = CTFMain.instance.getTeamManager().getRed();
				} else if (args[1].equalsIgnoreCase("blue")){
					team = CTFMain.instance.getTeamManager().getBlue();
				}
				if (team == null) {
					player.sendMessage(ChatColor.AQUA+"/team join <Red/Blue>");
					return true;
				}
				team.addPlayer(player.getUniqueId());
				player.sendMessage(ChatColor.AQUA+"joinned to "+args[1]+"!");
				return true;
			} else if (args[0].equalsIgnoreCase("leave")) {
				if (CTFMain.instance.getTeamManager().getTeamUUID(player.getUniqueId()) != null) {
					CTFMain.instance.getTeamManager().leaveFromTeam(player.getUniqueId());
					player.sendMessage(ChatColor.AQUA+"You left from team!");
				} else {
					player.sendMessage(ChatColor.RED+"You are not in a team!");
				}
				return true;
			}
		}
		return true;
	}
}
