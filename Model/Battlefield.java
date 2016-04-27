package Model;

import Model.Event.UpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Battlefield extends JPanel {
	public final int width;
	public final int height;
	private Vector<Movable> mList;
	private AssetLoader assetLoader;
	private JFrame frame;
	public Battlefield() {

		width = 600;
		height = 800;
		mList = new Vector<>();
		assetLoader = new AssetLoader();

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

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Battlefield b = this;
		Timer timer = new Timer(20, e -> new UpdateEvent(b, 0.02f).run());
		timer.start();

	}

	private static int getDistance(Movable a, Movable b){
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		return dx*dx+dy*dy;
	}

	@Override
	public int getWidth() {
		return width;
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
        
    }
	
	
}