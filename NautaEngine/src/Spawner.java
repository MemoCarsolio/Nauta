import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;

// Clase encargada de Spawnear enemigos y llevar el control del nivel
public class Spawner {

    private Handler handler;
    private int counter;
    private boolean asteroidRain;
    Random posx,size;

    public Spawner (Handler handler)
    {
        this.handler = handler;
        counter = 0;
        asteroidRain = false;
        //handler.addObj(new Asteroid(360,0,10,10,Color.WHITE,handler));
        
    }

    public void tick()
    {
    
        if (asteroidRain){
            
            if(counter >= 30){
                posx = new Random(System.currentTimeMillis());
                size = new Random(System.currentTimeMillis());
                int s1 = size.nextInt(5)*10;
                handler.addObj(new Asteroid(posx.nextInt(72)*10,0,s1,s1,Color.WHITE,handler));
        
                counter = 0;
            }
            counter++;
        }


    }

    public void activateAsteroids(){
        asteroidRain = true;
    }

}
