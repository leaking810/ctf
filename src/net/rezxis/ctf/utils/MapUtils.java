package net.rezxis.ctf.utils;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import net.rezxis.ctf.objects.FlagObject;

public class MapUtils {
	
	public static void initFlag(FlagObject flag) {
		Location loc = flag.getLoc().clone().add(-3, 0, -3);
		Location loc2 = loc.clone().add(-1, 0, -1);
		for (int x=0; x < 7; x++) {
			for (int z=0; z < 7; z++) {
				Block block = flag.getLoc().getWorld().getBlockAt(loc.clone().add(x,0,z));
				block.setType(Material.WOOL);
				block.setData(DyeColor.GRAY.getWoolData());
			}
		}
		for (int x=0; x < 8; x++) {
			Block block = flag.getLoc().getWorld().getBlockAt(loc2.add(1,0,0));
			block.setType(Material.WOOL);
			block.setData(DyeColor.BLACK.getWoolData());
		}
		for (int x=0; x < 8; x++) {
			Block block = flag.getLoc().getWorld().getBlockAt(loc2.add(0,0,1));
			block.setType(Material.WOOL);
			block.setData(DyeColor.BLACK.getWoolData());
		}
		for (int x=0; x < 8; x++) {
			Block block = flag.getLoc().getWorld().getBlockAt(loc2.add(-1,0,0));
			block.setType(Material.WOOL);
			block.setData(DyeColor.BLACK.getWoolData());
		}
		for (int x=0; x < 8; x++) {
			Block block = flag.getLoc().getWorld().getBlockAt(loc2.add(0,0,-1));
			block.setType(Material.WOOL);
			block.setData(DyeColor.BLACK.getWoolData());
		}
	}
}
