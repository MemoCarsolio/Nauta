import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Window {
    
    private JFrame frame;
    private int width, height;
    private String title;
    
    public static Canvas canvas;
    
    public Window (String title, int width, int height){
        
        this.title = title;
        this.width = width;
        this.height = height;
        
        createWindow();
    }
    
    public void createWindow()
    {
        frame = new JFrame(title);
        
        //Set dimensions.
        frame.setSize(width, height);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension (width, height));
        
        frame.setFocusable(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setResizable(false);
        
        //Make the frame appear on the center fo the window.
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
        
        canvas = new Canvas();
        
        //Set canvas dimensions.
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension (width, height));
        
        canvas.setFocusable(true);
        
        //Add canvas to frame.
        frame.add(canvas);
        
        //Make the frame adapt to the canvas.
        frame.pack();
        
        frame.requestFocus();
    }
    
    public Canvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}