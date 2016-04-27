package Controller.Event;

import Model.AcceleratingEnemyBullet;
import Model.Boss;
import View.Battlefield;

/**
 * Created by Anthony on 4/26/16.
 */

public class BoomerangShotgunEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float directionAngle;
    private final float widthAngle;
    private final int layerCount;

    public BoomerangShotgunEvent(Object s, int spreadCount, float directionAngle, float widthAngle, int layerCount, float speed) {
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
                b.add(new AcceleratingEnemyBullet(boss.getX(), boss.getY(), 7, a, layerSpeed, 10, -200));
            }
        }
    }
}