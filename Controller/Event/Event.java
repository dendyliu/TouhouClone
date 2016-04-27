package Controller.Event;

import Model.EnemyBullet;

import java.util.Vector;

/**
 * Created by Candra Ramsi on 4/24/16.
 */


public abstract class Event implements Runnable  {
	protected Vector<EnemyBullet> enemyBullets;
	protected Object self;

	public Event(Object s){
		self = s;
	}
	
	public void clean(){
		for (EnemyBullet b : enemyBullets) {
			b.forceKill();
		}
	}
}