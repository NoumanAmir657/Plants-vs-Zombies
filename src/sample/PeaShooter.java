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
    private static final String[] SHOOTER = new String[]{"resource/p0.gif", "resource/p1.gif", "resource/p2.gif", "resource/p3.gif", "resource/p4.gif"
            ,"resource/p5.gif","resource/p6.gif", "resource/p7.gif", "resource/p8.gif", "resource/p9.gif", "resource/p10.gif"};

    private int shooterCount;
    private final static int COST = 100;
    public final static int ATTACKPOWER = 20;
    public int indexOfPosition;
    public final List<Pea> peaList = new ArrayList<>();
    public Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> run()));
    private int health;
    private int yIndex;




    public PeaShooter(){
        shooterCount = 0;
        x = 0;
        y = 0;
        this.health = 50;

    }
    public PeaShooter(double x, double y, int yIndex){
        this.health = 50;
        this.x = x;
        this.y = y;
        this.yIndex = yIndex;
        peaList.add(new Pea(x,y));
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
        if (this.shooterCount == 11){
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
