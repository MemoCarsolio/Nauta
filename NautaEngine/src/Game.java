import java.awt.Color;
import image.Assets;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    
    public static int width, height;
    public String title;
    
    //Game thread.
    public Thread thread;
    
    //Game level
    private enum stage {
        MENU, SPACE, PLANET
    }
    private stage currentStage;
    
    private Button startBtn;
    
    private Window window;
    private Handler handler;
    private Spawner spawner;
    
    //Classes for game rendering
    private BufferStrategy bs;
    private BufferedImage background;
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

        //Create spawner.
        spawner = new Spawner(handler);
        
    }
    
    //Start the game.
    public void start() {
        
        //Set stage to menu.
        currentStage = stage.MENU;
        
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
        g.drawImage(background, 0, 0, width, height, null);
        
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
        
        stageSetup();
        
        while (running)
        {
            runStage();
            
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
    
    public void stageSetup(){
        switch (currentStage){
            
            case MENU:
                
                background = Assets.menu;
                
                startBtn = new Button(width/2 - 40, 250, 80, 40, Color.LIGHT_GRAY, handler);
                
                KeyInput startKeyInput = new KeyInput(startBtn);
                
                window.getFrame().addKeyListener(startKeyInput);
                
                handler.addObj(startBtn);
                
                break;
                
            case SPACE:
                break;
                
            case PLANET:
                
                background = Assets.planet;
                
                spawner.activateAsteroids();
                break;
        }
        
    }
    
    public void runStage(){
        
        switch (currentStage){
            case MENU:
                if (startBtn.isPressed()){
                    currentStage = stage.PLANET;
                    stageSetup();
                    handler.removeObj(startBtn);
                }
                break;
            case SPACE:
                break;
            case PLANET:
                break;
        }
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
}