import jdk.nashorn.internal.ir.WhileNode;

import java.awt.*;
import java.util.ListIterator;

public class Blaster extends GameObject {

    private int vx, vy;
    private AnimationSprite blV;
    private AnimationSprite blH;
    private boolean dead;
    private int direction;

    public Blaster(int direction, int x, int y, int vx, int vy, int width, int height, Color color, Handler handler){
        super(x,y,width,height,color,handler);
        this.vx = vx;
        this.vy = vy;
        this.direction = direction;
        dead = false;
        
        SpriteBuilder builderV = new SpriteBuilder("Images/Blaster/Bullet.png", 320,320);
        builderV.addImage(0,0);
        builderV.addImage(0,1);
        builderV.addImage(1,0);
        builderV.addImage(1,1);
        blV = new AnimationSprite(x, y, builderV.build(),width,height);
        blV.setAnimSpd(5);
    
        SpriteBuilder builderH = new SpriteBuilder("Images/Blaster/BulletH.png", 320,320);
        builderH.addImage(1,1);
        blH = new AnimationSprite(x, y, builderH.build(),width,height);
        blH.setAnimSpd(5);

    }


    @Override
    public void paint(Graphics g) {

        //g.setColor(color);
        //g.fillOval(getX(),getY(),width,height);
        if (direction == 1 || direction == 3){
            blV.render(g);
        } else {
            blH.render(g);
        }
    }


    @Override
    public void tick() {

        checkCollision();
    
        if (direction == 1 || direction == 3){
            blV.setsX(x);
            blV.setsY(y);
            blV.update();
        } else {
            blH.setsX(x);
            blH.setsY(y);
            blH.update();
        }

        if (y+vy >= 0) {
            y += vy;
        }else {
           handler.objects.remove(this);

        }

        x += vx;
    }

    public void checkCollision(){

        ListIterator <GameObject> iterator =  handler.objects.listIterator();
        
        while (iterator.hasNext()){
            
            GameObject aux = iterator.next();
            
            if (aux instanceof Asteroid){
                Rectangle ast = aux.getBounds();
                Rectangle ply = getBounds();

                if (ply.intersects(ast)){
                    if (((Asteroid) aux).getDamage() != 0){
                        ((Asteroid) aux).setDead(true);
                        handler.removeObj(this);
                    }

                }
            } else if (aux instanceof Gargant){
                
                Rectangle gar = aux.getBounds();
                Rectangle ply2 = getBounds();
                if (ply2.intersects(gar)){
                    
                    if (((Gargant) aux).getDamage() != 0){
                        ((Gargant) aux).setDead(true);
                        handler.removeObj(this);
                    }
            
                }
            }
        }





    }



    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }
    public boolean isDead(){
        return dead;
    }
}
