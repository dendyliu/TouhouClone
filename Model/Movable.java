package Model;

/**
 * Movable class 
 * This class represent the object that can move
 * @author Candra Ramsi on 4/24/16.
 */
public abstract class Movable {
	private boolean alive;
	protected float x;
	protected float y;
	protected float radius;
	protected float angle; // in degree
	protected float speed; // in pixel
        /*
	*Movable Constructor
        *@param x posisition object in coordinat x
        *@param y posisition object in coordinat y
        *@param radius radius of object
        *@param angle angle of object
        *@param speed speed of object
         */   
	public Movable(int x, int y, int radius, float angle, float speed){
		alive = true;
		
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
		this.speed = speed;
	}
        /*
        * Do the update 
        * @param dt delay time to do the update
        */
	public void update(float dt){
		double tang = Math.toRadians(angle);
		x += Math.cos(tang) * speed * dt;
		y += Math.sin(tang) * speed * dt;
	}
	/*
        * Interaction between movable object
        * @param m Movable object
        */
	public abstract void interact(Movable m);
        /*
        * force to kill this object
        */
	public void forceKill(){ alive = false; }
        /*
        * Return true if this object is dead
        * @return boolean containing isDead
        */
	public boolean isDead(){ return !alive; }
         /*
        * Return true if this object is dead
        * @return boolean containing isDead
        */
	public boolean isAlive(){ return alive; }
         /*
        * Return true if this object is alive
        * @return boolean containing isAlive
        */
	public float getAngle(){ return angle; }
         /*
        * Return Angle of this object
        * @return float containing angle
        */
	public float getSpeed(){ return speed; }
         /*
        * Return speed of this object
        * @return float containing speed
        */
	public float getRadius(){ return radius; }
         /*
        * Return posisition in coordinat x of this object
        * @return int containing posistion in coordinat x
        */
	public int getX(){ return new Float(x).intValue(); }
         /*
        * Return posisition in coordinat y of this object
        * @return int containing posistion in coordinat y
        */
	public int getY(){ return new Float(y).intValue(); }
}
