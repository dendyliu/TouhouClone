package Model;

public class Player extends Moveable {
	private int health;
	public Player(int x, int y){
		super(x,y,10,0,0);

		health = 100;
	}
	
	public void update(float dt){
		// input 
	}
	
	public int getHealth(){
		return health;
	}
	
	public void interact(Moveable m){
		if( m instanceof Bullet ){
			Bullet b = m;
			health -= b.damage;
			if( health < 0 )
				forceKill();
		}
	}
}