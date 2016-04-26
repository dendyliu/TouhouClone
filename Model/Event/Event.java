package Model.Event;

import Model.Bullet;
import java.util.Vector;
import java.lang.Runnable;

public abstract class Event implements Runnable  {
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