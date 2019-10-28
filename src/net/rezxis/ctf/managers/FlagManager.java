package net.rezxis.ctf.managers;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.rezxis.ctf.objects.FlagObject;
import net.rezxis.ctf.objects.MapConfig;
import net.rezxis.ctf.utils.MapUtils;

public class FlagManager {

	private ArrayList<FlagObject> flags;
	
	public FlagManager() {
		this.flags = new ArrayList<>();
	}
	
	public ArrayList<FlagObject> getFlags() {
		return this.flags;
	}
	
	public void initMap(MapConfig conf) {
		for (Entry<String,Location> f : conf.getFlags().entrySet()) {
			FlagObject fObj = new FlagObject(f.getKey(),f.getValue());
			flags.add(fObj);
			MapUtils.initFlag(fObj);
		}
	}
}
