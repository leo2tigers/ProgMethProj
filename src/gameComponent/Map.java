package gameComponent;

import java.util.ArrayList;

public class Map {
	public String name;
	
	protected boolean gravity;
	
	protected Player player;
	
	protected ArrayList<Monster> monsters;
	
	protected ArrayList<Platform> platforms;
	
	protected ArrayList<SpawnPoint> spawnPoints;
	
	public Map() {
		this.name = "";
		this.monsters = new ArrayList<Monster>();
		this.platforms = new ArrayList<Platform>();
		this.spawnPoints = new ArrayList<SpawnPoint>();
		System.out.println("GENERATE MAP " + this.name);
	}
	
	public Map(String name) {
		this.name = name;
		this.monsters = new ArrayList<Monster>();
		this.platforms = new ArrayList<Platform>();
		this.spawnPoints = new ArrayList<SpawnPoint>();
		System.out.println("GENERATE MAP " + this.name);
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}
	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}
	
	public ArrayList<SpawnPoint> getSpawnPoints() {
		return spawnPoints;
	}

	public void setSpawnPoints(ArrayList<SpawnPoint> spawnPoints) {
		this.spawnPoints = spawnPoints;
	}

	public void addMonster(Monster monster) {
		this.monsters.add(monster);
	}
	
	public void addSpawnPoint(SpawnPoint spawnPoint) {
		this.spawnPoints.add(spawnPoint);
	}
	
	public void spawnFromSpawnPoint(int index) {
		this.monsters.add(
				this.spawnPoints.get(index).spawn()
				);
	}
	
	public void enableGravity() {
		this.gravity = true;
	}
	
	public void disableGravity() {
		this.gravity = false;
	}
	
	public boolean isGravityEnabled() {
		return this.gravity;
	}
}
