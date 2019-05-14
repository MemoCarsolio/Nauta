import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.Random;

public class Planet extends Scene {
    
    private int counter, counter2, counter3;
    private Nauta nauta;
    private KeyListener k;
    private Boolean play;
    Random rGen;
    int posx, posy;
    int xNauta, yNauta;
    
    private Pause pause;
    
    protected Planet(int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window) {
        super(width, height, handler, g, bs, window);
        nauta = new Nauta((width/2)-25, 350, 50, 50, Color.red, handler, 10, window);
        play = false;
        running = true;
        
        AudioPlayer.get().setMusicVol(0.8f);
    }
    
    @Override
    public void loadAssets() {
        background = ImageLoader.loadImage("backgrounds/mars_floor.jpg");
    }
    
    @Override
    public void render() {
        bs = window.getCanvas().getBufferStrategy();
    
        //If canvas doesn't have a BufferStrategy.
        if (bs == null) {
        
            //Create one with three buffers and return.
            window.getCanvas().createBufferStrategy(3);
            return;
        }
    
        //Get whatever is drawn in BufferStrategy
        g = bs.getDrawGraphics();
    
        //Clean buffer.
        g.clearRect(0, 0, width, height);
    
        //Draw background.
        g.drawImage(background, 0, 0, width, height, null);
    
        //Draw objects contained in handler.
        handler.render(g);
    
        //Stop using the graphics.
        g.dispose();
    
        //Show the new drawing.
        bs.show();
    }
    
    @Override
    public void run() {
        running = true;
        
        nauta.resetValues(width);
        
        sceneSetup();
    
        //Set the frames per second and initialize the ticks count.
        int fps = 60, ticks = 0;
    
        //Calculate how much time should pass between each tick to meet the required fps.
        double timePerTick = 1000000000 / fps, delta = 0;
    
        //Get lastTime. Initialize now and timer.
        long now, lastTime = System.nanoTime(), timer = 0;
    
        while (running)
        {
            if (nauta.isPaused()){
                System.out.println("Paused");
                pause = new Pause(width, height, handler, g, bs, window, background);
                pause.run();
                nauta.setPaused(false);
                running = pause.isRun();
            }
        
            //Get now time.
            now = System.nanoTime();
        
            //Add elapsed time to delta.
            delta += now - lastTime;
        
            //Add elapsed time to timer.
            timer += now - lastTime;
        
            //Uodate lastTime.
            lastTime = now;
        
            //If its time to tick.
            if (delta >= timePerTick)
            {
            
                tick();
                render();
            
                //Update ticks.
                ticks ++;
            
                //Reset delta.
                delta = 0;
            }
        
            //If a second has passed (using nanoseconds).
            if (timer >= 1000000000)
            {
                System.out.println("Ticks and frames: " + ticks);
            
                ticks = 0;
                timer = 0;
            }
        }
        removeObjects();
    }
    
    public void removeObjects(){
        window.getCanvas().removeKeyListener(k);
        handler.empty();
    }
    
    public void updateGargants(){
        ListIterator<GameObject> iterator = handler.objects.listIterator();
    
        while (iterator.hasNext())
        {
            GameObject obj = iterator.next();
            if (obj instanceof Gargant){
                ((Gargant) obj).updateNauta(xNauta, yNauta);
            }
        }
    }
    
    @Override
    public void tick() {
        
        if(play){
            
            if (counter >= 40){
                 posx = rGen.nextInt(96) * 10;
                 posy = rGen.nextInt(54) * 10;
                 xNauta = nauta.getX();
                 yNauta = nauta.getY();
                 handler.addObj(new Gargant(posx, posy, 120, 120, Color.white, handler, xNauta, yNauta));
                 counter = 0;
            }
            
            counter ++;
            updateGargants();
            handler.tick();
            
            if (nauta.isDead()){
                change = 6;
                running = false;
            }
            
        }
    }
    
    @Override
    public void sceneSetup() {
        rGen = new Random(System.currentTimeMillis());
        play = true;
        k = new KeyInput(nauta);
        window.getCanvas().addKeyListener(k);
        
        handler.addObj(nauta);
        
        loadAssets();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
