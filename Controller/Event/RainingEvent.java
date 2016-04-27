package Controller.Event;

import Model.AcceleratingEnemyBullet;
import Model.Boss;
import View.Battlefield;

/**
 * Raining Event Class
 * This class will handle the Raining Event.
 * @author Anthony on 4/26/16.
 */


public class RainingEvent extends Event {

    private final int count;
    private final float speed;
    private float acceleration;
    private int delay;
     /**
        * RainingEvent Constuctor
        * @param s object that do the event
        * @param count count the raining bullet
        * @param delay time delay for raining event
        * @param speed speed of raining bullet move
        * @param acceleration acceleration of raining bullet move
        */
    public RainingEvent(Object s, int count, int delay, float speed, float acceleration) {
        super(s);
        this.count = count;
        this.acceleration = acceleration;
        this.delay = delay;
        this.speed = speed;
    }
    /*
    * Run the Raining Event
    */
    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        new Thread(() -> {
            for (int i = 0; i < count; ++i) {
                float rnd1 = (float) (Math.random() * 100 - 50);
                float rnd2 = (float) (Math.random() * 100 - 50);
                b.add(new AcceleratingEnemyBullet(boss.getX() + Math.round(rnd1), boss.getY() + Math.round(rnd2), 7, 90, speed, 10, acceleration));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}