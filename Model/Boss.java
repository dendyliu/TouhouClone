package Model;

class Boss extends Movable {
	private int health;
	private float currentT;
	private float initialX;
	private float initialY;

	public Boss(int x, int y, int health){
		super(x,y,80,0,0);
		this.health = health;
		currentT = 0;
		initialX = x;
		initialY = y;
	}
	
	public void update(float dt){
		currentT += dt;
		float a = 5;
		x = (float) ((a*Math.sqrt(2)*Math.cos(currentT)) / (Math.sin(currentT)*Math.sin(currentT) +1) + initialX);
		y = (float) ((a*Math.sqrt(2)*Math.cos(currentT)*Math.sin(currentT)) / (Math.sin(currentT)*Math.sin(currentT) +1));
	}
	
	public int getHealth(){
		return health;
	}
	
	public void interact(Movable m){
		if( m instanceof PlayerBullet ){
			PlayerBullet b = (PlayerBullet) m;
			health -= b.damage;
		}
	}
}