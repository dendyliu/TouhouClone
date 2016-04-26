package Model;

import java.util.Vector;

public abstract class Pattern {
	protected int defaultRadius = 5;
	protected float defaultSpeed = 5;
	protected int defaultDmg = 50;
	protected Vector<EnemyBullet> enemyBullets;
	private int lifetimeInMillis = 20000;

	public Pattern(){
		enemyBullets = new Vector<>();
	}

	public Vector<EnemyBullet> getEnemyBullets() {
		return enemyBullets;
	}
	
	public abstract void startAt(float x, float y);

	public void lifetimeCountdown() {
		try {
			Thread.sleep(lifetimeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (EnemyBullet it : enemyBullets) {
			it.forceKill();
		}
	}
	
}