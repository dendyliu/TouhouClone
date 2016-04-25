package Model.Event;

import Model.Bullet;
import Model.Boss;
import Model.Battlefield;

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
			b.add( new Bullet(boss.getX(),boss.getY(),10,a,100,10) );
		}
	}
}