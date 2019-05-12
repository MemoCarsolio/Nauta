import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Enemy extends GameObject{

    private int vx, vy;
    private double angle, px, py;
    private Window w;
    private BufferedImage img, imgL;
    private AnimationSprite sh;
    boolean isDead;
    int direction; //1-up 2-right 3-down 4-left

    public Enemy(int x, int y, int width, int height, Color color, Handler handler, Window w) {
        super(x, y, width, height, color, handler);
        img = ImageLoader.loadImage("Images/spaceship.png");
        this.w = w;
        angle = 0;
        vx = 0;
        vy = 0;
        this.w = w;
        isDead = false;

        SpriteBuilder builder = new SpriteBuilder("Images/Alien/alien_1.png", 128,128);
        builder.addImage(1,0);
        builder.addImage(4,0);
        builder.addImage(0,1);
        builder.addImage(2,1);

        sh = new AnimationSprite(x, y, builder.build(),width,height);
        sh.setAnimSpd(10);
    }

    public void death(){
        isDead = true;
    }

    public boolean isDead(){
        return isDead;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        sh.render(g);
    }

    @Override
    public void tick() {

        checkCollision();

        sh.setsX(x);
        sh.setsY(y);
        sh.update();

    }

    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }

    public void checkCollision(){

        ListIterator<GameObject> iterator = handler.objects.listIterator();
        while (iterator.hasNext()){

            GameObject aux = iterator.next();

            if (aux instanceof ShootNauta){

                Rectangle blst = aux.getBounds();
                Rectangle ply = getBounds();

                if (ply.intersects(blst)){
                    isDead = true;
                }

            }
        }
    }

    public void shoot() {

        int vxBlast = 0;
        int vyBlast = 0;

        switch (direction){
            case 1:
                vxBlast = 0;
                vyBlast = -10;
                break;
            case 2:
                vxBlast = 10;
                vyBlast = 0;
                break;
            case 3:
                vxBlast = 0;
                vyBlast = 10;
                break;
            case 4:
                vxBlast = -10;
                vyBlast = 0;
                break;
        }

        Blaster b = new Blaster(x, y, vxBlast, vyBlast,Color.red,handler);
        handler.addObj(b);
    }

    public void huntNauta(int posX, int posY){

        if (posX < x) {
            x -= vx;
        } else {
            x += vx;
        }

        if (posY < y) {
            y -= vy;
        } else {
            y += vy;
        }
    }
}