package gameComponent;

import java.util.ArrayList;

public class Map {
	public String name;
	public boolean gravityEnabled;
	
	public Player player;
	
	public ArrayList<Monster> monsters;
	public ArrayList<Platform> platforms;
	public ArrayList<SpawnPoint> spawnPoints;
	
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
	
}
