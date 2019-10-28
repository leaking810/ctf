package net.rezxis.ctf.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.rezxis.ctf.CTFMain;

public class MapConfig {
	
	/*
	 * Name-Value
	 * 
	 * Spawn<Team>-World:x:y:z
	 * Flag-Name:World:x:y:z
	 */
	private static File map = null;
	private String name;
	private HashMap<String,Location> flags;
	private Location spawnBlue;
	private Location spawnRed;
	
	public MapConfig(String name) {
		this.name = name;
		this.flags = new HashMap<>();
		if (map == null) {
			 map = new File(CTFMain.instance.getDataFolder(),"maps");
		}
	}
	
	public void load() throws Exception {
		if (!map.exists()) {
			map.mkdirs();
		}
		File file = new File(map,name+".map");
		if (!file.exists()) {
			throw new Exception("Couldn't load map config");
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("Spawn")) {
				String[] vals = line.split("#")[1].split(":");
				if (line.startsWith("SpawnRed")) {
					spawnRed = new Location(Bukkit.getWorld(vals[0]), Double.valueOf(vals[1]), Double.valueOf(vals[2]), Double.valueOf(vals[3]));
				} else {
					spawnBlue = new Location(Bukkit.getWorld(vals[0]), Double.valueOf(vals[1]), Double.valueOf(vals[2]), Double.valueOf(vals[3]));
				}
			}
			if (line.startsWith("Flag")) {
				String[] vals = line.split("#")[1].split(":");
				Location loc = new Location(Bukkit.getWorld(vals[1]), Double.valueOf(vals[2]), Double.valueOf(vals[3]), Double.valueOf(vals[4]));
				flags.put(vals[0], loc);
			}
		}
		br.close();
	}
	
	public Location getSpawnBlue() {
		return this.spawnBlue;
	}
	
	public Location getSpawnRed() {
		return this.spawnRed;
	}
	
	public HashMap<String,Location> getFlags() {
		return this.flags;
	}
}
