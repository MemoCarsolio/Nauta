import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class Scene implements MouseListener {
    
    protected int width;
    protected int height;
    protected Handler handler;
    protected boolean running;
    
    //Classes for game rendering
    protected BufferStrategy bs;
    protected BufferedImage background;
    protected Graphics g;
    protected JFrame frame;
    protected Window window;
    
    protected Scene(int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window){
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.g = g;
        this.bs = bs;
        this.window = window;
        this.frame = this.window.getFrame();
    
        window.getCanvas().addMouseListener(this);
    
    }
    
    public abstract void loadAssets();
    
    public abstract void render();
    
    public abstract void run();
    
    public abstract void tick();
    
    public abstract void sceneSetup();
    
    @Override
    public abstract void mouseClicked(MouseEvent e);
    
    @Override
    public abstract void mousePressed(MouseEvent e);
    
    @Override
    public abstract void mouseReleased(MouseEvent e);
    
    @Override
    public abstract void mouseEntered(MouseEvent e);
    
    @Override
    public abstract void mouseExited(MouseEvent e);
}
