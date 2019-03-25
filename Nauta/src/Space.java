import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Space extends Scene {
    
    private int counter;
    private Ship ship;
    Random posx, size;
    
    protected Space(int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window) {
        super(width, height, handler, g, bs, window);

        ship = new Ship(300, 300,50,50,Color.red, handler,10, window);


        running = true;
    }
    
    @Override
    public void loadAssets() {
        background = ImageLoader.loadImage("backgrounds/space.png");
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
    
        sceneSetup();
    
        //Set the frames per second and initialize the ticks count.
        int fps = 60, ticks = 0;
    
        //Calculate how much time should pass between each tick to meet the required fps.
        double timePerTick = 1000000000 / fps, delta = 0;
    
        //Get lastTime. Initialize now and timer.
        long now, lastTime = System.nanoTime(), timer = 0;
    
        while (running)
        {
        
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
        
    }
    
    @Override
    public void tick() {
    
        if(counter >= 25){
            posx = new Random(System.currentTimeMillis());
            size = new Random(System.currentTimeMillis());
            int s1 = size.nextInt(10)*10+20;
            handler.addObj(new Asteroid(posx.nextInt(96)*10,0,s1,s1,Color.WHITE, handler));
        
            counter = 0;
        }
        counter++;
        
        handler.tick();
    }
    
    @Override
    public void sceneSetup() {
        KeyListener k = new KeyInput(ship);
        window.getCanvas().addKeyListener(k);

        handler.addObj(ship);

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
