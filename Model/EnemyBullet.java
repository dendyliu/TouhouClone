package Model;


public class EnemyBullet extends Bullet {

    public EnemyBullet(int x, int y, int radius, float angle, float speed, int dmg) {
        super(x, y, radius, angle, speed, dmg);
    }

    public void update(float dt) {
        super.update(dt);
    }

    public void interact(Movable m) {

    }
}