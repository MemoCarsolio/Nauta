import java.awt.*;

public class Button extends GameObject {
    
    private boolean pressed;
    
    public Button(double x, double y, int width, int height, Color color, Handler handler) {
        super(x, y, width, height, color, handler);
        pressed = false;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(getX(),getY(),width,height);
    }
    
    @Override
    public void tick() {
    
    }
    
    @Override
    public Rectangle getBounds() {
        return null;
    }
    
    @Override
    public void keyPressed(int e) {
        pressed = true;
        System.out.println("Button pressed.");
    }
    
    public boolean isPressed(){
        return pressed;
    }
}
