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

    //scenes changer.
    private int change;

    
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

        change = 0;
        
    }
    
    @Override
    public void run() {

    while (change < 3) {
        switch (change) {

            case 0:
                startMenu.run();
                change = startMenu.getChange();
                break;
            case 1:
                space.run();
                change = space.getChange();
                break;
            case 2:
                planet.run();
                change = planet.getChange();
                break;

        }
    }
        

    }
    
    public void graphicsSetup(){
        
        gameObjHandler = new Handler();
        
        window = new Window(TITLE, WIDTH, HEIGHT);
        
        bs = window.getCanvas().getBufferStrategy();
        
    }
    
}
