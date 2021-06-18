package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.File;

public class Sunflower extends Plants {
    private static final String[] SUNFLOWER = new String[]{"resource/sun1.gif", "resource/sun2.gif", "resource/sun3.gif", "resource/sun4.gif", "resource/sun5.gif"
            ,"resource/sun6.gif","resource/sun7.gif", "resource/sun8.gif", "resource/sun9.gif", "resource/sun10.gif", "resource/sun11.gif"};
    private int sunCount;
    private int counter;
    private final static int COST = 50;
    public int indexOfPosition;
    private int health;
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> run()));

    public Sunflower(){
        sunCount = 0;
        x  = 0;
        y = 0;
        this.health = 50;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void subFromHealth(){
        this.health-=10;
    }

    public Sunflower(double x, double y){
        this.health = 50;
        this.x = x;
        this.y = y;
        //70, 180, 300, 390, 500
        if (this.y >= 70 && this.y <= 180){
            this.indexOfPosition = 0;
        }
        else if (this.y > 180 && this.y <= 280){
            this.indexOfPosition = 1;
        }
        else if (this.y > 280 && this.y <= 390){
            this.indexOfPosition = 2;
        }
        else if (this.y > 390 && this.y <= 500){
            this.indexOfPosition = 3;
        }
        else if (this.y > 500){
            this.indexOfPosition = 4;
        }
    }

    public String[] getImage(){
        return SUNFLOWER;
    }

    public static int getCost(){
        return COST;
    }

    public void drawSunflower(GraphicsContext gc){

        gc.drawImage(new Image(SUNFLOWER[this.sunCount]), x,y,Main.getSquareSize()*2,Main.getSquareSize()*2);
        ++(this.sunCount);
        ++counter;
        if (this.sunCount == 11){
            this.sunCount = 0;
        }

        /*
        File file = new File("C:/Users/HP/IdeaProjects/PvsZ/src/resource/sunflower.gif");
        Image img = new Image(file.toURI().toString(),Main.getSquareSize()*2,Main.getSquareSize()*2,false,false);
        ImageView imgView = new ImageView(img);
        gridPane.add(imgView,0,0);
        */

        if ((counter % 15) == 0){
            Cards.addToValue();
        }
    }

    public void run(){
        subFromHealth();
    }
}
