package net.rezxis.ctf.objects;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.utils.Hologram;
import org.bukkit.inventory.ItemStack;

public class FlagObject {

	private static int max = 49;
	
	private ChatColor team;
	private int score = 0;
	private Location loc;
	private Hologram holo;
	private String name;
	
	/*
	 * 49 = fully captured
	 */
	
	public FlagObject(String name, Location loc) {
		this.team = ChatColor.GRAY;
		this.name = name;
		this.loc = loc;
		this.holo = new Hologram(ChatColor.GOLD+name, loc.clone().add(0.5, 1, 0.5), new ItemStack(Material.WOOL,1,DyeColor.GRAY.getWoolData()));
		this.holo.show();
	}
	
	public void update(TeamObject team) {
		if (this.team == team.getTeam()) {
			if (score != max) {
				int p0 = score%7;
				int p1 = (score-(score%7))/7;
				Block b = loc.getWorld().getBlockAt(loc.clone().add(-3,0,-3).add(p0,0,p1));
				byte data;
				if (this.team == ChatColor.RED) {
					this.holo.setHead(new ItemStack(Material.WOOL,1,DyeColor.RED.getWoolData()));
					data = DyeColor.RED.getWoolData();
				} else {
					this.holo.setHead(new ItemStack(Material.WOOL,1,DyeColor.BLUE.getWoolData()));
					data = DyeColor.BLUE.getWoolData();
				}
				b.setData(data);
				score += 1;
			}
		} else {
			if (score == 0) {
				this.team = team.getTeam();
				byte data;
				if (this.team == ChatColor.RED) {
					this.holo.setHead(new ItemStack(Material.WOOL,1,DyeColor.RED.getWoolData()));
					data = DyeColor.RED.getWoolData();
				} else {
					this.holo.setHead(new ItemStack(Material.WOOL,1,DyeColor.BLUE.getWoolData()));
					data = DyeColor.BLUE.getWoolData();
				}
				Block b = loc.getWorld().getBlockAt(loc.clone().add(-3,0,-3));
				b.setData(data);
			} else {
				score -= 1;
				int p0 = score%7;
				int p1 = (score-(score%7))/7;
				Block b = loc.getWorld().getBlockAt(loc.clone().add(-3,0,-3).add(p0,0,p1));
				b.setData(DyeColor.GRAY.getWoolData());
			}
		}
		
		this.holo.updateAsync(this.team+""+score+"/"+max);
	}
	
	public void onTick() {
		
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ChatColor getTeam() {
		return this.team;
	}
	
	public Location getLoc() {
		return this.loc;
	}
	
	public Hologram getHologram() {
		return this.holo;
	}
	
	public boolean isIN(Location p) {
		Location pos1 = loc.clone().add(-3,0,-3);
		Location pos2 = loc.clone().add(4,0,4);
		boolean f0 = false,f1 = false,f2 = false,f3 = false;
		if (pos1.getX() <= p.getX()) {
			f0 = true;
		}
		if (pos1.getZ() <= p.getZ()) {
			f1 = true;
		}
		if (pos2.getX() >= p.getX()) {
			f2 = true;
		}
		if (pos2.getZ() >= p.getZ()) {
			f3 = true;
		}
		return f0&f1&f2&f3;
	}
}
