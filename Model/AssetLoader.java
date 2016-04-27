package Model;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 * Assetloader class
 * This class represent asset loader
 * This class will handle import image from asset folder 
 * @author Candra Ramsi on 4/24/16.
 */

public class AssetLoader {
	private BufferedImage bossImage;
	private BufferedImage playerImage;
	private BufferedImage[] bulletImage;
	/*
        *AssetLoader Constructor
        */
	public AssetLoader(){
		URL url = getClass().getResource("../assets"); //relative path
		bulletImage = new BufferedImage[3];

		try {
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
	/*
        * Return Image of Boss
        * @return BufferedImage contain Image of Boss
        */
	public BufferedImage getBossImage(){
		return bossImage;
	}
	/*
        * Return Image of Player
        * @return BufferedImage contain Image of Player
        */
	public BufferedImage getPlayerImage(){
		return playerImage;
	}
	/*
        * Return Image of Bullet
        * @param i represent kind of bullet
        * @return BufferedImage contain Image of Bullet
        */
	public BufferedImage getBulletImage(int i){
		return bulletImage[i];
	}
}