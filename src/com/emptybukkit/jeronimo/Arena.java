package com.emptybukkit.jeronimo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Arena {

	public static ArrayList<Arena> arenaObjects = new ArrayList<Arena>(); // A
																			// list
																			// of
																			// all
																			// the
																			// Arena
																			// Objects

	// private ArrayList<UUID> players = new ArrayList<UUID>(); // List of
	// player
	// names
	private HashMap<UUID, Integer> players = new HashMap<UUID, Integer>();

	private String arenaName; // Name of the arena
	private int maxPlayers; // Max players for this arena
	private int minPlayers; // Min players for this arena
	private boolean inGame = false; // Has game started
	private Location jumpLoc; // Where players jump from
	private Location lobbyLoc; // where players will spawn

	// constructor
	Arena(String arenaName, int maxPlayers, int minPlayers, Location jumpLoc,
			Location lobbyLoc) {
		this.arenaName = arenaName;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;
		this.jumpLoc = jumpLoc;
		this.lobbyLoc = lobbyLoc;
	}

	// GETTERS
	// ========================================================
	// return this arena name
	public String getArenaName() {
		return this.arenaName;
	}

	// return max players for this arena
	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	// return min players for this arena
	public int getMinPlayers() {
		return this.minPlayers;
	}

	// is arena in game???
	public boolean isInGame() {
		return this.inGame;
	}

	// return list of players by name
	public HashMap<UUID, Integer> getPlayers() {
		return this.players;
	}

	// return is arena full
	public boolean isFull() {
		if (this.players.size() >= this.maxPlayers) {
			return true;
		} else {
			return false;
		}
	}

	// return jump location
	public Location getJumpLoc() {
		return this.jumpLoc;
	}

	// return spawn location
	public Location getLobbyLoc() {
		return this.lobbyLoc;
	}

	// SETTERS
	// ========================================================
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	// send all players message
	public void sendMessage(String message) {
		for (Map.Entry<UUID, Integer> players : this.getPlayers().entrySet()) {
			Bukkit.getPlayer(players.getKey()).sendMessage(message);
		}
	}

}
