package Model;

/**
 *
 * @author Rmx
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class AssetLoader {
	private BufferedImage bossImage;
	private BufferedImage playerImage;
	private BufferedImage[] bulletImage;
	
	public AssetLoader(){
		try {
			bulletImage = new BufferedImage[3];
			bossImage = ImageIO.read(new File("E:\\University\\Semester 4\\OOP\\JagatRaya\\assets\\Boss.png"));
			playerImage = ImageIO.read(new File("E:\\University\\Semester 4\\OOP\\JagatRaya\\assets\\Player.png"));
			bulletImage[0] = ImageIO.read(new File("E:\\University\\Semester 4\\OOP\\JagatRaya\\assets\\Bullet1.png"));
			bulletImage[1] = ImageIO.read(new File("E:\\University\\Semester 4\\OOP\\JagatRaya\\assets\\Bullet2.png"));
			bulletImage[2] = ImageIO.read(new File("E:\\University\\Semester 4\\OOP\\JagatRaya\\assets\\Bullet3.png"));
			
		} catch (IOException ex) {
			ex.printStackTrace();

			// handle exception...
		}
	}
	
	public BufferedImage getBossImage(){
		return bossImage;
	}
	
	public BufferedImage getPlayerImage(){
		return playerImage;
	}
	
	public BufferedImage getBulletImage(int i){
		return bulletImage[i];
	}
}