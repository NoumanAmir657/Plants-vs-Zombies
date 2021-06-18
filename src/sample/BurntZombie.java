package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BurntZombie {
    private static final String[] BURNTZOMBIE = new String[]{"resource/bz1.gif", "resource/bz2.gif", "resource/bz3.gif", "resource/bz4.gif", "resource/bz5.gif"
            ,"resource/bz6.gif","resource/bz7.gif", "resource/bz8.gif", "resource/bz9.gif", "resource/bz10.gif", "resource/bz11.gif",
            "resource/bz12.gif", "resource/bz13.gif", "resource/bz14.gif", "resource/bz15.gif", "resource/bz16.gif"
            ,"resource/bz17.gif","resource/bz18.gif", "resource/bz19.gif", "resource/bz20.gif", "resource/bz21.gif", "resource/bz22.gif"};

    private double x;
    private double y;
    private int zombieCount = 0;
    private Timeline timeline;

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public Timeline getTimeline(){
        return timeline;
    }

    public void drawImage(GraphicsContext gc){
        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> run(gc)));
        timeline.setCycleCount(21); //21 because of 21 frames.
        timeline.play();
    }
    public void run(GraphicsContext gc){
        gc.drawImage(new Image(BURNTZOMBIE[zombieCount]), x,y,Main.getSquareSize()*2.5,Main.getSquareSize()*2.5);
        ++(this.zombieCount);
        if (this.zombieCount == 21){
            this.zombieCount = 0;
        }
    }
}
