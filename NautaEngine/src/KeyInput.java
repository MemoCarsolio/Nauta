import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class used to read input form keyboard.
public class KeyInput implements KeyListener{
    
    //Target game object.
    //Sprite sprite;
    Nauta nau;
    
    //Constructor.
    /*public KeyInput(Sprite sprite) {
        this.sprite = sprite;
    }*/
    public KeyInput(Nauta n){
        nau = n;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("hi");
        int key = e.getKeyCode();


        nau.keyPressed(key);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        nau.keyReleased(key);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}