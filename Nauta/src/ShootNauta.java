import jdk.nashorn.internal.ir.WhileNode;

import java.awt.*;
import java.util.ListIterator;

public class ShootNauta extends GameObject {

    private int vx, vy;
    private AnimationSprite bl;
    private boolean dead;



    public ShootNauta(int vx,int vy,int x, int y, int width, int height, Color color, Handler handler){
        super(x,y,width,height,color,handler);
        this.vy=vy;
        this.vx=vx;
        dead = false;
        SpriteBuilder builder = new SpriteBuilder("Images/Blaster/Bullet.png", 320,320);
        builder.addImage(0,0);
        builder.addImage(0,1);
        builder.addImage(1,0);
        builder.addImage(1,1);
        bl = new AnimationSprite(x, y, builder.build(),width,height);
        bl.setAnimSpd(5);
    }


    @Override
    public void paint(Graphics g) {

        //g.setColor(color);
        //g.fillOval(getX(),getY(),width,height);
        bl.render(g);
    }


    @Override
    public void tick() {

        checkCollision();

        bl.setsX(x);
        bl.setsY(y);
        bl.update();


        //1 left
        //2 right
        //3 up
        //4 down


        if (y+vy >= 0) {
            y += vy;
            x += vx;

        }else {

            handler.objects.remove(this);

        }


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
                        AudioPlayer.get().playEffectSound("audio/explosion");
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
