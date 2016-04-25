package Model.Event;

import java.util.TimerTask;
import Model.Bullet;
import java.util.Vector;

public abstract class Event extends TimerTask {
	protected Vector<Bullet> bullets;
	protected Object self;

	public Event(Object s){
		self = s;
	}
	
	public void clean(){
		for( Bullet b : bullets ){
			b.forceKill();
		}
	}
}