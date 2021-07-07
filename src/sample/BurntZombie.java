package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BurntZombie {
    private static final String[] BURNTZOMBIE = new String[]{"resource/burnt0.gif","resource/burnt1.gif","resource/burnt2.gif","resource/burnt3.gif","resource/burnt4.gif","resource/burnt5.gif","resource/burnt6.gif","resource/burnt7.gif","resource/burnt8.gif","resource/burnt9.gif","resource/burnt10.gif","resource/burnt11.gif","resource/burnt12.gif","resource/burnt13.gif","resource/burnt14.gif","resource/burnt15.gif","resource/burnt16.gif","resource/burnt17.gif","resource/burnt18.gif","resource/burnt19.gif","resource/burnt20.gif","resource/burnt21.gif","resource/burnt22.gif","resource/burnt23.gif","resource/burnt24.gif","resource/burnt25.gif","resource/burnt26.gif","resource/burnt27.gif","resource/burnt28.gif","resource/burnt29.gif"};

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
        timeline.setCycleCount(29); //21 because of 21 frames.
        timeline.play();
    }
    public void run(GraphicsContext gc){
        gc.drawImage(new Image(BURNTZOMBIE[zombieCount]), x,y-20,Main.getSquareSize()*2.9,Main.getSquareSize()*2.9);
        ++(this.zombieCount);
        if (this.zombieCount == 30){
            this.zombieCount = 0;
        }
    }
}
