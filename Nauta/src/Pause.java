import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Pause extends Scene {
    
    //Image of the current gameplay in the instant where the game is paused.
    private BufferedImage background;
    private BufferedImage gradient;
    private Button pauseBtn;
    MouseListener m;
    
    protected Pause(int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window, BufferedImage gameImage) {
        super(width, height, handler, g, bs, window);
        background = gameImage;
    }
    
    @Override
    public void loadAssets() {
        gradient = ImageLoader.loadImage("backgrounds/gradient.png");
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
    
        //Draw gradient.
        g.drawImage(gradient, 0, 0, width, height, null);
    
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
    
        removeObjects();
    }
    
    private void removeObjects() {
        handler.removeObj(pauseBtn);
    }
    
    @Override
    public void tick() {
        //handler.tick();
    
        if (pauseBtn.isPressed()){
            window.getCanvas().removeMouseListener(m);
            running = false;
        }
    }
    
    @Override
    public void sceneSetup() {
        running = true;
        
        loadAssets();
        
        pauseBtn = new Button(width / 2 - 80, 250, 160, 40, Color.LIGHT_GRAY, handler);
    
        handler.addObj(pauseBtn);
        m = new MouseInput(pauseBtn);
        window.getCanvas().addMouseListener(m);
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