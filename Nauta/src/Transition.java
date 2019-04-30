import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.concurrent.TimeUnit;

public class Transition extends Scene{
    
    String text;
    String displayText1;
    String displayText2;
    String displayText3;
    int textIndex;
    private BufferedImage background;
    private Button skipBtn;
    MouseListener m;
    
    protected Transition(String text, int width, int height, Handler handler, Graphics g, BufferStrategy bs, Window window) {
        super(width, height, handler, g, bs, window);
        this.text = text;
        textIndex = 1;
    }
    
    @Override
    public void loadAssets() {
        background = ImageLoader.loadImage("backgrounds/nothingness.png");
    }
    
    @Override
    public void render() {
        bs = window.getCanvas().getBufferStrategy();
    
        //If canvas doesn't have a BufferStrategy.
        if (bs == null) {
        
            //Create one with three buffers and return.
            window.getCanvas().createBufferStrategy(3);
            return;
        }
    
        //Get whatever is drawn in BufferStrategy
        g = bs.getDrawGraphics();
    
        //Clean buffer.
        g.clearRect(0, 0, width, height);
    
        //Draw background.
        g.drawImage(background, 0, 0, width, height, null);
        
        //Draw String
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        drawParagraph((Graphics2D) g, text.substring(0, textIndex), 750);
    
        //Draw objects contained in handler.
        handler.render(g);
    
        //Stop using the graphics.
        g.dispose();
    
        //Show the new drawing.
        bs.show();
    }
    
    @Override
    public void run() {
        sceneSetup();
    
        //Set the frames per second and initialize the ticks count.
        int fps = 60, ticks = 0;
    
        //Calculate how much time should pass between each tick to meet the required fps.
        double timePerTick = 1000000000 / fps, delta = 0;
    
        //Get lastTime. Initialize now and timer.
        long now, lastTime = System.nanoTime(), timer = 0;
        
        long textTimer = 0;
    
        while (running)
        {
        
            //Get now time.
            now = System.nanoTime();
        
            //Add elapsed time to delta.
            delta += now - lastTime;
        
            //Add elapsed time to timer.
            timer += now - lastTime;
    
            textTimer += now - lastTime;
        
            //Uodate lastTime.
            lastTime = now;
        
            //If its time to tick.
            if (delta >= timePerTick)
            {
            
                tick();
                render();
            
                //Update ticks.
                ticks ++;
            
                //Reset delta.
                delta = 0;
            }
            
            if (textIndex == 2){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
        
                }
            }
            
            //Increase the number of characters displayed.
            if(textTimer >= 120000000){
                if (textIndex < text.length()){
                    textIndex ++;
                }
                textTimer = 0;
            }
        
            //If a second has passed (using nanoseconds).
            if (timer >= 1000000000)
            {
                System.out.println("Ticks and frames: " + ticks);
            
                ticks = 0;
                timer = 0;
            }
        }
    
        removeObjects();
    }
    
    private void removeObjects() {
        handler.removeObj(skipBtn);
    }
    
    @Override
    public void tick() {
        if (skipBtn.isPressed()){
            change = 2;
            window.getCanvas().removeMouseListener(m);
            running = false;
        }
    }
    
    @Override
    public void sceneSetup() {
        running = true;
    
        loadAssets();
    
        skipBtn = new Button(10, 460, 160, 40, Color.LIGHT_GRAY, handler);
    
        handler.addObj(skipBtn);
        m = new MouseInput(skipBtn);
        window.getCanvas().addMouseListener(m);
    }
    
    public String getDisplayText(){
        return displayText1;
    }
    
    void drawParagraph(Graphics2D g, String paragraph, float width) {
        Font font = new Font("TimesRoman", Font.ITALIC, 28);
        AttributedString styledPar = new AttributedString(paragraph);
        styledPar.addAttribute(TextAttribute.FONT, font);
        LineBreakMeasurer linebreaker = new LineBreakMeasurer(styledPar.getIterator(), g.getFontRenderContext());
        
        
    
    
        float y = 100.0f;
        while (linebreaker.getPosition() < paragraph.length()) {
            TextLayout tl = linebreaker.nextLayout(width);
            
            y += tl.getAscent();
            tl.draw(g, 110, y);
            y += tl.getDescent() + tl.getLeading();
            
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
