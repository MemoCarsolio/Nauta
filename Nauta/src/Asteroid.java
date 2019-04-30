import java.util.Random;
import java.awt.*;

public class Asteroid extends GameObject{
    
    private int spd, counter, damage;
    private boolean dead;
    private AnimationSprite ad, exp;
    
    
    public Asteroid(int x, int y, int width, int height, Color color, Handler handler){
        
        super(x,y, width,height,color, handler);
        Random r = new Random(System.currentTimeMillis());
        spd = r.nextInt(5)*1 + 10;
        dead = false;
        counter = 0;
        damage = 1;


        SpriteBuilder builder = new SpriteBuilder("Images/Asteroid/Asteroids_64x96.png", 32,32);
        builder.addImage(1,0);
        builder.addImage(0,1);
        builder.addImage(0,2);



        ad = new AnimationSprite(x, y, builder.build(),width,height);
        ad.setAnimSpd(5);

        SpriteBuilder builder2 = new SpriteBuilder("Images/Asteroid/exp.png", 300,300);
        builder2.addImage(0,0);
        builder2.addImage(1,0);
        builder2.addImage(2,0);
        builder2.addImage(3,0);
        builder2.addImage(4,0);
        builder2.addImage(0,1);
        builder2.addImage(1,1);
        builder2.addImage(2,1);
        builder2.addImage(3,1);
        builder2.addImage(4,1);
        builder2.addImage(0,2);
        builder2.addImage(1,2);
        exp = new AnimationSprite(x, y, builder2.build(),width,height);
        exp.setAnimSpd(20);



        
    }

    public Asteroid(int x, int y, int width, int height, Color color, Handler handler, int damage, int spd){

        super(x,y, width,height,color, handler);
        Random r = new Random(System.currentTimeMillis());
        this.spd = spd;
        dead = false;
        counter = 0;
        this.damage = damage;


        SpriteBuilder builder = new SpriteBuilder("Images/Asteroid/Asteroids_64x96.png", 32,32);
        builder.addImage(1,0);
        builder.addImage(0,1);
        builder.addImage(0,2);



        ad = new AnimationSprite(x, y, builder.build(),width,height);
        ad.setAnimSpd(5);

        SpriteBuilder builder2 = new SpriteBuilder("Images/Asteroid/exp.png", 300,300);
        builder2.addImage(0,0);
        builder2.addImage(1,0);
        builder2.addImage(2,0);
        builder2.addImage(3,0);
        builder2.addImage(4,0);
        builder2.addImage(0,1);
        builder2.addImage(1,1);
        builder2.addImage(2,1);
        builder2.addImage(3,1);
        builder2.addImage(4,1);
        builder2.addImage(0,2);
        builder2.addImage(1,2);
        exp = new AnimationSprite(x, y, builder2.build(),width,height);
        exp.setAnimSpd(20);




    }


    public void tick(){


        if (!dead){
            ad.setsX(x);
            ad.setsY(y);
            ad.update();

            if (y <= 540){
                y  += spd;
            }
            else{
                handler.objects.remove(this);
            }
        }else {
            exp.setsX(x);
            exp.setsY(y);
            exp.update();



            if (counter >= 40){
                handler.objects.remove(this);
            }
            counter++;

        }



    }
    public Rectangle getBounds(){
        return (new Rectangle(getX(), getY(), width, height));
        
    }
    
    @Override
    public void paint(Graphics g) {
        if (!dead){
            ad.render(g);
        }else {
            exp.render(g);
        }



    }
    
    public int getSpd() {
        return spd;
    }
    
    public void setSpd(int spd) {
        this.spd = spd;
    }
    public void toDeath(){

        dead = true;

    }
    public void setDead(boolean dead){
        damage = 0;
        this.dead = dead;

    }

    public int getDamage() {
        return damage;
    }
}