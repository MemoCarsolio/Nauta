import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;
    private static final String TITLE = "Nauta";
    
    //Game scenes.
    private Scene startMenu;
    private Scene space;
    private Scene planet;
    
    //GameObject's handler.
    private Handler gameObjHandler;
    
    private Graphics g;
    private BufferStrategy bs;
    
    private Window window;
    
    public Game(){
        
        graphicsSetup();
        
        startMenu = new StartMenu(WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        space = new Space(WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        planet = new Planet(WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        
    }
    
    @Override
    public void run() {
        
        startMenu.run();
        space.run();
        planet.run();
    }
    
    public void graphicsSetup(){
        
        gameObjHandler = new Handler();
        
        window = new Window(TITLE, WIDTH, HEIGHT);
        
        bs = window.getCanvas().getBufferStrategy();
        
    }
    
}
