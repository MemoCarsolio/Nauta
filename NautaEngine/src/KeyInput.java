import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class used to read input form keyboard.
public class KeyInput implements KeyListener{
    
    //Target game object.
    //Sprite sprite;
    
    //Constructor.
    /*public KeyInput(Sprite sprite) {
        this.sprite = sprite;
    }*/
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        //sprite.keyPressed(key);
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