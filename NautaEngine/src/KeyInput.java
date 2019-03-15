import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class used to read input form keyboard.
public class KeyInput implements KeyListener{
    
    //Target game object.
    GameObject object;
    
    //Constructor.
    public KeyInput(GameObject object) {
        this.object = object;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        object.keyPressed(key);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        //sprite.keyReleased(key);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}