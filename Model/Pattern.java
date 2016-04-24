package Model;

import java.util.Vector;

public abstract class Pattern {
	private Vector<Bullet> bullets;
	
	public Pattern(){
		bullets = new Vector<>();
	}
	
	public abstract void start();

	public void kill(){
		for( Bullet it : bullets ){
			it.forceKill();
		}
	}
	
}