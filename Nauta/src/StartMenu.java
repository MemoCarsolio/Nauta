import com.sun.media.sound.AuFileReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;

public class StartMenu extends Scene{
    
    Button startBtn, quitBtn;
    MouseListener s, q;
    
    private static BufferedImage background;
    
    public StartMenu(int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window) {
        super(width, height, handler, g, bs, window);

    }
    
    @Override
    public void loadAssets() {
        background = ImageLoader.loadImage("backgrounds/menu.jpg");
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

        AudioPlayer.get().setMusicVol(0.8f);
        AudioPlayer.get().playBackMusic("audio/ss");
        
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
        AudioPlayer.get().stopBackMusic();
        removeObjects();
    }
    
    private void removeObjects() {

        handler.removeObj(startBtn);
        handler.removeObj(quitBtn);
    }
    
    @Override
    public void tick() {
        handler.tick();

        if (startBtn.isPressed()){
            change = 4;
            window.getCanvas().removeMouseListener(s);
            running = false;
            AudioPlayer.get().stopBackMusic();
        }
        if (quitBtn.isPressed()){
            change = 7;
            window.getCanvas().removeMouseListener(q);
            running = false;
            AudioPlayer.get().stopBackMusic();
        }
    }
    
    @Override
    public void sceneSetup() {
        running = true;
        
        loadAssets();
        
        startBtn = new Button(width / 2 - 80, 250, 200, 80, Color.LIGHT_GRAY, handler, "start1", "start2");
        quitBtn = new Button(width / 2 - 80, 320, 200, 80, Color.LIGHT_GRAY, handler, "quit1", "quit2");
        handler.addObj(startBtn);
        handler.addObj(quitBtn);
        q = new MouseInput(quitBtn);
        s = new MouseInput(startBtn);
        window.getCanvas().addMouseListener(s);
        window.getCanvas().addMouseListener(q);
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
