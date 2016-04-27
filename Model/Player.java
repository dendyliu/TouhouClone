package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Movable implements KeyListener {
	private int health;
	private Set<Character> currentKeyPressed;
	private boolean onFiringState;
	private boolean justDamaged;
	private boolean visible;

	public Player(int x, int y){
		super(x, y, 10, 0, 10);

		health = 100;
		currentKeyPressed = new HashSet<>();
		onFiringState = false;
		justDamaged = false;
		visible = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isOnFiringState() {
		return onFiringState;
	}
	
	public void update(float dt){
		onFiringState = currentKeyPressed.contains(' ');
		if (currentKeyPressed.contains('w')) {
			y -= speed;
		}
		if (currentKeyPressed.contains('a')) {
			x -= speed;
		}
		if (currentKeyPressed.contains('s')) {
			y += speed;
		}
		if (currentKeyPressed.contains('d')) {
			x += speed;
		}
	}
	
	public int getHealth(){
		return health;
	}
	
	public void interact(Movable m){
		if (m instanceof EnemyBullet) {
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
				}, 3000);

				EnemyBullet b = (EnemyBullet) m;
				health -= b.damage;
				b.forceKill();
				if (health < 0)
					forceKill();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentKeyPressed.add(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentKeyPressed.remove(e.getKeyChar());
	}
}