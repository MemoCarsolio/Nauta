import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteBuilder {

    private BufferedImage spritesheet;
    private List<BufferedImage> images;
    private  int cellW, cellH;

    public SpriteBuilder(String fName, int cellW, int cellH){

        try {
            spritesheet = (BufferedImage) ImageIO.read(getClass().getClassLoader().getResource("./"+fName));
        }catch (IOException e){
            e.printStackTrace();
        }

        images = new ArrayList<>();

        this.cellW = cellW;
        this.cellH = cellH;
    }

    public SpriteBuilder addImage(int i, int j){

        images.add(spritesheet.getSubimage(i*cellW,j*cellH, cellW, cellH));
        return this;
    }

    public CachedSprite build(){
        return new CachedSprite(images);
    }

}
