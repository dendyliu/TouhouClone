package Model.Event;

import Model.Battlefield;
import Model.Player;
import Model.PlayerBullet;

import java.util.Date;

public class PlayerFiringEvent extends Event {
    private long cooldown; //milliseconds
    private long lastShotTime;

    public PlayerFiringEvent(Object s, float cooldown) {
        super(s);
        this.cooldown = new Float(cooldown * 1000).intValue();
        lastShotTime = new Date().getTime() - this.cooldown;
    }

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