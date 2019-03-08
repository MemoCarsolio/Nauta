import java.util.Random;
import java.awt.*;

public class Asteroid extends GameObject{

    private int spd;


    public Asteroid(double x, double y, int width, int height, Color color, Handler h){

        super(x,y, width,height,color,h);
        Random r = new Random(System.currentTimeMillis());
        spd = r.nextInt(5)+1;

    }

    public void tick(){
        if (y <= 480){
            y  += spd;


        }
    }
    public Rectangle getBounds(){
        return (new Rectangle(getX(), getY(), width, height));

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(getX(),getY(),width,height);
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }
}

