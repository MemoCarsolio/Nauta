import java.awt.*;

public class Button extends GameObject {
    
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
    
    public void press() {
        pressed = true;
        System.out.println("Button pressed.");
    }
    
    public boolean isPressed(){
        return pressed;
    }
}