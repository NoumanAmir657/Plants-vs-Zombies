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
    private static final String[] SUNFLOWER = new String[]{"resource/sun0.gif","resource/sun1.gif","resource/sun2.gif","resource/sun3.gif","resource/sun4.gif","resource/sun5.gif","resource/sun6.gif","resource/sun7.gif","resource/sun8.gif","resource/sun9.gif","resource/sun10.gif","resource/sun11.gif","resource/sun12.gif","resource/sun13.gif","resource/sun14.gif","resource/sun15.gif","resource/sun16.gif","resource/sun17.gif","resource/sun18.gif","resource/sun19.gif","resource/sun20.gif","resource/sun21.gif","resource/sun22.gif","resource/sun23.gif","resource/sun24.gif","resource/sun25.gif","resource/sun26.gif","resource/sun27.gif","resource/sun28.gif","resource/sun29.gif","resource/sun30.gif","resource/sun31.gif","resource/sun32.gif","resource/sun33.gif","resource/sun34.gif","resource/sun35.gif","resource/sun36.gif","resource/sun37.gif","resource/sun38.gif","resource/sun39.gif","resource/sun40.gif","resource/sun41.gif","resource/sun42.gif","resource/sun43.gif","resource/sun44.gif","resource/sun45.gif","resource/sun46.gif","resource/sun47.gif","resource/sun48.gif","resource/sun49.gif","resource/sun50.gif","resource/sun51.gif","resource/sun52.gif","resource/sun53.gif"};
    private int sunCount;
    private int counter;
    private final static int COST = 50;
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

    public Sunflower(double x, double y, int yIndex, int xIndex){
        this.health = 50;
        this.x = x;
        this.y = y;
        setxIndex(xIndex);
        setyIndex(yIndex);
        //70, 180, 300, 390, 500
        /*
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

         */
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
        if (this.sunCount == 54){
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
