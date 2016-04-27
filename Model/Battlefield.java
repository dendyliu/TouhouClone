package Model;

import Model.Event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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


	public Battlefield() {

		width = 600;
		height = 800;
		mList = new Vector<>();
		assetLoader = new AssetLoader();
		scheduledPool = Executors.newScheduledThreadPool(1);

		frame = new JFrame("Touhou Clone");
		frame.setSize(600, 800);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		
		Boss boss = new Boss(200, 200, 2000);
		add(boss);
		Player player = new Player(200, 500);
		add(player);
		Runnable task2 = () -> frame.addKeyListener(player);

		new Thread(task2).start();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {	
				super.windowClosing(e);
				System.exit(0);
			}
		});

		Battlefield b = this;
		Timer timer = new Timer(20, e -> new UpdateEvent(b, 0.02f).run());
		timer.start();
		scheduledPool.scheduleWithFixedDelay(new PlayerFiringEvent(this, 0.2f), 30, 20, TimeUnit.MILLISECONDS);

		int t = 0;
		t += 1000;
		for (int i = 0; i < 10; ++i) {
			scheduledPool.schedule(new RainingEvent(this, 10, 50, 0, 300), t, TimeUnit.MILLISECONDS);
			t += 500;
		}
		for (int i = 0; i < 5; ++i) {
			scheduledPool.schedule(new BoomerangShotgunEvent(this, 4, 60, 60, 4, 250), t, TimeUnit.MILLISECONDS);
			t += 500;
		}

		for (int i = 0; i < 1; ++i) {
			scheduledPool.schedule(new RicochetFlowerEvent(this, 100, 50, 100, 0, frame.getWidth()), t, TimeUnit.MILLISECONDS);
			t += 5000;
		}

		for (int i = 0; i < 5; ++i) {
			scheduledPool.schedule(new SpiralEvent(this, 10, i * 360 / 5.f, 1, 0, 100), t, TimeUnit.MILLISECONDS);
			t += 500;
		}
		t += 2000;

		for (int i = 0; i < 10; ++i) {
			t += 200;
			scheduledPool.schedule(new FlowerEvent(this, 10, i * 10.f, 100), t, TimeUnit.MILLISECONDS);
		}

		for( int i = 0; i < 10; ++ i ){
			t += 500;
			scheduledPool.schedule(new FlowerEvent(this,10,-10.f,100), t, TimeUnit.MILLISECONDS);
		}
		
		for( int i = 0; i < 10; ++ i ){
			t += 500;
			scheduledPool.schedule(new FlowerEvent(this,10,10.f,100), t, TimeUnit.MILLISECONDS);
		}

		
		t += 1000;

		for( int i = 0; i < 200; ++ i ){
			scheduledPool.schedule(new FlowerEvent(this,10,10*i + 0.f,100), t, TimeUnit.MILLISECONDS);
			t += 200;
		}

		for (int i = 0; i < 10; ++i) {
			scheduledPool.schedule(new ShotgunEvent(this, 4, 60f, 60f, 4, 100), t, TimeUnit.MILLISECONDS);
			t += 500;
		}
	}
	
	private static int getDistance(Movable a, Movable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}
	
	public boolean isInField(int x, int y) {
		return x >= 0 && y >= 0 && x <= getWidth() && y <= getHeight();

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

	public Player getPlayer() throws Error {
		for (Movable m : mList) {
			if (m instanceof Player)
				return (Player) m;
		}


		throw new Error("no player");
	}
	
	public void update(float dt){
		// updates Movable
		for (int i = 0; i < mList.size(); ++i) {
			Movable it = mList.get(i);
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
                g.drawImage(assetLoader.getBossImage(), x - 42, y - 63,85,127,null);
			else if (it instanceof Player) {
				Player p = ((Player) it);
				if (p.isVisible()){
					int rad = new Float(p.getRadius()).intValue();
					g.drawImage(assetLoader.getPlayerImage(), x - 32, y - 64, 65, 125, null);
					g.drawImage( assetLoader.getBulletImage(2), x - rad, y - rad,rad*2,rad*2,null );
				}
			}
			else if (it instanceof EnemyBullet){
				Bullet b = (Bullet) it;
				int rad = new Float(b.getRadius()).intValue();
				
                g.drawImage( assetLoader.getBulletImage(0), x - rad, y - rad,rad*2,rad*2,null );
			}
			else if (it instanceof PlayerBullet){
				Bullet b = (Bullet) it;
				int rad = new Float(b.getRadius()).intValue();
				
                g.drawImage( assetLoader.getBulletImage(1), x - rad, y - rad,rad*2,rad*2,null );
			}
		}
    }
	
	
}