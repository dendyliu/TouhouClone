package Controller;
/**
 * Main class
 * This class will handle the main program and run the entire  class
 * @author Anthony on 4/26/16.
 */
import Controller.Event.*;
import Model.Boss;
import Model.Player;
import View.Battlefield;
import View.HealthBar;
import View.MainMenu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	static JFrame frame;
	static Battlefield battlefield;
	static ScheduledExecutorService scheduledPool;
	static boolean gameOver = false;

	/*
	* Start the game
    */
	public static void startGame(){
		frame = new JFrame("Touhou Clone");

		frame.setSize(600, 700);
		frame.setResizable(false);
		frame.setVisible(true);

		battlefield = new Battlefield(frame);
		frame.setLayout(new BorderLayout());
		frame.add(battlefield, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		Boss boss = new Boss(400, 200, 2000);
		battlefield.add(boss);
		Player player = new Player(200, 500);
		battlefield.add(player);

		HealthBar healthBar = new HealthBar(boss.getHealth(), player.getHealth());
		frame.add(healthBar.getBossBar(), BorderLayout.NORTH);
		frame.add(healthBar.getPlayerBar(), BorderLayout.SOUTH);

		Runnable task2 = () -> frame.addKeyListener(player);
		new Thread(task2).start();

		scheduledPool = Executors.newScheduledThreadPool(1);

		scheduledPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						healthBar.getBossBar().setValue(boss.getHealth());
						healthBar.getBossBar().setString("Boss HP: " + boss.getHealth());
						healthBar.getPlayerBar().setValue(player.getHealth());
						healthBar.getPlayerBar().setString("Player HP: " + player.getHealth());

						if ((boss.getHealth() <= 0 || player.getHealth() <= 0) && !gameOver) {
							gameOver = true;

							JPanel panel1 = new JPanel();
							JFrame frame = new JFrame();

							JLabel label;
							if (boss.getHealth() <= 0) {
								label = new JLabel("You Win");
							} else {
								label = new JLabel("You Lose");
							}
							frame.setVisible(true);
							frame.setSize(500, 500);
							frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
							panel1.add(label);
							panel1.setLayout(null);
							label.setBounds(175, 200, 100, 50);
							frame.add(panel1);
							frame.setLocationRelativeTo(null);
						}
					}
				});
			}
		}, 30, 20, TimeUnit.MILLISECONDS);
		
		scheduledPool.scheduleWithFixedDelay(new PlayerFiringEvent(battlefield, 0.2f), 30, 20, TimeUnit.MILLISECONDS);
	}
	/*
        * Initialize bullet 
        */
	public static void initBullets(){
		int t = 0;
		t += 1000;
		for (int i = 0; i < 10; ++i) {
			scheduledPool.schedule(new RainingEvent(battlefield, 10, 50, 0, 300), t, TimeUnit.MILLISECONDS);
			t += 500;
		}
		for (int i = 0; i < 5; ++i) {
			scheduledPool.schedule(new BoomerangShotgunEvent(battlefield, 4, 60, 60, 4, 250), t, TimeUnit.MILLISECONDS);
			t += 500;
		}

		for (int i = 0; i < 1; ++i) {
			scheduledPool.schedule(new RicochetFlowerEvent(battlefield, 100, 50, 100, 0, battlefield.getWidth()), t, TimeUnit.MILLISECONDS);
			t += 5000;
		}

		for (int i = 0; i < 5; ++i) {
			scheduledPool.schedule(new SpiralEvent(battlefield, 10, i * 360 / 5.f, 1, 0, 100), t, TimeUnit.MILLISECONDS);
			t += 500;
		}
		t += 2000;

		for (int i = 0; i < 10; ++i) {
			t += 200;
			scheduledPool.schedule(new FlowerEvent(battlefield, 10, i * 10.f, 100), t, TimeUnit.MILLISECONDS);
		}


		for (int i = 0; i < 10; ++i) {
			t += 200;
			scheduledPool.schedule(new FlowerEvent(battlefield, 10, -i * 10.f, 100), t, TimeUnit.MILLISECONDS);
		}

		for (int i = 0; i < 10; ++i) {
			t += 200;
			scheduledPool.schedule(new FlowerEvent(battlefield, 10, i * 10.f, 100), t, TimeUnit.MILLISECONDS);
		}

		t += 1000;

		for (int i = 0; i < 10; ++i) {
			scheduledPool.schedule(new FlowerEvent(battlefield, 10, 0.f, 100 + i * 10), t, TimeUnit.MILLISECONDS);
			t += 100;
		}

		for (int i = 0; i < 10; ++i) {
			scheduledPool.schedule(new ShotgunEvent(battlefield, 4, 60f, 4, 100), t, TimeUnit.MILLISECONDS);
			t += 500;
		}

		scheduledPool.schedule(Main::initBullets, t, TimeUnit.MILLISECONDS);
	}
	 /*
        * Run the program
        */
	public static void main(String[] args){
		Menu menu = new Menu();

	}
}