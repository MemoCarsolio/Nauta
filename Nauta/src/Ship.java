
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Ship extends Player {

    private int vx, vy;
    private double angle, px, py;
    private Window w;
    private BufferedImage img, imgL;
    private AnimationSprite sh;
    private boolean isPaused, isDead, isHit;


    public Ship(int x, int y, int width, int height, Color color, Handler handler, int life, Window w){
    super(x,y,width,height,color,life,handler);
    img = ImageLoader.loadImage("Images/spaceship.png");
    imgL = ImageLoader.loadImage("Images/Life/life_10.png");
    angle = 0;
    vx = 0;
    vy = 0;
    this.w = w;
    isPaused = false;
    isDead = false;



        SpriteBuilder builder = new SpriteBuilder("Images/Ship/ship.png", 128,128);
        builder.addImage(1,0);
        builder.addImage(4,0);
        builder.addImage(0,1);
        builder.addImage(2,1);




        sh = new AnimationSprite(x, y, builder.build(),width,height);
        sh.setAnimSpd(10);

    }

    public void resetValues(int w){
        life = 10;
        isPaused = false;
        isDead = false;
        vx = 0;
        vy = 0;
        x = (w/2)-25;
        y = 400;
    }

    @Override
    public void death() {

    }

    @Override
    public void hurt() {

    }

    @Override
    public void mouseClicked(int x, int y) {




    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

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

        if (key == 37){
            vx = -10;
        }
        if (key == 38){
            vy = - 10;
        }
        if (key == 39){
            vx = 10;
        }
        if (key == 40){
            vy = 10;
        }

        if (key == 27){
        isPaused = true;
        }




    }

    @Override
    public void keyReleased(int key) {

        if (key == 37){
            vx = 0;
        }
        if (key == 38){
            vy = 0;
        }
        if (key == 39){
            vx =0;
        }
        if (key == 40){
            vy = 0;
        }

        if (key == 32){
            Blaster b = new Blaster(1, x+17,y-10, 0, -12,20,20,Color.red,handler);
            handler.addObj(b);
            AudioPlayer.get().playEffectSound("audio/blaster");
        }

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        imgL = ImageLoader.loadImage("Images/Life/life_"+life+".png");

        g2d.drawImage(imgL,0,0,200,50,null);
        sh.render(g);
    }



    @Override
    public void tick() {

        checkCollision();

        if (x+vx <= 960-width && x+vx >= 0 && y+vy >= 0 && y+vy<= 540-height) {
            x += vx;
            y += vy;
        }else {

        }

        sh.setsX(x);
        sh.setsY(y);
        sh.update();

    }

    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }

    public void checkCollision(){

        ListIterator <GameObject> iterator = handler.objects.listIterator();
        while (iterator.hasNext()){
            GameObject aux = iterator.next();
            if (aux instanceof Asteroid ){


                Rectangle ast = aux.getBounds();
                Rectangle ply = getBounds();

                if (ply.intersects(ast)){



                    if (life > 0){
                        life -= ((Asteroid) aux).getDamage();
                    }
                    else{
                        isDead = true;

                    }

                    if (((Asteroid) aux).getDamage() != 0){
                        ((Asteroid) aux).setDead(true);
                        AudioPlayer.get().playEffectSound("audio/explosion");
                    }

                    isHit = true;

                }



            }
        }




    }



    public double getAngle(){

        Component[] components = w.getFrame().getComponents();
        Component canvas = components[0];



        px = MouseInfo.getPointerInfo().getLocation().x - canvas.getLocationOnScreen().x;
        py = MouseInfo.getPointerInfo().getLocation().y - canvas.getLocationOnScreen().y;

        double xdiff = x-px;
        double ydiff = y-py;

        angle = Math.toDegrees(Math.atan2(ydiff,xdiff));



        return angle;




    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
