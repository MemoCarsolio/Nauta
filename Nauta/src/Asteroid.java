import java.util.Random;
import java.awt.*;

public class Asteroid extends GameObject{
    
    private int spd;
    private boolean dead;
    private AnimationSprite ad;
    
    
    public Asteroid(int x, int y, int width, int height, Color color, Handler handler){
        
        super(x,y, width,height,color, handler);
        Random r = new Random(System.currentTimeMillis());
        spd = r.nextInt(5)+1;
        dead = false;


        SpriteBuilder builder = new SpriteBuilder("Images/Asteroid/Asteroids_64x96.png", 32,32);
        builder.addImage(1,0);
        builder.addImage(0,1);
        builder.addImage(0,2);



        ad = new AnimationSprite(x, y, builder.build(),width,height);
        ad.setAnimSpd(5);



        
    }
    
    public void tick(){

        ad.setsX(x);
        ad.setsY(y);
        ad.update();
        if (y <= 540){
            y  += spd;
        }
        else{
            handler.objects.remove(this);
        }


    }
    public Rectangle getBounds(){
        return (new Rectangle(getX(), getY(), width, height));
        
    }
    
    @Override
    public void paint(Graphics g) {
        //g.setColor(color);
        //g.fillOval(getX(),getY(),width,height);
        ad.render(g);


    }
    
    public int getSpd() {
        return spd;
    }
    
    public void setSpd(int spd) {
        this.spd = spd;
    }


}