package Model;

/**
 * Created by anthony on 4/27/16.
 */
public class SpiralEnemyBullet extends EnemyBullet {
    private float currentT;
    private int initialX;
    private int initialY;

    public SpiralEnemyBullet(int x, int y, int radius, float angle, float speed, int dmg) {
        super(x, y, radius, angle, speed, dmg);
        currentT = 0;
        initialX = x;
        initialY = y;
    }

    @Override
    public void update(float dt) {
        currentT += dt;
        float a = 50;
        x = (float) (currentT * Math.cos(currentT + Math.toRadians(angle))) * a + initialX;
        y = (float) (currentT * Math.sin(currentT + Math.toRadians(angle))) * a + initialY;
    }
}

