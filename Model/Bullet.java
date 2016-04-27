package Model;


public class Bullet extends Movable {
	public final int damage;

	public Bullet(int x, int y, int radius, float angle, float speed, int dmg){
		super(x,y,radius,angle,speed);
		damage = dmg;
	}
		
	@Override
	public void update(float dt){
		super.update(dt);
	}

        @Override
	public void interact(Movable m){
		
	}
}