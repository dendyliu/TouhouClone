package Model;

// Author : Candra Ramsi

public abstract class Moveable {	
	private boolean alive;
	protected float x;
	protected float y;
	protected float radius;
	protected float angle; // in degree
	protected float speed; // in pixel
	
	public Moveable(int x, int y, int radius, float angle, float speed){
		alive = true;
		
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
		this.speed = speed;
	}

	public void update(float dt){
		double tang = Math.toRadians(angle);
		x += Math.cos(tang) * speed * dt;
		y += Math.sin(tang) * speed * dt;
	}
	
	public abstract void interact(Moveable m);

	public void forceKill(){ alive = false; }
	public boolean isDead(){ return !alive; }
	public boolean isAlive(){ return alive; }
	public float getAngle(){ return angle; }
	public float getSpeed(){ return speed; }
	public int getX(){ return new Float(x).intValue(); }
	public int getY(){ return new Float(y).intValue(); }

	public float getRadius() {
		return radius;
	}
}
