package Model;

// Author : Candra Ramsi

public abstract class Moveable {	
	private boolean alive;
	private float x;
	private float y;
	private float radius;
	private float angle; // in degree
	private float speed; // in pixel
	
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
	public int getX(){ return x; }
	public int getY(){ return y; }
}
