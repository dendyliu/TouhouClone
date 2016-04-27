
import Model.Event.*;
import javax.swing.*;
import java.awt.*;
import Model.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(() -> {
			JFrame frame = new JFrame("Touhou Clone");
			frame.setSize(600, 800);
			frame.setResizable(false);
			frame.setVisible(true);

			

			
			Battlefield b = new Battlefield(frame);
			frame.setLayout(new BorderLayout());
			frame.add(b, BorderLayout.CENTER);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			
			Boss boss = new Boss(400, 200, 2000);
			b.add(boss);
			Player player = new Player(200, 500);
			b.add(player);
			Runnable task2 = () -> frame.addKeyListener(player);

			new Thread(task2).start();
			
			ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
			scheduledPool.scheduleWithFixedDelay(new PlayerFiringEvent(b, 200), 30, 20, TimeUnit.MILLISECONDS);
			int t = 0;
			t += 1000;
			for (int i = 0; i < 10; ++i) {
				scheduledPool.schedule(new RainingEvent(b, 10, 50, 0, 300), t, TimeUnit.MILLISECONDS);
				t += 500;
			}
			for (int i = 0; i < 5; ++i) {
				scheduledPool.schedule(new BoomerangShotgunEvent(b, 4, 60, 60, 4, 250), t, TimeUnit.MILLISECONDS);
				t += 500;
			}

			for (int i = 0; i < 1; ++i) {
				scheduledPool.schedule(new RicochetFlowerEvent(b, 100, 50, 100, 0, b.getWidth()), t, TimeUnit.MILLISECONDS);
				t += 5000;
			}

			for (int i = 0; i < 5; ++i) {
				scheduledPool.schedule(new SpiralEvent(b, 10, i * 360 / 5.f, 1, 0, 100), t, TimeUnit.MILLISECONDS);
				t += 500;
			}
			t += 2000;

			for (int i = 0; i < 10; ++i) {
				t += 200;
				scheduledPool.schedule(new FlowerEvent(b, 10, i * 10.f, 100), t, TimeUnit.MILLISECONDS);
			}


			for (int i = 0; i < 10; ++i) {
				t += 200;
				scheduledPool.schedule(new FlowerEvent(b, 10, -i * 10.f, 100), t, TimeUnit.MILLISECONDS);
			}

			for (int i = 0; i < 10; ++i) {
				t += 200;
				scheduledPool.schedule(new FlowerEvent(b, 10, i * 10.f, 100), t, TimeUnit.MILLISECONDS);
			}

			t += 1000;

			for (int i = 0; i < 10; ++i) {
				scheduledPool.schedule(new FlowerEvent(b, 10, 0.f, 100 + i * 10), t, TimeUnit.MILLISECONDS);
				t += 100;
			}

			for (int i = 0; i < 10; ++i) {
				scheduledPool.schedule(new ShotgunEvent(b, 4, 60f, 60f, 4, 100), t, TimeUnit.MILLISECONDS);
				t += 500;
			}
		});

		
		/*
		while( true ){
			
			b.update(0.02f);
			b.repaint();
			try { Thread.sleep(20); } catch(Exception ex){}
			
		}*/
	}
}