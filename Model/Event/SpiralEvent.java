package Model.Event;

import Model.Battlefield;
import Model.Boss;
import Model.SpiralEnemyBullet;

/**
 * Created by Anthony on 4/26/16.
 */

public class SpiralEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float startingAngle;
    private int numberOfRotation;
    private int delay;

    public SpiralEvent(Object s, int spreadCount, float startingAngle, int numberOfRotation, int delay, float speed) {
        super(s);
        this.spreadCount = spreadCount;
        this.numberOfRotation = numberOfRotation;
        this.delay = delay;
        this.speed = speed;
        this.startingAngle = startingAngle;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        new Thread(() -> {
            for (float a = startingAngle; a < 360 * numberOfRotation + startingAngle; a += 360 * numberOfRotation / spreadCount) {
                b.add(new SpiralEnemyBullet(boss.getX(), boss.getY(), 7, a, speed, 10));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}