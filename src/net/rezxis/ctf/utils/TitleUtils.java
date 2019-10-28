package net.rezxis.ctf.utils;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class TitleUtils {

	public static void sendTitle(String text, ChatColor color, Player p, int a, int b, int c) {
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\",color:" + color.name().toLowerCase() + "}");
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(a,b,c);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(length);
	}
}
