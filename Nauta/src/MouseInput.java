import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private int x,y;
    //Button obj;
    ObjectWInput obj;


    public MouseInput(ObjectWInput obj){
        this.obj = obj;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        obj.mouseClicked(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        obj.mousePressed(x,y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        obj.mouseReleased(x,y);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        obj.mouseEntered(x,y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        obj.mouseExited(x,y);
    }
}
