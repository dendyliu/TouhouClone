package Model;

class PlayerBullet extends Bullet {
	
	public PlayerBullet(int x, int y, int radius, float angle, float speed, int dmg){
		super(x,y,radius,angle,speed,dmg);
	}
		
	public void update(float dt){
		
		super(dt);
	}
	
	public void interact(Moveable m){
		
	}
}