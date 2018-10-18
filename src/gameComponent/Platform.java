package gameComponent;

import collider.RectangleCollider;
import math.Vector;

public class Platform extends GameComponent {
	public RectangleCollider collider;
	private Vector leftBorder;
	private Vector rightBorder;
	private double thick;
	
	public Platform(Vector position,Vector leftBorder, Vector rightBorder) {
		super();
		this.position = position;
		this.leftBorder = leftBorder;
		this.rightBorder = rightBorder;
		this.collider = new RectangleCollider(
				this.leftBorder,
				this.rightBorder,
				new Vector(this.leftBorder.x, this.leftBorder.y - this.thick),
				new Vector(this.rightBorder.x, this.rightBorder.y - this.thick));
	}
}
