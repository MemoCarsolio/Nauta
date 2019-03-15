import java.awt.*;
import java.awt.event.KeyEvent;

public class Nauta extends GameObject {

    private int spdx, spdy;

    public Nauta(double x, double y, int width, int height, Color color, Handler h){
        super(x,y,width,height,color,h);
        spdx = 0;
        spdy = 0;
    }

    public void tick(){

            x += spdx;
            y += spdy;



    }


    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,width,height);

    }

    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }
    public void keyPressed(int key){

        if (key == 37){
            setSpdx(-5);
        }
        if (key == 39){
            setSpdx(+5);
        }
        if (key == 38){
            setSpdy(-5);
        }
        if (key == 40){
            setSpdy(+5);
        }
        if (key == 32){
            Bullet b = new Bullet(getX(),getY(),handler);
            
        }

    }

    public void keyReleased(int key){
        if (key == 37){
            setSpdx(0);
        }
        if (key == 39){
            setSpdx(0);
        }
        if (key == 38){
            setSpdy(0);
        }
        if (key == 40){
            setSpdy(0);
        }
    }

    public int getSpdx() {
        return spdx;
    }

    public void setSpdx(int spdx) {
        this.spdx = spdx;
    }

    public int getSpdy() {
        return spdy;
    }

    public void setSpdy(int spdy) {
        this.spdy = spdy;
    }
}
