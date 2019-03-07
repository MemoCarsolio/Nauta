package image;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage background;
	
	//Initialize images.
	public static void init()
	{
		//Load image.
		background = ImageLoader.loadImage("/mars_floor.jpg");
	}
}
