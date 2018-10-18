package gameComponent;

import math.Vector;

abstract public class GameComponent {
	public Vector position;
	
	public GameComponent() {}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
}
