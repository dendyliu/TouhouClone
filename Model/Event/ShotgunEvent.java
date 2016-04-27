package Model.Event;

import Model.Battlefield;
import Model.Boss;
import Model.EnemyBullet;

/**
 * Created by Anthony on 4/26/16.
 */


public class ShotgunEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float widthAngle;
    private final int layerCount;

    public ShotgunEvent(Object s, int spreadCount, float widthAngle, int layerCount, float speed) {
        super(s);
        this.spreadCount = spreadCount;
        this.speed = speed;
        this.widthAngle = widthAngle;
        this.layerCount = layerCount;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();
        float deltaX = b.getBoss().getX() - b.getPlayer().getX();
        float deltaY = b.getBoss().getY() - b.getPlayer().getY();
        float angle = (float) Math.atan2(deltaY, deltaX);
        angle = (float) Math.toDegrees(angle);
        angle += 180;
        angle -= widthAngle / 2;
        System.out.println(angle);

        for (float layerSpeed = 0.5f * speed; layerSpeed < 1.5 * speed; layerSpeed += speed / layerCount) {
            for (float a = angle; a < widthAngle + angle; a += widthAngle / spreadCount) {
                b.add(new EnemyBullet(boss.getX(), boss.getY(), 7, a, layerSpeed, 10));
            }
        }
    }
}