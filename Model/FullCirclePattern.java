package Model;

/**
 * Created by anthony on 4/24/16.
 */
public class FullCirclePattern extends Pattern {
    private int bulletCount;

    public FullCirclePattern(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    @Override
    public void startAt(float x, float y) {
        float angle = 0;
        for (int i = 0; i < bulletCount; i++) {
            Bullet bullet = new Bullet(new Float(x).intValue(), new Float(y).intValue(), defaultRadius, angle, defaultSpeed, defaultDmg);
            bullets.add(bullet);
            Battlefield.getInstance().add(bullet);
            angle += 360/bulletCount;
        }
        new Thread(this::lifetimeCountdown).start();
    }
}
