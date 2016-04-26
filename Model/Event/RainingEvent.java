package Model.Event;

import Model.AcceleratingEnemyBullet;
import Model.Battlefield;
import Model.Boss;

public class RainingEvent extends Event {

    private final int count;
    private final float speed;
    private float acceleration;
    private int numberOfRotation;
    private int delay;

    public RainingEvent(Object s, int count, int delay, float speed, float acceleration) {
        super(s);
        this.count = count;
        this.acceleration = acceleration;
        this.delay = delay;
        this.speed = speed;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();

        new Thread(() -> {
            for (int i = 0; i < count; ++i) {
                b.add(new AcceleratingEnemyBullet(boss.getX(), boss.getY(), 10, 90, speed, 10, acceleration));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}