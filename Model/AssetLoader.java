package Model;

/**
 *
 * @author Rmx
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AssetLoader {
	private BufferedImage bossImage;
	private BufferedImage playerImage;
	private BufferedImage[] bulletImage;
	
	public AssetLoader(){
		//TODO make relative path http://stackoverflow.com/questions/3844307/how-to-read-text-file-from-relative-path-in-a-project
		String cdir = "E:\\University\\Semester 4\\OOP\\TouhouClone";
		bulletImage = new BufferedImage[3];

		try {
			bossImage = ImageIO.read(new File(cdir+"\\assets\\Boss.png"));
			playerImage = ImageIO.read(new File(cdir+"\\assets\\Player.png"));
			bulletImage[0] = ImageIO.read(new File(cdir+"\\assets\\Bullet1.png"));
			bulletImage[1] = ImageIO.read(new File(cdir+"\\assets\\Bullet2.png"));
			bulletImage[2] = ImageIO.read(new File(cdir+"\\assets\\Bullet3.png"));
			
			
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