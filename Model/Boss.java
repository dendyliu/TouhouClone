package Model;

public class Boss extends Movable {
        public static final int RADIUS = 60;
	private int health;
	private float currentT;
	private float initialX;
	private float initialY;
		
	public Boss(int x, int y, int health){
		super(x,y,RADIUS,0,0);
		this.health = health;
		currentT = 0;
		initialX = x;
		initialY = y;
	}
	
	public void update(float dt){
		currentT += dt;
		float a = 100;
		float timeFactor = 0.5f;
		float t = currentT * timeFactor;
		x = (float) ((a*Math.sqrt(2)*Math.cos(t)) / (Math.sin(t)*Math.sin(t) +1) + initialX);
		y = (float) ((a*Math.sqrt(2)*Math.cos(t)*Math.sin(t)) / (Math.sin(t)*Math.sin(t) +1)) + initialY;
		
		if( getHealth() < 0 ) forceKill();
	}
	
	public int getHealth(){
		return health;
	}
	
	public void interact(Movable m){
		if (m instanceof PlayerBullet) {
			PlayerBullet b = (PlayerBullet) m;
			health -= b.damage;
			b.forceKill();
			System.out.println(health);
		}
	}
}