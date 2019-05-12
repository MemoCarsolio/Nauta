import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class NautaGround extends Player {

    private int vx, vy;
    private double angle, px, py;
    private Window w;
    private BufferedImage img, imgL;
    private AnimationSprite sh;
    private boolean isPaused, isDead, isHit;
    private int direction;


    public NautaGround(int x, int y, int width, int height, Color color, Handler handler, int life, Window w){
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

    public void resetValues(){
        life = 10;
        isPaused = false;
        isDead = false;
        vx = 0;
        vy = 0;

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

        //izquierda
        if (key == 37){
            vx = -10;
            direction=1;
        }
        //Derecha
        if (key == 39){
            vx = 10;
            direction=2;
        }


        //Arriba
        if (key == 38){
            vy = - 10;
            direction=3;
        }

        //Abajo
        if (key == 40){
            vy = 10;
            direction=4;
        }

        //Escape
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


        //Space, release to shoot
        if (key == 32){
            //ShootNauta b = new ShootNauta(10,10,x+17,y-10,20,20,Color.red,handler);
            //handler.addObj(b);
            //ShootNauta(int x, int y, int width, int height,int vx, int vy, Color color, Handler handler)

            //Shoot Left
            if(direction==1) {
                ShootNauta b = new ShootNauta(-20,0,x+17,y-10,20,20,Color.red,handler);
                handler.addObj(b);
            }

            //Shoot Right
            else if(direction==2) {
                ShootNauta b = new ShootNauta(20,0,x+17,y-10,20,20,Color.red,handler);
                handler.addObj(b);
            }

            //Shoot Arriba
            else if(direction==3) {
                ShootNauta b = new ShootNauta(0,-20,x+17,y-10,20,20,Color.red,handler);
                handler.addObj(b);
            }

            //Shoot Abajo
            else{
                ShootNauta b = new ShootNauta(0,20,x+17,y-10,20,20,Color.red,handler);
                handler.addObj(b);
            }


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

    public int getDirection() {
        return direction;
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

