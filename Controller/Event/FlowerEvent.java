package Controller.Event;

import Model.Boss;
import Model.EnemyBullet;
import View.Battlefield;

/**
 * Flower Event class
 * This class will handle Flower Event.
 * @author Candra Ramsi on 4/25/16.
 */

public class FlowerEvent extends Event {

	private final int spreadCount;
	private final float speed;
	private final float startingAngle;
        /**
        * FlowerEvent Constuctor
        * @param s object that do the event
        * @param spreadCount count the spread of flower bullet
        * @param startingAngle start angle from the flower spread
        * @param speed speed of flower bullet move
        */
	public FlowerEvent(Object s, int spreadCount, float startingAngle, float speed){
		super(s);
		this.spreadCount = spreadCount;
		this.speed = speed;
		this.startingAngle = startingAngle;
	}
	/*
        * Run the Flower Event
        */
	public void run(){
		Battlefield b = (Battlefield) self;
		Boss boss = b.getBoss();
		
		for( float a = startingAngle; a < 360 + startingAngle; a += 360 / spreadCount ){
			b.add(new EnemyBullet(boss.getX(), boss.getY(), 7, a, speed, 10));
		}
	}
}