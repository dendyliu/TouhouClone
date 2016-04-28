package View;

import Controller.Event.UpdateEvent;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Semaphore;
/*
* Battlefield Class
* This class will display Battle Field , place where player will be play
* @author Candra Ramsi on 4/24/16     
*/

public class Battlefield extends JPanel {
	public final int width;
	public final int height;
	private java.util.List<Movable> mList;
	private AssetLoader assetLoader;
	private Boss boss;
	private Player player;
	private Semaphore lock;
	/*
        * BattleField Constructor
        * @param frame frame of Battle Field
        */
	public Battlefield(JFrame frame) {
		lock = new Semaphore(1);
		width = frame.getWidth();
		height = frame.getHeight();
		boss = null;
		player = null;
		
		mList = new Vector<>();
		assetLoader = new AssetLoader();


		Battlefield b = this;
		Timer timer = new Timer(20, e -> new UpdateEvent(b, 0.02f).run());
		timer.start();

	}
        /*
        * Return distance between movable object
        * @return integer containing distance between mmovable object
        */
	private static int getDistance(Movable a, Movable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}

	/*
        * Return width of Battlefield
        * @return int containing width of Battlefield
        */	
	@Override
	public int getWidth() {
		return width;
	}
	/*
        * Return true if coordinat x and y in battlefield
        * @return int containing coordinat x and y that availabel in battlefield
        */
	public boolean isInField(int x, int y) {
		return x >= 0 && y >= 0 && x <= getWidth() && y <= getHeight();

	}
	
	/*
        * Add movable object to battlefield
        */
	public synchronized void add(Movable m) {
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if( m instanceof Boss )
			boss = (Boss) m;
		else if( m instanceof Player )
			player = (Player) m;

		mList.add(m);
		lock.release();
	}
	/*
        * Return Boss in Battlefield
        * @return Boss containing Boss in Battlefield
        */
	public Boss getBoss() throws Error {
		if( boss == null )
			throw new Error("no boss");

		return boss;
	}
        /*
        * Return Player in Battlefield
        * @return Player containing Player in Battlefield
        */
	public Player getPlayer() throws Error {
		if( player == null )
			throw new Error("no player");

		return player;
	}
	/*
        * Return true if game is over
        * @return boolean containing game over
        */
	public boolean isGameOver(){
		return getPlayer().isDead() || getBoss().isDead();
	}
        /*
        * Do the update
        */
	public synchronized void update(float dt){
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if( isGameOver() ){
 			return;	
		}
		
		// updates Movable
		for (int i = 0; i < mList.size(); ++i) {
			Movable it = mList.get(i);
			it.update(dt);
		}
		
		Player player = getPlayer();
		player.fixCoordinate(getWidth(), getHeight());

		
		// interact when collide
		for( int i = 0; i < mList.size(); ++ i ){
			Movable first = mList.get(i);
			if( first.isDead() ) continue;
			
			for( int k = i + 1; k < mList.size(); ++ k ){
				Movable second = mList.get(k);
				if( second.isDead() ) continue;

				if( Math.sqrt(getDistance(first,second)) < first.getRadius() + second.getRadius()){
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
		lock.release();
	}
	
        /*
        * paint the components in battlefield
        */
	@Override
	protected synchronized void paintComponent(Graphics g) {
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.paintComponent(g);

		g.setColor(Color.white);
		g.drawImage(assetLoader.getBackgroundImage(), 0, 0, null);

		for (int i = 0; i < mList.size(); ++i) {
			Movable it = mList.get(i);
            int x = it.getX();
            int y = it.getY();

            if( it.isDead() ) continue;

            if( it instanceof Boss )
                g.drawImage(assetLoader.getBossImage(), x - 42, y - 63,85,127,null);
			else if (it instanceof Player) {
				Player p = ((Player) it);
				if (p.isVisible()){
					int rad = new Float(p.getRadius()).intValue();
					g.drawImage(assetLoader.getPlayerImage(), x - 32, y - 64, 65, 125, null);
					g.drawImage( assetLoader.getBulletImage(2), x - rad*3/2, y - rad*3/2,rad*3,rad*3,null );
				}
			}
			else if (it instanceof EnemyBullet){
				Bullet b = (Bullet) it;
				int rad = new Float(b.getRadius()).intValue();
				
                g.drawImage( assetLoader.getBulletImage(0), x - rad*3/2, y - rad*3/2,rad*6/2,rad*6/2,null );
			}
			else if (it instanceof PlayerBullet){
				Bullet b = (Bullet) it;
				int rad = new Float(b.getRadius()).intValue();
				
                g.drawImage( assetLoader.getBulletImage(1), x - rad*3/2, y - rad*3/2,rad*6/2,rad*6/2,null );
			}
		}
		lock.release();

	}
	
	
}