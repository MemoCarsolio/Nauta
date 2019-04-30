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
    private Scene transition1;
    private Scene transition2;
    private Scene transition3;
    private Scene transition4;

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
        
        String transText1 = "Level 1 \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec consectetur tempus nunc, vel eleifend metus " +
                "consequat nec. Nunc tristique eros ut condimentum aliquet.";
        transition1 = new Transition(transText1, WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        transition1.setChange(2);
        
        String transText2 = " Level 2 \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec consectetur tempus nunc, vel eleifend metus " +
                                    "consequat nec. Nunc tristique eros ut condimentum aliquet.";
        transition2 = new Transition(transText2, WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        transition2.setChange(4);
    
        String transText3 = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec consectetur tempus nunc, vel eleifend metus " +
                                    "consequat nec. Nunc tristique eros ut condimentum aliquet.";
        transition3 = new Transition(transText3, WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        transition3.setChange(-1);


        String transText4 = "Game over";
        transition4 = new Transition(transText4, WIDTH, HEIGHT, gameObjHandler, g, bs, window);
        transition4.setChange(0);

        change = 0;
        
    }
    
    @Override
    public void run() {

    while (change <= 6) {
        switch (change) {

            case 0:
                startMenu.run();
                change = startMenu.getChange();
                break;
            case 1:
                transition1.run();
                change = transition1.getChange();
                break;
            case 2:
                space.run();
                change = space.getChange();
                break;
            case 3:
                transition2.run();
                change = transition2.getChange();
                break;
            case 4:
                planet.run();
                change = planet.getChange();
                break;
            case 5:
                transition3.run();
                change = transition3.getChange();
                break;
            case 6:
                System.out.println("we got here");
                transition4.run();
                change = transition4.getChange();
                break;
            default:
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
