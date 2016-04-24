package Model;

import java.util.Vector;
import java.util.stream.Collectors;

public class Battlefield {
	private static Battlefield battlefield = new Battlefield();

	public final int width;
	public final int height;
	private Vector<Movable> mList;

	public static Battlefield getInstance() { return battlefield; }
	
	private static int getDistance(Movable a, Movable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}
	
	private Battlefield(){
		width = 600;
		height = 800;
		mList = new Vector<>();
	}
	
	public void add(Movable m){
		mList.add(m);
	}

	public void update(float dt){
		// updates Moveable
		for( Movable it : mList ){
			it.update(dt);
		}
		
		// interact when collide
		for( int i = 0; i < mList.size(); ++ i ){
			for( int k = i + 1; k < mList.size(); ++ k ){
				Movable first = mList.get(i);
				Movable second = mList.get(k);
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
				.filter(Movable::isAlive)
				.collect(Collectors.toCollection(Vector::new));
	}
	
	
}