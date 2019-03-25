import java.awt.*;

public abstract class Player extends ObjectWInput {

    protected int life;



    public Player(int x , int y, int width, int height, Color color, int life, Handler handler){
        super(x,y,width,height,color, handler);
        this.life = life;
    }

    public abstract void death();
    public abstract void hurt();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
