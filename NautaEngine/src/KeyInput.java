import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class used to read input form keyboard.
public class KeyInput implements KeyListener{
    

    GameObject object;
    Nauta nau;
    
    //Constructor.
    public KeyInput(GameObject object) {
        this.object = object;
    }
    public KeyInput(Nauta nau){
        this.object = nau;
    }

    


    
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        object.keyPressed(key);

    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
       object   .keyReleased(key);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}