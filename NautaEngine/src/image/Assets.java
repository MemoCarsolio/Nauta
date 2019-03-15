package image;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage planet;
	public static BufferedImage menu;
	
	//Initialize images.
	public static void init()
	{
		//Load images.
		menu = ImageLoader.loadImage("/menu.jpg");
		planet = ImageLoader.loadImage("/mars_floor.jpg");
	}
}
