package Controller.Event;

import Model.Boss;
import Model.RicochetEnemyBullet;
import View.Battlefield;


/**
 * Ricochet Flower Event class
 * This class will handel Ricochet Flower Event.
 * @author Anthony on 4/26/16.
 */

public class RicochetFlowerEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float startingAngle;
    private int leftBarrier;
    private int rightBarrier;
    /**
        * RicochetFlowerEvent Constuctor
        * @param s object that do the event
        * @param spreadCount count spread the RicochetFlower bullet
        * @param startingAngle starting angle for RicochetFlower event
        * @param speed speed of RicochetFlower bullet move
        * @param leftBarrier leftBarrier of flower
        * @param rightBarrier rightBarrier of flwoer
        */
    public RicochetFlowerEvent(Object s, int spreadCount, float startingAngle, float speed, int leftBarrier, int rightBarrier) {
        super(s);
        this.spreadCount = spreadCount;
        this.speed = speed;
        this.startingAngle = startingAngle;
        this.leftBarrier = leftBarrier;
        this.rightBarrier = rightBarrier;
    }
    /*
    * Run the RicochetFlower Event
    */
    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        for (float a = startingAngle; a < 360 + startingAngle; a += 360 / spreadCount) {
            b.add(new RicochetEnemyBullet(boss.getX(), boss.getY(), 7, a, speed, 10, leftBarrier, rightBarrier));
        }
    }
}