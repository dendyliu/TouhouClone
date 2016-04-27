package Model;
/**
 * Player Bullet class
 * This class represent the player bullet
 * @author Anthony on 4/24/16.
 */

public class PlayerBullet extends Bullet {
        /*
        * PlayerBullet Constructor
        *@param x posisition bullet in coordinat x
        *@param y posisition bullet in coordinat y
        *@param radius radius of bullet
        *@param angle angle of bullet
        *@param speed speed of bullet
        *@param dmg damage of bullet
         */ 
	public PlayerBullet(int x, int y, int radius, float angle, float speed, int dmg){
		super(x,y,radius,angle,speed,dmg);
	}
	/**
        * {@inheritDoc}
        */	
	@Override
	public void update(float dt){
		super.update(dt);
	}
	/**
        * {@inheritDoc}
        */
	@Override
	public void interact(Movable m){
		
	}
}