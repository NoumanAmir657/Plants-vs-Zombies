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
    private static final String[] ZOMBIE = new String[]{"resource/conehead0.gif","resource/conehead1.gif","resource/conehead2.gif","resource/conehead3.gif","resource/conehead4.gif","resource/conehead5.gif","resource/conehead6.gif","resource/conehead7.gif","resource/conehead8.gif","resource/conehead9.gif","resource/conehead10.gif","resource/conehead11.gif","resource/conehead12.gif","resource/conehead13.gif","resource/conehead14.gif","resource/conehead15.gif","resource/conehead16.gif","resource/conehead17.gif","resource/conehead18.gif","resource/conehead19.gif","resource/conehead20.gif","resource/conehead21.gif","resource/conehead22.gif","resource/conehead23.gif","resource/conehead24.gif","resource/conehead25.gif","resource/conehead26.gif","resource/conehead27.gif","resource/conehead28.gif","resource/conehead29.gif","resource/conehead30.gif","resource/conehead31.gif","resource/conehead32.gif","resource/conehead33.gif","resource/conehead34.gif","resource/conehead35.gif","resource/conehead36.gif","resource/conehead37.gif","resource/conehead38.gif","resource/conehead39.gif","resource/conehead40.gif","resource/conehead41.gif","resource/conehead42.gif","resource/conehead43.gif","resource/conehead44.gif","resource/conehead45.gif","resource/conehead46.gif","resource/conehead47.gif","resource/conehead48.gif","resource/conehead49.gif","resource/conehead50.gif"};
    private int zombieCount = 0;
    private static final double DX = 1;
    private boolean stop;
    private double[] positions = new double[]{75, 195, 315, 435, 555};
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
        this.x = 350 + 9*105;
        this.yIndex = (int) Math.floor(Math.random()*(4-0+1)+0);
        this.y = positions[this.yIndex]; //increment of 120
        this.stop = false;
        this.health = 100;
    }


    public void drawImage(GraphicsContext gc){
        gc.drawImage(new Image(ZOMBIE[this.zombieCount]), x,y-20,Main.getSquareSize()*2.9,Main.getSquareSize()*2.9);
        ++(this.zombieCount);
        if (this.zombieCount == 51){
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

