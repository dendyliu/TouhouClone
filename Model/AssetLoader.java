package Model;

/**
 *
 * @author Rmx
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class AssetLoader {
	private BufferedImage bossImage;
	private BufferedImage playerImage;
	private BufferedImage[] bulletImage;
	
	public AssetLoader(){
		URL url = getClass().getResource("../assets"); //relative path
		bulletImage = new BufferedImage[3];

		try {
			System.out.println(url.getPath());
			bossImage = ImageIO.read(new File(url.getPath()+"/Boss.png"));
			playerImage = ImageIO.read(new File(url.getPath()+"/Player.png"));
			bulletImage[0] = ImageIO.read(new File(url.getPath()+"/Bullet1.png"));
			bulletImage[1] = ImageIO.read(new File(url.getPath()+"/Bullet2.png"));
			bulletImage[2] = ImageIO.read(new File(url.getPath()+"/Bullet3.png"));
			
			
		} catch (IOException ex) {
			ex.printStackTrace();

			// handle exception..
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