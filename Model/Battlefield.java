package Model;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.stream.Collectors;


public class Battlefield extends JPanel {
	private static Battlefield battlefield = new Battlefield();

	public final int width;
	public final int height;
	private Vector<Movable> mList;
	private AssetLoader assetLoader;
	private JFrame frame;


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
		assetLoader = new AssetLoader();

		frame = new JFrame("Touhou Clone");
		frame.setSize(600,800);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
	}
	
	public void add(Movable m){
		mList.add(m);
	}
	
	public void update(float dt){
		// updates Movable
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

		repaint();
	}
	

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.white);
        for( Movable it : mList ){
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