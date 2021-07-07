package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

public class LawnMower {
    private int state; //0: idle, 1: moving, 2: used
    private int yIndex;
    private double y;
    private double x;
    private double DX = 3;
    private static final String MOWER = "resource/lawnmowerIdle.gif";
    private static final String[] ACTIVATED = {"resource/activated0.gif","resource/activated1.gif","resource/activated2.gif","resource/activated3.gif","resource/activated4.gif","resource/activated5.gif","resource/activated6.gif","resource/activated7.gif","resource/activated8.gif","resource/activated9.gif","resource/activated10.gif","resource/activated11.gif","resource/activated12.gif"};
    private int count;

    public LawnMower(double x, double y, int yIndex, int state){
        this.x = x;
        this.y = y;
        this.yIndex = yIndex;
        this.state = state;
    }

    public void drawImageIdle(GraphicsContext gc){
        gc.drawImage(new Image(MOWER), x,y,Main.getSquareSize()*2.5,Main.getSquareSize()*2.5);
    }

    public void drawImageActivated(GraphicsContext gc){
        gc.drawImage(new Image(ACTIVATED[count]), x,y,Main.getSquareSize()*2.5,Main.getSquareSize()*2.5);
        ++(this.count);
        if (this.count == 13){
            this.count = 0;
        }
        x+=DX;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getyIndex() {
        return yIndex;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
