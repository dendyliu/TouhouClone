package Controller.Event;

import Model.Player;
import Model.PlayerBullet;
import View.Battlefield;

import java.util.Date;

/**
 * Player Firing Event class
 * This class will handle bullet firing from player
 *  @author Candra Ramsi on 4/24/16.
 */


public class PlayerFiringEvent extends Event {
    private long cooldown; //milliseconds
    private long lastShotTime;
    /**
     * PlayerFiringEvent Constuctor
     * @param s object that do the event
     * @param cooldown delay time for firing the bullet
     */
    public PlayerFiringEvent(Object s, float cooldown) {
        super(s);
        this.cooldown = new Float(cooldown * 1000).intValue();
        lastShotTime = new Date().getTime() - this.cooldown;
    }
    /*
    * Run the Firing 
    */
    public void run() {
        Battlefield b = (Battlefield) self;
        Player player = b.getPlayer();

        if (player.isOnFiringState()) {
            long tmp = new Date().getTime() - lastShotTime;
            if (tmp > cooldown) {
                b.add(new PlayerBullet(player.getX() - 30, player.getY(), 10, 270, 500, 5));
                b.add(new PlayerBullet(player.getX(), player.getY(), 10, 270, 500, 5));
                b.add(new PlayerBullet(player.getX() + 30, player.getY(), 10, 270, 500, 5));

                lastShotTime = new Date().getTime();

            }
        }
    }
}