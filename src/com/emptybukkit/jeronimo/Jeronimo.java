package com.emptybukkit.jeronimo;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Jeronimo extends JavaPlugin {

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		getLogger().info("===== Jeronimo Plugin has been enabled =====");
	}

}
