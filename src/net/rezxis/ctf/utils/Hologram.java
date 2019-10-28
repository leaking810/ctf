package net.rezxis.ctf.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Hologram {
	
	public static ArrayList<ArmorStand> entities = new ArrayList<>();
	private Location loc;
	private String text;
	private ArmorStand entity;
	private ItemStack head;
	
	public Hologram(String text, Location loc, ItemStack head) {
		this.text = text;
		this.loc = loc;
		this.head = head;
	}
	
	public void show() {
		ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		as.setGravity(false);
		as.setCanPickupItems(false);
		as.setCustomName(text);
		as.setCustomNameVisible(true);
		as.setVisible(false);
		as.setHelmet(head);
		this.entity = as;
		entities.add(as);
	}
	
	public void destory() {
		entities.remove(this.entity);
		entity.remove();
	}
	
	public void update(String text) {
		this.text = text;
		this.update();
	}
	
	public void updateAsync(String text) {
		this.text = text;
	}
	
	public void update() {
		this.entity.setCustomName(text);
	}
	
	public void setHead(ItemStack is) {
		this.entity.setHelmet(is);
	}
	
	public static void removeAll() {
		for (ArmorStand as : entities) {
			as.remove();
		}
		entities.clear();
	}
}
