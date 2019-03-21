import java.awt.*;

public class Button extends ObjectWInput {
    
    private boolean pressed;
    
    public Button(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        pressed = false;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y,width,height);
    }
    
    @Override
    public void tick() {}
    
    @Override
    public Rectangle getBounds() {
        return (new Rectangle(getX(), getY(), width, height));
    }
    

    public boolean isPressed(){
        return pressed;
    }


    @Override
    public void mouseClicked(int x, int y) {
        pressed = true;
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseEntered(int x, int y) {

    }

    @Override
    public void mouseExited(int x, int y) {

    }

    @Override
    public void keyTyped(int key) {

    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }
}