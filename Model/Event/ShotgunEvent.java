package Model.Event;

import Model.Battlefield;
import Model.Boss;
import Model.EnemyBullet;

public class ShotgunEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float directionAngle;
    private final float widthAngle;
    private final int layerCount;

    public ShotgunEvent(Object s, int spreadCount, float directionAngle, float widthAngle, int layerCount, float speed) {
        super(s);
        this.spreadCount = spreadCount;
        this.speed = speed;
        this.directionAngle = directionAngle;
        this.widthAngle = widthAngle;
        this.layerCount = layerCount;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        for (float layerSpeed = 0.5f * speed; layerSpeed < 1.5 * speed; layerSpeed += speed / layerCount) {
            for (float a = directionAngle; a < widthAngle + directionAngle; a += widthAngle / spreadCount) {
                b.add(new EnemyBullet(boss.getX(), boss.getY(), 10, a, layerSpeed, 10));
            }
        }
    }
}