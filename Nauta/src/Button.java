import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends ObjectWInput {
    
    private boolean pressed;
    private int imgn;
    private BufferedImage img;
    
    public Button(int x, int y, int width, int height, Color color, Handler handler) {
        super(x, y, width, height, color, handler);
       
        pressed = false;
        img = ImageLoader.loadImage("Images/Button/StartButton/1.png");
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x,y,width,height,null);
    }
    
    @Override
    public void tick() {}
    
    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }
    

    public boolean isPressed(){
        return pressed;
    }


    @Override
    public void mouseClicked(int x, int y) {





    }

    @Override
    public void mousePressed(int x, int y) {
        if( x >= this.x && x <= this.x+width && y >= this.y && y <= this.y + height){
            img = ImageLoader.loadImage("Images/Button/StartButton/2.png");
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if( x >= this.x && x <= this.x+width && y >= this.y && y <= this.y + height){
            img = ImageLoader.loadImage("Images/Button/StartButton/1.png");
            pressed = true;
        }


    }

    @Override
    public void mouseEntered(int x, int y) {

    }

    @Override
    public void mouseExited(int x, int y) {

    }

    @Override
    public void keyTyped(int key) {

    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }


}