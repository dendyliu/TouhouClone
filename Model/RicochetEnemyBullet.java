package Model;

/**
 * Ricochet Enemy Bullet class
 * This class represent RicochetEnemt bullet
 * @author anthony on 4/27/16.
 */
public class RicochetEnemyBullet extends EnemyBullet {
    private final int barrierRadius = 5;
    private int leftBarrier;
    private int rightBarrier;
    private int bounceLimit = 2;
    /*
     *RicochetEnemyBullet Constructor
     *@param x posisition bullet in coordinat x
     *@param y posisition bullet in coordinat y
     *@param radius radius of bullet
     *@param angle angle of bullet
     *@param speed speed of bullet
     *@param dmg damage of bullet
     *@param leftBarrier left barrier of bullet
     *@param rightBarrier right barrier of bullet
      */ 
    public RicochetEnemyBullet(int x, int y, int radius, float angle, float speed, int dmg, int leftBarrier, int rightBarrier) {
        super(x, y, radius, angle, speed, dmg);
        this.leftBarrier = leftBarrier;
        this.rightBarrier = rightBarrier;
    }
    /**
     * {@inheritDoc}
     */	
    @Override
    public void update(float dt) {
        while (angle < 0)
            angle += 360;
        while (angle > 360)
            angle -= 360;

        if (leftBarrier + barrierRadius > x) {
            if (bounceLimit > 0) {
                bounceLimit--;
                if (angle < 180 && angle > 90) {
                    angle = 180 - angle;
                } else if (angle > 180 && angle < 270) {
                    angle = 360 - angle + 180;
                }
            }
        }
        if (rightBarrier - barrierRadius < x) {
            if (bounceLimit > 0) {
                bounceLimit--;
                if (angle < 90 && angle > 0) {
                    angle += 180 - 2 * angle;
                } else if (angle > 270 && angle < 360) {
                    angle = 360 - angle + 180;
                }
            }
        }
        super.update(dt);
    }
}
