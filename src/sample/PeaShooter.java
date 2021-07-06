package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PeaShooter extends Plants {
    private static final String[] SHOOTER = new String[]{"resource/peashooter0.gif","resource/peashooter1.gif","resource/peashooter2.gif","resource/peashooter3.gif","resource/peashooter4.gif","resource/peashooter5.gif","resource/peashooter6.gif","resource/peashooter7.gif","resource/peashooter8.gif","resource/peashooter9.gif","resource/peashooter10.gif","resource/peashooter11.gif","resource/peashooter12.gif","resource/peashooter13.gif","resource/peashooter14.gif","resource/peashooter15.gif","resource/peashooter16.gif","resource/peashooter17.gif","resource/peashooter18.gif","resource/peashooter19.gif","resource/peashooter20.gif","resource/peashooter21.gif","resource/peashooter22.gif","resource/peashooter23.gif"};
    private int shooterCount;
    private final static int COST = 100;
    public final static int ATTACKPOWER = 20;
    public final List<Pea> peaList = new ArrayList<>();
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> run()));
    private int health;




    public PeaShooter(){
        shooterCount = 0;
        x = 0;
        y = 0;
        this.health = 50;

    }
    public PeaShooter(double x, double y, int yIndex, int xIndex){
        this.health = 50;
        this.x = x;
        this.y = y;
        setxIndex(xIndex);
        setyIndex(yIndex);
        peaList.add(new Pea(x, y, xIndex, yIndex));
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

    public static String[] getImage() {
        return SHOOTER;
    }

    public static int getCOST() {
        return COST;
    }

    public void drawPeashooter(GraphicsContext gc){
        gc.drawImage(new Image(SHOOTER[this.shooterCount]), x,y,Main.getSquareSize()*2,Main.getSquareSize()*2);
        ++(this.shooterCount);
        if (this.shooterCount == 24){
            this.shooterCount = 0;
        }
    }

    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return health;
    }
    public void subFromHealth(){
        this.health-=10;
    }
    public void run(){
        subFromHealth();
    }
}
