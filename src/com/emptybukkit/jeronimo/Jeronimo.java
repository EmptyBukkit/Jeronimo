package com.emptybukkit.jeronimo;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Jeronimo extends JavaPlugin {

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		getLogger().info("===== Jeronimo Plugin has been enabled =====");

		getCommand("leave").setExecutor(new CommandListener());
		getCommand("join").setExecutor(new CommandListener());
		getCommand("jlist").setExecutor(new CommandListener());

		World world = Bukkit.getWorld("world");
		
		ArenaManager.getManager().createArena("a", 2, 1, new Location(world,-243,79,213,0,0), new Location(world,-276,71,215,0,0));
	}

}
