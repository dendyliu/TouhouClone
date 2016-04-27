package Model.Event;

import Model.Battlefield;
import Model.Boss;
import Model.RicochetEnemyBullet;


/**
 * Created by Anthony on 4/26/16.
 */

public class RicochetFlowerEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float startingAngle;
    private int leftBarrier;
    private int rightBarrier;

    public RicochetFlowerEvent(Object s, int spreadCount, float startingAngle, float speed, int leftBarrier, int rightBarrier) {
        super(s);
        this.spreadCount = spreadCount;
        this.speed = speed;
        this.startingAngle = startingAngle;
        this.leftBarrier = leftBarrier;
        this.rightBarrier = rightBarrier;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        for (float a = startingAngle; a < 360 + startingAngle; a += 360 / spreadCount) {
            b.add(new RicochetEnemyBullet(boss.getX(), boss.getY(), 7, a, speed, 10, leftBarrier, rightBarrier));
        }
    }
}