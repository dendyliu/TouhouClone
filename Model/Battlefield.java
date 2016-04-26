package Model;

import Model.Event.FlowerEvent;
import Model.Event.UpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Battlefield extends JPanel {
	public final int width;
	public final int height;
	private Vector<Movable> mList;
	private AssetLoader assetLoader;
	private JFrame frame;
	private ScheduledExecutorService scheduledPool;


	
	private static int getDistance(Movable a, Movable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}
	
	public boolean isInField(int x, int y) {
		return x >= 0 && y >= 0 && x <= getWidth() && y <= getHeight();

	}
	
	public Battlefield(){
		width = 600;
		height = 800;
		mList = new Vector<>();
		assetLoader = new AssetLoader();
		scheduledPool = Executors.newScheduledThreadPool(1);
		
		frame = new JFrame("Touhou Clone");
		frame.setSize(600,800);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		
		
		Boss boss = new Boss(200,200,2000);
		add( boss );
		
		scheduledPool.scheduleWithFixedDelay(new UpdateEvent(this,0.02f), 30, 20, TimeUnit.MILLISECONDS);
		
		int t = 0;
		for( int i = 0; i < 10; ++ i ){
			t += 200;
			scheduledPool.schedule(new FlowerEvent(this,10,i*10.f,100), t, TimeUnit.MILLISECONDS);
		}
		

		for( int i = 0; i < 10; ++ i ){
			t += 200;
			scheduledPool.schedule(new FlowerEvent(this,10,-i*10.f,100), t, TimeUnit.MILLISECONDS);
		}
		
		for( int i = 0; i < 10; ++ i ){
			t += 200;
			scheduledPool.schedule(new FlowerEvent(this,10,i*10.f,100), t, TimeUnit.MILLISECONDS);
		}

		t += 1000;
		
		for( int i = 0; i < 10; ++ i ){
			scheduledPool.schedule(new FlowerEvent(this,10,0.f,100 + i * 10), t, TimeUnit.MILLISECONDS);
			t += 100;
		}
	}
	
	public void add(Movable m){
		mList.add(m);
	}
	
	public Boss getBoss() throws Error {
		for( Movable m : mList ){
			if( m instanceof Boss )
				return (Boss)m;
		}
		
		
		throw new Error("no boss");
	}
	
	public void update(float dt){
		// updates Movable
		for( Iterator<Movable> itr = mList.iterator(); itr.hasNext(); ){
			Movable m = itr.next();
			m.update(dt);
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
		for( Iterator<Movable> itr = mList.iterator(); itr.hasNext(); ){
			Movable m = itr.next();
			if( m.isDead() || !isInField(m.getX(),m.getY()) ){
				itr.remove();
			}
		}
	}
	

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.white);

		for (int i = 0; i < mList.size(); ++i) {
			Movable it = mList.get(i);
            int x = it.getX();
            int y = it.getY();

            if( it.isDead() ) continue;

            if( it instanceof Boss )
                g.drawImage(assetLoader.getBossImage(), x, y,85,127,null);
            else if( it instanceof Player )
                g.drawImage(assetLoader.getPlayerImage(), x, y,65,125,null);
            else if( it instanceof Bullet )
                g.drawImage(assetLoader.getBulletImage(0), x, y, null );
        }
        
    }
	
	
}