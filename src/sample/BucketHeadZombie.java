package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BucketHeadZombie {
    private int health;
    private double x;
    private double y;
    private static final int ATTACKPOWER = 10;
    private static final String[] ZOMBIE = new String[]{"resource/c0.gif", "resource/c1.gif", "resource/c2.gif", "resource/c3.gif", "resource/c4.gif"
            ,"resource/c5.gif","resource/c6.gif", "resource/c7.gif", "resource/c8.gif", "resource/c9.gif", "resource/c10.gif"};
    private int zombieCount = 0;
    private static final double DX = 2;
    private boolean stop;
    private double[] positions = new double[]{70, 180, 290, 400, 510};
    public int yIndex;
    private Plants madeTheStop = null;
    public static int numberOfZombiesMade = 0;



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


    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Plants getMadeTheStop() {
        return madeTheStop;
    }

    public void setMadeTheStop(Plants madeTheStop) {
        this.madeTheStop = madeTheStop;
    }

    public BucketHeadZombie(){
        numberOfZombiesMade++;
        this.x = 330 + 9*110;
        this.yIndex = (int) Math.floor(Math.random()*(4-0+1)+0);
        this.y = positions[this.yIndex]; //increment of 120
        this.stop = false;
        this.health = 100;
    }


    public void drawImage(GraphicsContext gc){
        gc.drawImage(new Image(ZOMBIE[this.zombieCount]), x,y,Main.getSquareSize()*2.5,Main.getSquareSize()*2.5);
        ++(this.zombieCount);
        if (this.zombieCount == 11){
            this.zombieCount = 0;
        }
        if (!stop){
            x-=DX;
        }
    }

    public void decreaseHealth(){
        this.health-= PeaShooter.ATTACKPOWER;
    }

    public int getHealth() {
        return health;
    }

    public boolean getStop(){
        return stop;
    }

    public int getyIndex() {
        return yIndex;
    }

    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }
}

