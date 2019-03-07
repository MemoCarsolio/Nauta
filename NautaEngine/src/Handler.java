import java.awt.Graphics;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

//Class used to handle game objects.
public class Handler {
    
    //Game objects list.
    public CopyOnWriteArrayList <GameObject> obj;
    
    //Constructor.
    public Handler()
    {
        obj = new CopyOnWriteArrayList <GameObject>();
    }
    
    //Update game objects.
    public void tick()
    {
        
        ListIterator <GameObject> iterator = obj.listIterator();
        while (iterator.hasNext())
        {
            GameObject aux = iterator.next();
            aux.tick();
        }
    }
    
    //Render game objects.
    public void render(Graphics g)
    {
        
        ListIterator <GameObject> iterator = obj.listIterator();
        
        while (iterator.hasNext())
        {
            GameObject aux = iterator.next();
            aux.paint(g);
        }
    }
    
    //Add game object.
    public void addObj(GameObject obj)
    {
        this.obj.add(obj);
    }
    
    //Remove game object.
    public void removeObj(GameObject obj)
    {
        this.obj.remove(obj);
    }
}