import java.awt.*;


public class Bullet extends GameObject{

    private int spd, r;


    public Bullet(int x, int y, Handler handler){
        super(x,y,10,10,Color.red,handler);
        r = 10;
        spd = -10;
    }

    public void tick(){
        y+=spd;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval((int)x, (int)y, r, r);

    }

    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }

    @Override
    public void keyPressed(int e) {

    }
    public void keyReleased(int e){

    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }



}
