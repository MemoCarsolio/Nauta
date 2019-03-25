import java.awt.*;

public abstract class ObjectWInput extends GameObject{


    public ObjectWInput(int x, int y, int width, int height, Color color, Handler handler){
        super(x,y,width,height, color, handler);
    }


    public abstract void mouseClicked(int x, int y);

    public abstract void mousePressed(int x, int y);

    public abstract void mouseReleased(int x, int y);

    public abstract void mouseEntered(int x, int y);

    public abstract void mouseExited(int x, int y);

    public abstract void keyTyped(int key);

    public abstract void keyPressed(int key);

    public abstract void keyReleased(int key);


}
