import java.awt.Color;
import image.Assets;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    
    public static int width, height;
    public String title;
    
    //Game thread.
    public Thread thread;
    
    private Window window;
    private Handler handler;
    private KeyInput keyInput;
    private Spawner spawner;
    
    //Classes for game rendering
    private BufferStrategy bs;
    private Graphics g;
    
    private boolean running = false;
    
    //Game constructor.
    public Game(String title, int width, int height)
    {
        this.title = title;
        Game.width = width;
        Game.height = height;
    }
    
    //Initialize the necessary resources.
    public void init()
    {
        //Initialize assets.
        Assets.init();
        
        //Create the game window.
        window = new Window(title, width, height);
        
        //Create the handler for game objects.
        handler = new Handler();

        //
        spawner = new Spawner(handler);
        
        //*******************
        //AÑADIR SPRITE AQUÍ
        //*******************
        
        //Create sprite.
        //Sprite sprite = new Sprite(100, 200, 32, 32, Color.WHITE, handler);
        
        //Create KeyInput (KeyListener) for the sprite.
        //keyInput = new KeyInput(sprite);
        
        //Add the listener to the window's frame.
        //window.getFrame().addKeyListener(keyInput);
        
        //Add sprite to handler.
        //handler.addObj(sprite);
        
        //**********************
        //**********************
    }
    
    //Start the game.
    public synchronized void start()
    {
        //Do nothing if game is already running.
        if (running) return;
        
        //Set running to true and initialize thread.
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    //Stop the game.
    public synchronized void stop()
    {
        //Do nothing if game is not running.
        if (!running) return;
    
        //Set running to true and stop thread.
        running = false;
        try {
            thread.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //Update game.
    public void tick()
    {
        handler.tick();
        spawner.tick();
    }
    
    //Render the game.
    public void render()
    {
        
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
        g.drawImage(Assets.background, 0, 0, width, height, null);
        
        //Draw objects contained in handler.
        handler.render(g);
        
        //Stop using the graphics.
        g.dispose();
        
        //Show the new drawing.
        bs.show();
    }
    
    //Check game limits.
    public static double clamp(double var, double min, double max)
    {
        if (var >= max) return var = max;
        else if (var <= min) return var = min;
        else return var;
    }
    
    public static boolean clampBool(double var, double min, double max)
    {
        if (var >= max) return true;
        else if (var <= min) return true;
        else return false;
    }
    
    //Game loop.
    public void run()
    {
        //Initialize resources.
        init();
        
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
        
        //Stop if game isn't running.
        stop();
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
}