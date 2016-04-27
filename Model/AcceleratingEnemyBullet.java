package Model;

/**
 * Accelerating Enemy Bullet class
 * This class will handle the accelerate of enemy bullet
 * @author anthony on 4/27/16.
 */
public class AcceleratingEnemyBullet extends EnemyBullet {
    private float acceleration;
    /*
    *AcceleratingEnemyBullet Constructor
    *@param x posisition bullet in coordinat x
    *@param y posisition bullet in coordinat y
    *@param radius radius of bullet
    *@param angle angle of bullet
    *@param initialSpeed start speed of bullet
    *@param dmg damage of bullet
    *@param acceleration acceleration of bullet
    */
    public AcceleratingEnemyBullet(int x, int y, int radius, float angle, float initialSpeed, int dmg, float acceleration) {
        super(x, y, radius, angle, initialSpeed, dmg);

        this.acceleration = acceleration;
    }
  /**
    * {@inheritDoc}
    */
    @Override
    public void update(float dt) {
        double tang = Math.toRadians(angle);
        x += Math.cos(tang) * speed * dt;
        y += Math.sin(tang) * speed * dt;
        speed += acceleration / 100;
    }
}
