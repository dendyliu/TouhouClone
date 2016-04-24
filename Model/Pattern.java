package Model;

import java.util.Vector;

public abstract class Pattern {
	private int lifetimeInMillis = 20000;
	protected int defaultRadius = 5;
	protected float defaultSpeed = 5;
	protected int defaultDmg = 50;

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	protected Vector<Bullet> bullets;
	
	public Pattern(){
		bullets = new Vector<>();
	}
	
	public abstract void startAt(float x, float y);

	public void lifetimeCountdown() {
		try {
			Thread.sleep(lifetimeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for( Bullet it : bullets ){
			it.forceKill();
		}
	}
	
}