import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected double x, y;
    
    protected int width, height;
    
    protected Color color;
    
    protected Handler handler;
    
    public GameObject (double x, double y, int width, int height, Color color, Handler handler)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.handler = handler;
    }
    
    // Todo objeto requiere ser pintado
    public abstract void paint(Graphics g);
    
    // Todo objeto requiere ser actualizado
    public abstract void tick();
    
    // Para obtener los bordes del objeto (nos ayuda con las colisiones)
    public abstract Rectangle getBounds();
    
    // A partir de aqu� son los getters y setters
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
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
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