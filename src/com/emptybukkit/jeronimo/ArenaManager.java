package com.emptybukkit.jeronimo;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class ArenaManager {

	static Jeronimo plugin;

	public ArenaManager(Jeronimo Jeronimo) {
		plugin = Jeronimo;
	}

	private static ArenaManager am = new ArenaManager(plugin);

	public static ArenaManager getManager() {
		return am;
	}

	// ==========================================================
	// createArena
	// ==========================================================
	public void createArena(String arenaName, int maxPlayers, int minPlayers,
			Location jumpLoc, Location lobbyLoc) {

		Arena arena = new Arena(arenaName, maxPlayers, minPlayers, jumpLoc,
				lobbyLoc);
	}

	// ==========================================================
	// getArena
	// --------
	// Method for getting arena by name
	// ==========================================================
	public Arena getArena(String name) {
		for (Arena a : Arena.arenaObjects) {
			if (a.getArenaName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}

	// ==========================================================
	// addPlayers
	// Method for adding players to a arena
	// ==========================================================
	public void addPlayers(final UUID uuid, final String arenaName) {
		if (getArena(arenaName) != null) {
			final Arena arena = getArena(arenaName);

			// check id uuid is already in a game
			for (Arena a : Arena.arenaObjects) {
				for (Map.Entry<UUID, Integer> players : a.getPlayers()
						.entrySet()) {
					if (uuid == players.getKey()) {
						Bukkit.getPlayer(uuid).sendMessage(
								"You are already in " + a.getArenaName());
						return;
					}
				}
			}

			// check if game is already full
			if (!arena.isFull()) {
				// check if game has already started
				if (!arena.isInGame()) {

					// main addPlayers functions
					arena.getPlayers().put(uuid, 0);

				} else {
					Bukkit.getPlayer(uuid).sendMessage(
							arena.getArenaName() + " has already started!");
					return;
				}
			} else {
				Bukkit.getPlayer(uuid).sendMessage(
						arena.getArenaName() + " is full!");
				return;
			}
		}
	}

	// ==========================================================
	// removePlayers
	// ==========================================================
	public void removePlayer(UUID uuid, String arenaName) {
		if (getArena(arenaName) != null) {
			Arena arena = getArena(arenaName);

			if (arena.getPlayers().containsKey(uuid)) {

				arena.getPlayers().remove(uuid);
			}
		}

	}

	// ==========================================================
	// endArena
	// ==========================================================
	public void endArena(String arenaName) {

		if (getArena(arenaName) != null) {
			Arena arena = getArena(arenaName);
			arena.setInGame(false);

			for (Map.Entry<UUID, Integer> players : arena.getPlayers()
					.entrySet()) {
				this.removePlayer(players.getKey(), arenaName);
			}

		}

	}

	// ==========================================================
	// setupPlayers
	// Setup player when joining a game
	// ==========================================================
	@SuppressWarnings("deprecation")
	public void setupPlayer(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid);
		if (player.isOnline()) {
			player.getInventory().clear();
			player.setHealth(20);
			player.setFireTicks(0);
			player.setGameMode(GameMode.SURVIVAL);
			player.setFlying(false);
			player.getEquipment().clear();
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			player.setExhaustion(0);
			player.setLevel(0);
			player.setExp(0);
		}
	}

}
