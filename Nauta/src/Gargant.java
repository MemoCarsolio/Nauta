import java.awt.*;
import java.util.Random;

public class Gargant extends GameObject {
    
    private int spd, counter, damage;
    private boolean dead;
    private boolean attacking;
    private int xNauta, yNauta;
    private AnimationSprite mv, atk;
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
        
        SpriteBuilder moveBuilder = new SpriteBuilder("Images/Gargant/gargant-berserker-move.png", 64, 64);
        moveBuilder.addImage(0, 0);
        moveBuilder.addImage(2, 0);
        moveBuilder.addImage(4, 0);
        moveBuilder.addImage(2, 0);
        
        mv = new AnimationSprite(x, y, moveBuilder.build(), width, height);
        mv.setAnimSpd(5);
        
        SpriteBuilder attackBuilder = new SpriteBuilder("Images/Gargant/gargant-berserker-attack.png", 64, 64);
        attackBuilder.addImage(0, 0);
        attackBuilder.addImage(1, 0);
        attackBuilder.addImage(2, 0);
        attackBuilder.addImage(3, 0);
        attackBuilder.addImage(4, 0);
        
        atk = new AnimationSprite(x, y, attackBuilder.build(), width, height);
        atk.setAnimSpd(1);
    }
    
    @Override
    public void paint(Graphics g) {
        if (!dead & !attacking){
            System.out.println("Moving");
            mv.render(g);
        } else if (!dead & attacking){
            System.out.println("Attacking");
            atk.render(g);
        }
    }
    
    @Override
    public void tick() {
        
        if (!dead & !attacking) {
            mv.setsX(x);
            mv.setsY(y);
            mv.update();
            
            huntNauta();
            
            
        } else if (!dead & attacking){
            atk.setsX(x);
            atk.setsY(y);
            atk.update();
            
        } else {
            handler.objects.remove(this);
        }
        
    }
    
    //Updates the Gargant's position to follow Nauta.
    public void huntNauta(){
        if (xNauta > x & yNauta < y){
            x += spd;
            y -= spd;
        } else if (xNauta > x & yNauta > y){
            x += spd;
            y += spd;
        } else if (xNauta < x & yNauta > y){
            x -= spd;
            y += spd;
        } else {
            x -= spd;
            y -= spd;
        }
    }
    
    public void updateNauta(int x, int y){
        xNauta = x;
        yNauta = y;
    }
    
    @Override
    public Rectangle getBounds() {
        return null;
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
