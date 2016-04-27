package Model;

/**
 * Created by anthony on 4/27/16.
 */
public class AcceleratingEnemyBullet extends EnemyBullet {
    private float acceleration;

    public AcceleratingEnemyBullet(int x, int y, int radius, float angle, float initialSpeed, int dmg, float acceleration) {
        super(x, y, radius, angle, initialSpeed, dmg);

        this.acceleration = acceleration;
    }

    @Override
    public void update(float dt) {
        double tang = Math.toRadians(angle);
        x += Math.cos(tang) * speed * dt;
        y += Math.sin(tang) * speed * dt;
        speed += acceleration / 100;
    }
}
