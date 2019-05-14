import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Nauta extends Player {
    
    private int vx, vy;
    private double angle, px, py;
    private Window w;
    private BufferedImage img, imgL;
    private AnimationSprite shF;
    private AnimationSprite shR;
    private AnimationSprite shD;
    private AnimationSprite shL;
    private boolean isPaused, isDead, isHit;
    private int direction; //1-up 2-right 3-down 4-left
    private int blastervx, blastervy;
    
    
    public Nauta(int x, int y, int width, int height, Color color, Handler handler, int life, Window w){
        super(x,y,width,height,color,life,handler);
        angle = 0;
        vx = 0;
        vy = 0;
        this.w = w;
        isPaused = false;
        isDead = false;
        direction = 1;
        
        SpriteBuilder builderF = new SpriteBuilder("Images/Nauta/nautaFront.png", 48,48);
        builderF.addImage(1,6);
        builderF.addImage(2,6);
        builderF.addImage(3,6);
        builderF.addImage(4,6);
        shF = new AnimationSprite(x, y, builderF.build(),width,height);
        shF.setAnimSpd(10);
    
        SpriteBuilder builderR = new SpriteBuilder("Images/Nauta/nautaRight.png", 48,48);
        builderR.addImage(0,1);
        builderR.addImage(0,2);
        builderR.addImage(0,3);
        builderR.addImage(0,4);
        shR = new AnimationSprite(x, y, builderR.build(),width,height);
        shR.setAnimSpd(10);
    
        SpriteBuilder builderD = new SpriteBuilder("Images/Nauta/nautaDown.png", 48,48);
        builderD.addImage(0,0);
        builderD.addImage(0,1);
        builderD.addImage(0,2);
        builderD.addImage(0,3);
        shD = new AnimationSprite(x, y, builderD.build(),width,height);
        shD.setAnimSpd(10);
    
        SpriteBuilder builderL = new SpriteBuilder("Images/Nauta/nautaLeft.png", 48,48);
        builderL.addImage(6,0);
        builderL.addImage(6,1);
        builderL.addImage(6,2);
        builderL.addImage(6,3);
        shL = new AnimationSprite(x, y, builderL.build(),width,height);
        shL.setAnimSpd(10);
        
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
            direction = 4;
        }
        if (key == 38){
            vy = - 10;
            direction = 1;
        }
        if (key == 39){
            vx = 10;
            direction = 2;
        }
        if (key == 40){
            vy = 10;
            direction = 3;
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
            
            switch (direction){
                case 1:
                    blastervx = 0;
                    blastervy = -12;
                    break;
                case 2:
                    blastervx = 12;
                    blastervy = 0;
                    break;
                case 3:
                    blastervx = 0;
                    blastervy = 12;
                    break;
                case 4:
                    blastervx = -12;
                    blastervy = 0;
                    break;
            }
            
            Blaster b = new Blaster(direction, x+17, y, blastervx, blastervy,20,20,Color.red,handler);
            handler.addObj(b);
            AudioPlayer.get().playEffectSound("audio/blaster");
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        imgL = ImageLoader.loadImage("Images/Life/life_"+life+".png");
        
        g2d.drawImage(imgL,0,0,200,50,null);
        
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
        
        checkCollision();
        
        if (x+vx <= 960-width && x+vx >= 0 && y+vy >= 0 && y+vy<= 540-height) {
            x += vx;
            y += vy;
        }else {
        
        }
    
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
    }
    
    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }
    
    public void checkCollision(){
        
        ListIterator <GameObject> iterator = handler.objects.listIterator();
        while (iterator.hasNext()){
            GameObject aux = iterator.next();
            if (aux instanceof Gargant ){
                
                
                Rectangle gar = aux.getBounds();
                Rectangle ply = getBounds();
                
                if (ply.intersects(gar)){
                    
                    
                    
                    if (life > 0){
                        life -= ((Gargant) aux).getDamage();
                    }
                    else{
                        isDead = true;
                        
                    }
                    
                    if (((Gargant) aux).getDamage() != 0){
                        ((Gargant) aux).setDead(true);
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

