package Controller.Event;

import Model.Boss;
import Model.SpiralEnemyBullet;
import View.Battlefield;

/**
 * Spiral Event class
 * this class will handle the spiral event.
 * @author Anthony on 4/26/16.
 */

public class SpiralEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float startingAngle;
    private int numberOfRotation;
    private int delay;
      /**
        * SpiralEvent Constuctor
        * @param s object that do the event
        * @param spreadCount count spread the Spiral bullet
        * @param startingAngle starting angle of Spiral bullet
        * @param numberOfRotation number of rotation in Spiral event.
        * @param delay time delay for Spiral event
        * @param speed speed of Spiral bullet move
        */
    public SpiralEvent(Object s, int spreadCount, float startingAngle, int numberOfRotation, int delay, float speed) {
        super(s);
        this.spreadCount = spreadCount;
        this.numberOfRotation = numberOfRotation;
        this.delay = delay;
        this.speed = speed;
        this.startingAngle = startingAngle;
    }
    /*
    * Run the Spiral Event.
    */
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