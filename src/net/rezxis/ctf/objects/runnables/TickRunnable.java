package net.rezxis.ctf.objects.runnables;

import org.bukkit.scheduler.BukkitRunnable;

import net.rezxis.ctf.CTFMain;
import net.rezxis.ctf.objects.FlagObject;

public class TickRunnable extends BukkitRunnable {

	@Override
	public void run() {
		for (FlagObject flag : CTFMain.instance.getFlagManager().getFlags()) {
			flag.onTick();
		}
	}
}
