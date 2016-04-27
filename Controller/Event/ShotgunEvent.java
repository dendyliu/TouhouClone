package Controller.Event;

import Model.Boss;
import Model.EnemyBullet;
import View.Battlefield;

/**
 * Shotgun Event Class
 * This class will handle the Shotgun Event
 * @author Anthony on 4/26/16.
 */


public class ShotgunEvent extends Event {

    private final int spreadCount;
    private final float speed;
    private final float widthAngle;
    private final int layerCount;
     /**
        * ShotgunEvent Constuctor
        * @param s object that do the event
        * @param spreadCount count spread the Shotgun bullet
        * @param widthAngle width angle for Shotgun bullet
        * @param layerCount count the layer for ShotgunEvent
        * @param speed speed of Shotgun bullet move
        */
    public ShotgunEvent(Object s, int spreadCount, float widthAngle, int layerCount, float speed) {
        super(s);
        this.spreadCount = spreadCount;
        this.speed = speed;
        this.widthAngle = widthAngle;
        this.layerCount = layerCount;
    }
    /*
    * Run the Shotgun 
    */
    public void run() {
        Battlefield b = (Battlefield) self;
        Boss boss = b.getBoss();
        float deltaX = b.getBoss().getX() - b.getPlayer().getX();
        float deltaY = b.getBoss().getY() - b.getPlayer().getY();
        float angle = (float) Math.atan2(deltaY, deltaX);
        angle = (float) Math.toDegrees(angle);
        angle += 180;
        angle -= widthAngle / 2;

        for (float layerSpeed = 0.5f * speed; layerSpeed < 1.5 * speed; layerSpeed += speed / layerCount) {
            for (float a = angle; a < widthAngle + angle; a += widthAngle / spreadCount) {
                b.add(new EnemyBullet(boss.getX(), boss.getY(), 7, a, layerSpeed, 10));
            }
        }
    }
}