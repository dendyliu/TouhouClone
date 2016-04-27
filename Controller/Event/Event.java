package Controller.Event;

import Model.EnemyBullet;

import java.util.Vector;

/**
 * Event Class
 * This class will handle all event in this game
 *  @author Candra Ramsi on 4/24/16.
 */


public abstract class Event implements Runnable  {
	protected Vector<EnemyBullet> enemyBullets;
	protected Object self;
     /**
     * Event Constuctor
     * @param s object that do the event
     */
	public Event(Object s){
		self = s;
	}
    /**
     *  clean the enemyBullets
     */
	public void clean(){
		for (EnemyBullet b : enemyBullets) {
			b.forceKill();
		}
	}
}