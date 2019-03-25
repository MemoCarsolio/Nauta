
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Ship extends Player {

    private int vx, vy;
    private double angle, px, py;
    private Window w;
    private BufferedImage img, imgL;


    public Ship(int x, int y, int width, int height, Color color, Handler handler, int life, Window w){
    super(x,y,width,height,color,life,handler);
    img = ImageLoader.loadImage("Images/spaceship.png");
    imgL = ImageLoader.loadImage("Images/Life/life_10.png");
    angle = 0;
    vx = 0;
    vy = 0;
    this.w = w;

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
            Blaster b = new Blaster(x+(width/2)-4,y-10,20,20,Color.red,handler);
            handler.addObj(b);
        }

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);



        at.scale(.2,.2);

        at.rotate(0,img.getWidth()/2,img.getHeight()/2);

        imgL = ImageLoader.loadImage("Images/Life/life_"+life+".png");

        g2d.drawImage(imgL,0,0,200,50,null);
        g2d.drawImage(img,at, null);

    }



    @Override
    public void tick() {

        checkCollision();

        if (x+vx <= 960-width && x+vx >= 0 && y+vy >= 0 && y+vy<= 540-height) {
            x += vx;
            y += vy;
        }else {
            x -= vx;
            y -= vy;
        }

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
                    handler.removeObj(aux);

                    if (life > 0){
                        life -= 1;
                    }
                    else{

                    }

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

        System.out.println(angle);

        return angle;




    }


}
