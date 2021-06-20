package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pea {
    private static final String PEA = "resource/pea.png";
    private double x;
    private double y;
    private int xIndex;
    private int yIndex;

    public Pea(){
        this.x = 0;
        this.y = 0;
    }
    public Pea(double x , double y, int xIndex, int yIndex){
        this.x = x+70;
        this.y = y+10;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void drawImage(GraphicsContext gc) {
        gc.drawImage(new Image(PEA), x, y, Main.getSquareSize()*0.5, Main.getSquareSize()*0.5);
        x+=8;
    }
}
