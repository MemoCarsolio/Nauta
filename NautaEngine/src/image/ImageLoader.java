package image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// Class used to load images.
public class ImageLoader {
	
	public static BufferedImage loadImage(String path)
	{
		//Try to load the image.
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		//If image isn't found.
		} catch (IOException e) {
			e.printStackTrace();
			//Close the game
			System.exit(1);
		}
		//In case nothing happens (rare).
		return null;
	}
	
}