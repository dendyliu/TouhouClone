package Controller.Event;

import Model.Boss;
import Model.EnemyBullet;
import View.Battlefield;

/**
 * Created by Candra Ramsi on 4/25/16.
 */

public class FlowerEvent extends Event {

	private final int spreadCount;
	private final float speed;
	private final float startingAngle;

	public FlowerEvent(Object s, int spreadCount, float startingAngle, float speed){
		super(s);
		this.spreadCount = spreadCount;
		this.speed = speed;
		this.startingAngle = startingAngle;
	}
	
	public void run(){
		Battlefield b = (Battlefield) self;
		Boss boss = b.getBoss();
		
		for( float a = startingAngle; a < 360 + startingAngle; a += 360 / spreadCount ){
			b.add(new EnemyBullet(boss.getX(), boss.getY(), 7, a, speed, 10));
		}
	}
}