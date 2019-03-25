import java.awt.*;

public class AnimationSprite {

    private int imageIndex;
    private CachedSprite sprite;

    private boolean reachedEnd;
    private int animSpd, animCount, topCount, sX, sY, width, height;

    public AnimationSprite(int x, int y, CachedSprite sprite){

        imageIndex = 0;
        this.sprite = sprite;
        System.out.println("Sprite Amount:" + sprite.size());
        sX = x;
        sY = y;

    }
    public AnimationSprite(int x, int y, CachedSprite sprite, int width, int height){

        imageIndex = 0;
        this.sprite = sprite;
        System.out.println("Sprite Amount:" + sprite.size());
        sX = x;
        sY = y;

        this.width = width;
        this.height = height;


    }

    public int getsX() {
        return sX;
    }

    public void setsX(int sX) {
        this.sX = sX;
    }

    public int getsY() {
        return sY;
    }

    public void setsY(int sY) {
        this.sY = sY;
    }

    public  void setAnimSpd(int animSpd){
        this.animSpd = animSpd;
        if (animSpd != 0){
            topCount = 60/ animSpd;
            animCount = 0;
        }
        reachedEnd = false;
    }
    public void update(){
        if (animSpd > 0){
            if (animCount < topCount){
                animCount++;
                reachedEnd = false;

            }else {
                animCount = 0;
                imageIndex = (imageIndex+1) % sprite.size();
                reachedEnd = true;
            }
        }
    }
    public void render(Graphics g){
        if (width != 0){
            g.drawImage(sprite.get(imageIndex), sX, sY, width, height, null);
        }else {
            g.drawImage(sprite.get(imageIndex), sX, sY, null);
        }
    }
    public boolean hasReachedEnd(){
        return reachedEnd;
    }
    public void reset(){
        imageIndex = 0;
        animCount = 0;
    }
}
