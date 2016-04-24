package Model;

class Boss extends Moveable {
	private int health;
	
	public Boss(int x, int y, int health){
		super(x,y,80,0,0);
		this.health = health;
	}
	
	public void update(float dt){
		
	}
	
	public int getHealth(){
		return health;
	}
	
	public void interact(Moveable m){
		if( m instanceof PlayerBullet ){
			health -= m.damage;
		}
	}
}