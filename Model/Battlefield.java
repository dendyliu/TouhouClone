package Model;

import java.util.Vector;
import java.util.stream.Collectors;

public class Battlefield {
	public final int width;
	public final int height;
	private Vector<Moveable> mList;
	
	private static int getDistance(Moveable a, Moveable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}
	
	public Battlefield(){
		width = 600;
		height = 800;
		mList = new Vector<>();
	}
	
	public void add(Moveable m){
		mList.add(m);
	}
	
	public void update(float dt){
		// updates Moveable
		for( Moveable it : mList ){
			it.update(dt);
		}
		
		// interact when collide
		for( int i = 0; i < mList.size(); ++ i ){
			for( int k = i + 1; k < mList.size(); ++ k ){
				Moveable first = mList.get(i);
				Moveable second = mList.get(k);
				if( getDistance(first,second) < first.getRadius() + second.getRadius()){
					first.interact(second);
					if( first.isAlive() && second.isAlive() ){
						second.interact(first);
					}
				}
			}
		}
		
		// clear dead stuff
		mList = mList.stream()
				.filter(Moveable::isAlive)
				.collect(Collectors.toCollection(Vector::new));
	}
	
	
}