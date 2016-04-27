package Model.Event;

import Model.Battlefield;
import Model.Player;
import Model.PlayerBullet;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerFiringEvent extends Event {
    private long cooldown; //milliseconds
    private boolean onCooldown;

    public PlayerFiringEvent(Object s, long cooldown) {
        super(s);
        this.cooldown = cooldown;
        onCooldown = false;
    }

    public void run() {
        Battlefield b = (Battlefield) self;
        Player player = b.getPlayer();

        if (player.isOnFiringState()) {
            if (!onCooldown) {
                b.add(new PlayerBullet(player.getX() - 10, player.getY(), 10, 270, 100, 100));

                onCooldown = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        onCooldown = false;
                    }
                }, cooldown);
            }
        }
    }
}