import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected int x, y;
    
    protected int width, height;
    
    protected Color color;
    
    public GameObject (int x, int y, int width, int height, Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    //Paint object.
    public abstract void paint(Graphics g);
    
    //Update object.
    public abstract void tick();
    
    //Get bounds for collision.
    public abstract Rectangle getBounds();
    
    public int getX()
    {
        return (int)x;
    }
    public int getY()
    {
        return (int)y;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Color getColor()
    {
        return color;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
}