package test;

import java.util.ArrayList;

import collider.*;
import gameComponent.*;
import math.*;

public class test {
	public static void main(String[] args) {
		// Maps
		Map map = new Map("0x01");
		map.gravityEnabled = false;
		
		// Player
		Player player = new Player(1);
		map.player = player;
		player.name = "player01";
		player.setPosition(Vector.Origin);
		player.orientation = new Vector(1, 0);
		player.setMass(50);
		player.hitBox = new RectangleCollider(
				new Box(2, 2), 
				Vector.add(
						player.getPosition(), 
						new Vector(0, 1)
						)
				);
		player.currentMap = map;
		player.show();
		
		map.addSpawnPoint(new SpawnPoint(MonsterType.AMI, new Vector(2, 0), false));
		
		// Monster
		map.spawnFromSpawnPoint(0);
		Monster monster = map.monsters.get(0);
		monster.name = "monster01";
		monster.orientation = new Vector(-1, 0);
		monster.show();
		System.out.println(monster.hitBox);
		
		// Event
		player.attack();
		ArrayList<Integer> deadIndex = new ArrayList<Integer>();
		for(int i = 0; i < map.monsters.size(); i ++) {
			if (!map.monsters.get(i).isAlive) {
				deadIndex.add(i);
			}
		}
		for(int i = 0; i < deadIndex.size(); i ++) {
			map.monsters.remove(deadIndex.get(i) - i);
		}
	}
}
