package collider;

import math.Box;
import math.LinearEquation;
import math.Vector;

public class RectangleCollider extends Collider {	
	protected Vector[] points;
	
	public RectangleCollider() {
		super("RectangleCollider");
		
		this.points = new Vector[] {
				Vector.Origin,
				Vector.Origin,
				Vector.Origin,
				Vector.Origin
		};
	}
	
	public RectangleCollider(Vector upperLeft, Vector upperRight, Vector lowerLeft, Vector lowerRight) {
		super("RectangleCollider");
		
		
		this.points = new Vector[] {
				upperLeft,
				upperRight,
				lowerLeft,
				lowerRight
		};
	}
	
	public RectangleCollider(Box box, Vector center) {
		super("RectangleCollider");
		
		this.points = new Vector[] {
				new Vector(center.x - box.width/2, center.y + box.height/2),
				new Vector(center.x + box.width/2, center.y + box.height/2),
				new Vector(center.x - box.width/2, center.y - box.height/2),
				new Vector(center.x + box.width/2, center.y - box.height/2)
		};
	}

	public Vector[] getPoints() {
		return points;
	}

	public void setPoints(Vector... points) {
		if (points.length == 4) {
			this.points = points;
		}
	}
	
	public Vector getCenter() {
		Vector center = Vector.Origin;
		
		for (Vector point : this.points) {
			center = Vector.add(center, point);
		}
		center = Vector.multiply(center, 0.25);
		
		return this.center;
	}
	
	public double[] getDimension() {
		return new double[] {
				this.points[1].x - this.points[0].x, // x
				this.points[0].y - this.points[2].y, // y
				this.points[1].y - this.points[0].y, // delta y
				this.points[2].x - this.points[1].x, // delta x
		};
	}
	
	public void scale(double scale) {
		Vector center = this.getCenter();
		for (int i = 0; i < 4; i ++) {
			points[i] = Vector.add(points[i], Vector.multiply(Vector.subtract(points[i], center), scale - 1));
		}
	}
	
	public void scale(double scale, Vector ref) {
		for (int i = 0; i < 4; i ++) {
			points[i] = Vector.add(points[i], Vector.multiply(Vector.subtract(points[i], ref), scale - 1));
		}
	}
	
	protected LinearEquation[] border() {
		LinearEquation upper = LinearEquation.line(points[0], points[1]);
		LinearEquation right = LinearEquation.line(points[2], points[0]);
		LinearEquation left = LinearEquation.line(points[3], points[1]);
		LinearEquation lower = LinearEquation.line(points[2], points[3]);
		return new LinearEquation[] {upper, left, right, lower};
	}
	
	protected boolean innerPoint(Vector point) {
		LinearEquation[] line = this.border();
		return (line[0].compute(point) >= 0)  && (line[1].compute(point) <= 0) && (line[2].compute(point) >= 0) && (line[3].compute(point) <= 0);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void translate(Vector vector) {
		for (int i = 0; i< 4; i ++) {
			this.points[i] = Vector.add(this.points[i], vector);
		}
	}
	
	@Override
	public boolean overlap(Collider other) {
		boolean isOverlapped = false;
		if(other instanceof RectangleCollider) {
			
			// inner points
			
			for (Vector point : this.getPoints()) {
				isOverlapped = (isOverlapped || other.innerPoint(point));
			}
			for (Vector point : other.getPoints()) {
				isOverlapped = (isOverlapped || this.innerPoint(point));
			}
			
			// fuck overlap
			
			boolean betweenPoints = true;
			Vector[] first = this.getPoints();
			Vector[] second = other.getPoints();
			for (Vector point : first) {
				betweenPoints = (
						betweenPoints 
						&& point.y < Math.min(second[0].y, second[1].y) 
						&& point.y > Math.max(second[2].y, second[3].y)
						);
			}
			for (Vector point : second) {
				betweenPoints = (
						betweenPoints 
						&& point.x > Math.max(first[0].x, first[2].x) 
						&& point.x < Math.min(first[1].x, first[3].x)
						);
			}
			
			isOverlapped = isOverlapped || betweenPoints;
			
			betweenPoints = true;
			first = other.getPoints();
			second = this.getPoints();
			for (Vector point : first) {
				betweenPoints = (
						betweenPoints 
						&& point.y < Math.min(second[0].y, second[1].y) 
						&& point.y > Math.max(second[2].y, second[3].y)
						);
			}
			for (Vector point : second) {
				betweenPoints = (
						betweenPoints 
						&& point.x > Math.max(first[0].x, first[2].x) 
						&& point.x < Math.min(first[1].x, first[3].x)
						);
			}
			
			isOverlapped = isOverlapped || betweenPoints;
		}
		else if(other instanceof CircleCollider) {
			//
		}
		else {
			return false;
		}
		return isOverlapped;
	}
}
