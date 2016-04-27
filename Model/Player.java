package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Player class
 * This class represent player
 * @author Anthony on 4/24/16.
 */

public class Player extends Movable implements KeyListener {
	private int health;
	private Set<Character> currentKeyPressed;
	private boolean onFiringState;
	private boolean justDamaged;
	private boolean visible;
	/*
	*Player Constructor
        *@param x posisition player in coordinat x
        *@param y posisition player in coordinat y
         */   
	public Player(int x, int y){
		super(x, y, 5, 0, 100);

		health = 100;
		currentKeyPressed = new HashSet<>();
		onFiringState = false;
		justDamaged = false;
		visible = true;
	}
        /*Return true if player is visible
        *@return boolean contain visible
        */
	public boolean isVisible() {
		return visible;
	}
        /*Return true if player is on firing
        * @return boolean contain isOnFiringState
        */
	public boolean isOnFiringState() {
		return onFiringState;
	}
	/**
        * {@inheritDoc}
        */	
	public void update(float dt){
		onFiringState = currentKeyPressed.contains(' ');
		if (currentKeyPressed.contains('w')) {
			y -= speed * dt;
		}
		if (currentKeyPressed.contains('a')) {
			x -= speed * dt;
		}
		if (currentKeyPressed.contains('s')) {
			y += speed * dt;
		}
		if (currentKeyPressed.contains('d')) {
			x += speed * dt;
		}
	}
	/*
        * Fix coodinate posisition of player
        */
	public void fixCoordinate(int boundWidth, int boundHeight){
		if( x < 0 ) 
			x = 0;
		else if( x >= boundWidth)
			x = boundWidth - 1;
		
		if( y < 0 ) 
			y = 0;
		else if( y >= boundHeight )
			y = boundHeight - 1;
	}
	
	public int getHealth(){
		return health;
	}
	/**
        * {@inheritDoc}
        */	
	public void interact(Movable m){
		if (m instanceof EnemyBullet || m instanceof Boss ) {
			if (!justDamaged) {
				justDamaged = true;
				Timer blinkingTimer = new Timer();
				blinkingTimer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						visible = !visible;
					}
				}, 0, 200);

				Timer justDamagedTimer = new Timer();
				justDamagedTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						blinkingTimer.cancel();
						justDamaged = false;
						visible = true;
					}
				}, 1000);

                                if( m instanceof EnemyBullet ){
                                    EnemyBullet b = (EnemyBullet) m;
                                    health -= b.damage;
                                    b.forceKill();
                                }
                                else { // boss
                                    health -= 20;
                                }
                                
				if (health < 0)
					forceKill();
			}
		}
	}
        /**
        * {@inheritDoc}
        */	
	@Override
	public void keyTyped(KeyEvent e) {

	}
        /**
        * {@inheritDoc}
        */	
	@Override
	public void keyPressed(KeyEvent e) {
		currentKeyPressed.add(e.getKeyChar());
	}
        /**
        * {@inheritDoc}
        */	
	@Override
	public void keyReleased(KeyEvent e) {
		currentKeyPressed.remove(e.getKeyChar());
	}
}