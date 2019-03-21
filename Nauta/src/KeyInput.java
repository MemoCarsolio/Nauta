import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private int key;
    ObjectWInput obj;

    public KeyInput(ObjectWInput obj){
        this.obj = obj;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        key = e.getKeyCode();
        obj.keyTyped(key);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        obj.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        obj.keyReleased(key);
    }
}
