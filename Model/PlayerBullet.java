package Model;
/**
 * Created by Anthony on 4/26/16.
 */

public class PlayerBullet extends Bullet {
	
	public PlayerBullet(int x, int y, int radius, float angle, float speed, int dmg){
		super(x,y,radius,angle,speed,dmg);
	}
		
	@Override
	public void update(float dt){
		super.update(dt);
	}
	
	@Override
	public void interact(Movable m){
		
	}
}