import java.awt.*;
import java.util.Random;

public class Gargant extends GameObject {
    
    private int spd, counter, damage;
    private boolean dead;
    private boolean attacking;
    private int xNauta, yNauta;
    private AnimationSprite shF, shR, shD, shL;
    private int direction;
    Random rGen;
    
    public Gargant(int x, int y, int width, int height, Color color, Handler handler, int xNauta, int yNauta) {
        super(x, y, width, height, color, handler);
        rGen = new Random(System.currentTimeMillis());
        spd = 2;
        dead = false;
        counter = 0;
        damage = 1;
        this.xNauta = xNauta;
        this.yNauta = yNauta;
        
        SpriteBuilder builderF = new SpriteBuilder("Images/Gargant/gargantFront.png", 64, 64);
        builderF.addImage(0, 0);
        builderF.addImage(1, 0);
        builderF.addImage(2, 0);
        builderF.addImage(3, 0);
        builderF.addImage(4, 0);
        builderF.addImage(5, 0);
        builderF.addImage(6, 0);
        builderF.addImage(7, 0);
        shF = new AnimationSprite(x, y, builderF.build(), width, height);
        shF.setAnimSpd(5);
        
        SpriteBuilder builderR = new SpriteBuilder("Images/Gargant/gargantRight.png", 64, 64);
        builderR.addImage(0, 0);
        builderR.addImage(0, 1);
        builderR.addImage(0, 2);
        builderR.addImage(0, 3);
        builderR.addImage(0, 4);
        builderR.addImage(0, 5);
        builderR.addImage(0, 6);
        builderR.addImage(0, 7);
        shR = new AnimationSprite(x, y, builderR.build(), width, height);
        shR.setAnimSpd(5);
    
        SpriteBuilder builderD = new SpriteBuilder("Images/Gargant/gargantDown.png", 64, 64);
        builderD.addImage(0, 0);
        builderD.addImage(1, 0);
        builderD.addImage(2, 0);
        builderD.addImage(3, 0);
        builderD.addImage(4, 0);
        builderD.addImage(5, 0);
        builderD.addImage(6, 0);
        builderD.addImage(7, 0);
        shD = new AnimationSprite(x, y, builderD.build(), width, height);
        shD.setAnimSpd(5);
    
        SpriteBuilder builderL = new SpriteBuilder("Images/Gargant/gargantLeft.png", 64, 64);
        builderL.addImage(0, 0);
        builderL.addImage(0, 1);
        builderL.addImage(0, 2);
        builderL.addImage(0, 3);
        builderL.addImage(0, 4);
        builderL.addImage(0, 5);
        builderL.addImage(0, 6);
        builderL.addImage(0, 7);
        shL = new AnimationSprite(x, y, builderL.build(), width, height);
        shL.setAnimSpd(5);
    }
    
    @Override
    public void paint(Graphics g) {
        
        switch (direction){
            case 1:
                shF.render(g);
                break;
            case 2:
                shR.render(g);
                break;
            case 3:
                shD.render(g);
                break;
            case 4:
                shL.render(g);
                break;
        }
    }
    
    @Override
    public void tick() {
    
        switch (direction){
            case 1:
                shF.setsX(x);
                shF.setsY(y);
                shF.update();
                break;
            case 2:
                shR.setsX(x);
                shR.setsY(y);
                shR.update();
                break;
            case 3:
                shD.setsX(x);
                shD.setsY(y);
                shD.update();
                break;
            case 4:
                shL.setsX(x);
                shL.setsY(y);
                shL.update();
                break;
        }
        
        if (dead){
            handler.objects.remove(this);
        }
        
    }
    
    //Updates the Gargant's position to follow Nauta.
    public void huntNauta(){
        if (xNauta > x & yNauta < y){
            x += spd;
            y -= spd;
            direction = 1;
        } else if (xNauta > x & yNauta > y){
            x += spd;
            y += spd;
            direction = 2;
        } else if (xNauta < x & yNauta > y){
            x -= spd;
            y += spd;
            direction = 3;
        } else {
            x -= spd;
            y -= spd;
            direction = 4;
        }
    }
    
    public void updateNauta(int x, int y){
        xNauta = x;
        yNauta = y;
        huntNauta();
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
    
    public int getSpd(){
        return spd;
    }
    
    public void setSpd(int spd){
        this.spd = spd;
    }
    
    public void toDeath(){
        dead = true;
    }
    
    public void setDead(boolean dead){
        damage = 0;
        this.dead = dead;
    }
    
    public int getDamage(){
        return damage;
    }
}
