package sample;

import com.sun.source.tree.Scope;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.*;
import java.util.List;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class Main extends Application {
    private static final double WIDTH = 1366;
    private static final double HEIGHT = 700;
    private static final int ROWS = 30;
    private static final int COLUMNS = ROWS;
    private static final double SQUARE_SIZE = WIDTH / ROWS;

    private GraphicsContext gc;
    private Scene scene;

    private final ShovelCard shovelCard = new ShovelCard();
    private Sunflower sunflower = null;
    private final List<Sunflower> sunflowers = new ArrayList<>();
    private final SunflowerCard sunflowerCard = new SunflowerCard();
    private final Cards cards = new Cards();
    private final Shovel shovel = new Shovel();
    private final BucketHeadZombie bucketHeadZombie = new BucketHeadZombie();
    private final List<BucketHeadZombie> bucketHeadZombies = new ArrayList<>();
    private PeaShooter peaShooter = null;
    private final List<PeaShooter> peashooters = new ArrayList<>();
    private final List<Plants> plants = new ArrayList<>();
    private final BurntZombie burntZombie = new BurntZombie();

    public static Group root = new Group();

    private Timer timer = new Timer();
    private TimerTask timertask = new TimerZombies();
    private boolean flag = false;

    private Timeline timeline;
    private Stage primaryStage = new Stage();
    private boolean gameOver = false;

    private static int score;
    public GridPane gridPane = new GridPane();
    public Rectangle[][] rectangleGrid = new Rectangle[5][10];

    private int xCellIndex;
    private int yCellIndex;
    private final List<LawnMower> lawnMowers = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //new code
        int c = 105;
        int r = 120;
        AnchorPane anchorPane = new AnchorPane();
        for (int i = 0; i < 5; ++i){
            RowConstraints row = new RowConstraints(r);
            gridPane.getRowConstraints().add(row);
        }
        for (int i = 0; i < 10; ++i){
            ColumnConstraints column = new ColumnConstraints(c);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 5; ++i){
            for (int j = 0; j < 10; ++j){
                rectangleGrid[i][j] = new Rectangle(c,r);
                rectangleGrid[i][j].setFill(Color.TRANSPARENT);
                gridPane.add(rectangleGrid[i][j],j,i);
                rectangleGrid[i][j].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
                        xCellIndex = GridPane.getColumnIndex((Node) t.getSource()).intValue();
                        yCellIndex = GridPane.getRowIndex((Node) t.getSource()).intValue();
                        System.out.println(xCellIndex);
                        System.out.println(yCellIndex);
                    }
                });
            }
        }
        //gridPane.setGridLinesVisible(true);
        AnchorPane.setLeftAnchor(gridPane,325.0);
        AnchorPane.setTopAnchor(gridPane,75.0);
        anchorPane.getChildren().add(gridPane);
        //new code



        primaryStage.setTitle("Plants vs Zombies");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        root.getChildren().add(anchorPane);
        root.getChildren().add(cards.drawCards());
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        initializeMower();
        createSunflower();
        removePlant();
        createShooter();
        timer.schedule(timertask,2000,4000);

        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);


        timeline.play();
    }


    private void run(GraphicsContext gc){

        setBackground(gc);
        synchronized (lawnMowers){
            for (LawnMower item : lawnMowers){
                if (item.getState() == 0){
                    item.drawImageIdle(gc);
                }
                else if(item.getState() == 1){
                    item.drawImageActivated(gc);
                }
            }
        }
        for (Sunflower item : sunflowers){
            item.drawSunflower(gc);
        }
        root.getChildren().add(cards.setSunCounterValue());
        root.getChildren().add(cards.setWaveValue());

        try{
            synchronized (bucketHeadZombies){
                for(BucketHeadZombie item: bucketHeadZombies){
                    item.drawImage(gc);
                }
            }
        }
        catch (ConcurrentModificationException e){
            System.out.println("exception here");
        }

        for(PeaShooter item: peashooters){
            item.drawPeashooter(gc);
            if (inRow(item)) {
                for (Pea pea : item.peaList) {
                    pea.drawImage(gc);
                }
            }
        }
        stopZombie();
        collision();
        collisionMower();
        for (LawnMower item: lawnMowers){
            if (item.getX() > 1200){
                item.setState(2);
            }
        }

        inRange();

        setHealth();
        checkStoppedZombies();
    }

    public void setBackground(GraphicsContext gc){
        gc.drawImage(new Image("resource/Lawn.jpg"), 0,0,WIDTH,HEIGHT);
    }

    public void createSunflower(){
        cards.sunflowerCard.sunflowerButton.setOnAction((ActionEvent e) -> {
            flag = true;
            scene.setOnMouseClicked((MouseEvent m) -> {
                if (flag){
                    if (Cards.getValue() >= Sunflower.getCost()){
                        //System.out.println(m.getSceneX());
                        //System.out.println(m.getSceneY());
                        sunflower = new Sunflower(430+((xCellIndex-1)*105), 75+(yCellIndex*120),yCellIndex, xCellIndex);
                        //System.out.println(sunflower.indexOfPosition);
                        sunflowers.add(sunflower);
                        plants.add(sunflower);
                        Cards.subFromValue(Sunflower.getCost());
                    }
                }
                flag = false;
            });
        });
    }

    public void createShooter(){
        cards.peaShooterCard.peashooterButton.setOnAction((ActionEvent e) ->{
            flag = true;
            scene.setOnMouseClicked((MouseEvent m) ->{
                if (flag){
                    if (Cards.getValue() >= PeaShooter.getCOST()){
                        peaShooter = new PeaShooter(430+((xCellIndex-1)*105),75+(yCellIndex*120), yCellIndex, xCellIndex);
                        //System.out.println(peaShooter.indexOfPosition);
                        peashooters.add(peaShooter);
                        plants.add(peaShooter);
                        Cards.subFromValue(PeaShooter.getCOST());
                    }
                }
                flag = false;
            });
        });
    }

    public void removePlant(){
        cards.shovelCard.shovelButton.setOnAction((ActionEvent e) -> {
            shovel.setFlag(true);
            scene.setOnMouseClicked((MouseEvent m) -> {
                if (shovel.isFlag()){
                    for (int i = 0; i < plants.size(); ++i){
                        //Math.abs(m.getSceneX() - plants.get(i).getX()) <= 10) && (Math.abs(m.getSceneY() - plants.get(i).getY()) <= 10
                        if ((plants.get(i).getxIndex() == xCellIndex && plants.get(i).getyIndex() == yCellIndex)){
                            for (BucketHeadZombie zombie: bucketHeadZombies){
                                if (zombie.getMadeTheStop() == plants.get(i)){
                                    zombie.setStop(false);
                                }
                            }
                            if (plants.get(i) instanceof Sunflower){
                                for (int j = 0; j < sunflowers.size(); ++j){
                                    if (sunflowers.get(j) == plants.get(i)){
                                        sunflowers.remove(j);
                                    }
                                }
                            }
                            else if(plants.get(i) instanceof PeaShooter){
                                for (int j = 0; j < peashooters.size(); ++j){
                                    if (peashooters.get(j) == plants.get(i)){
                                        peashooters.remove(j);
                                    }
                                }
                            }
                            plants.remove(i);
                            break;
                        }
                    }
                }
                shovel.setFlag(false);
            });
        });
    }

    public static double getSquareSize(){
        return SQUARE_SIZE;
    }

    public void inRange(){
        for (Plants item: plants){
            for (BucketHeadZombie zombie: bucketHeadZombies){
                if ((Math.abs(item.getX() - zombie.getX()) < 2*SQUARE_SIZE && Math.abs(item.getY() - zombie.getY()) < 2*SQUARE_SIZE)) {
                    zombie.setStop(true);
                    zombie.setMadeTheStop(item);
                }
            }
        }
    }

    public boolean inRow(PeaShooter shooter) {
        for (BucketHeadZombie zombie: bucketHeadZombies){
            if (zombie.getyIndex() == shooter.getyIndex() && ((shooter.getX() - zombie.getX()) < 0)){
                return true;
            }
        }
        return false;
    }

    public boolean collision(){
        for (PeaShooter shooter: peashooters){
            for (int j = 0; j < bucketHeadZombies.size(); ++j){
                if (bucketHeadZombies.get(j).getyIndex() == shooter.getyIndex()){
                    for (int i = 0; i < shooter.peaList.size(); ++i){
                        if (Math.abs((shooter.peaList.get(i).getX() + getSquareSize()*0.25) - (bucketHeadZombies.get(j).getX() + getSquareSize()*2.9/2)) < 8){
                            shooter.peaList.remove(i);
                            shooter.peaList.add(new Pea(shooter.getX(),shooter.getY(), shooter.getxIndex(), shooter.getyIndex()));
                            bucketHeadZombies.get(j).decreaseHealth();
                            if (bucketHeadZombies.get(j).getHealth() == 0){
                                burntZombie.setX(bucketHeadZombies.get(j).getX());
                                burntZombie.setY(bucketHeadZombies.get(j).getY());
                                burntZombie.drawImage(gc);
                                bucketHeadZombies.remove(j);
                                score+=5;
                            }
                        }
                        else if (shooter.peaList.get(i).getX() >= 1300){
                            shooter.peaList.remove(i);
                            //shooter.peaList.add(new Pea(shooter.getX(),shooter.getY()));
                        }
                    }
                }
            }
        }
        return false;
    }

    public void collisionMower(){
        synchronized (lawnMowers){
            for (LawnMower item: lawnMowers){
                for (int j = 0; j < bucketHeadZombies.size(); ++j){
                    if (bucketHeadZombies.get(j).getyIndex() == item.getyIndex() && item.getState() == 1){
                        if (Math.abs((item.getX() + getSquareSize()*2.5/2) - (bucketHeadZombies.get(j).getX() + getSquareSize()*2.9/2)) < 3){
                            burntZombie.setX(bucketHeadZombies.get(j).getX());
                            burntZombie.setY(bucketHeadZombies.get(j).getY());
                            burntZombie.drawImage(gc);
                            bucketHeadZombies.remove(j);
                            score+=5;
                        }
                    }
                }
            }
        }
    }


    public void setHealth(){
        for (BucketHeadZombie zombie: bucketHeadZombies){
            if (zombie.isStop()){
                if (zombie.getMadeTheStop() instanceof Sunflower){
                    //((Sunflower) zombie.getMadeTheStop()).subFromHealth();
                    ((Sunflower) zombie.getMadeTheStop()).timeline.play();
                    if (((Sunflower) zombie.getMadeTheStop()).getHealth() <= 0){
                        sunflowers.remove((zombie.getMadeTheStop()));
                        plants.remove((zombie.getMadeTheStop()));
                        zombie.setMadeTheStop(null);
                        zombie.setStop(false);
                    }
                }
                else if(zombie.getMadeTheStop() instanceof  PeaShooter){
                    //((Sunflower) zombie.getMadeTheStop()).subFromHealth();
                    ((PeaShooter) zombie.getMadeTheStop()).timeline.play();
                    if (((PeaShooter) zombie.getMadeTheStop()).getHealth() <= 0){
                        peashooters.remove((zombie.getMadeTheStop()));
                        plants.remove((zombie.getMadeTheStop()));
                        zombie.setMadeTheStop(null);
                        zombie.setStop(false);
                    }
                }
            }
        }
    }

    public void rmPlantOnLowHealth(){
        for (int i = 0; i < sunflowers.size(); ++i){
            if (sunflowers.get(i).getHealth() <= 0){
                for (BucketHeadZombie zombie: bucketHeadZombies){
                    if (sunflowers.get(i) == zombie.getMadeTheStop()){
                        zombie.setMadeTheStop(null);
                        zombie.setStop(false);
                        break;
                    }
                }
                sunflowers.remove(i);
                plants.remove(i);
                break;
            }
        }
    }

    public void stopZombie(){
        for (BucketHeadZombie zombie: bucketHeadZombies){
            if (zombie.getX() < 430){
                zombie.setStop(true);
                for (LawnMower item: lawnMowers){
                    if (item.getyIndex() == zombie.getyIndex() && item.getState() == 0){
                        item.setState(1);
                    }
                }
            }
        }
    }

    public void checkStoppedZombies(){
        for (int i = 0; i < bucketHeadZombies.size(); ++i){
            if (bucketHeadZombies.get(i).getX() < 430){
                Yard.decreaseHealth();
            }
        }
        if (Yard.getYardHealth() < 0){
            //System.out.println("GAME OVER");
            timeline.stop();
            primaryStage.close();
            drawGameOverScreen();
        }
        //System.out.println(Yard.getYardHealth());
    }

    public void drawGameOverScreen(){
        StackPane stackPane = new StackPane();
        Text scr = new Text(String.valueOf("Score: " + score));
        scr.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
        stackPane.getChildren().add(scr);

        Scene scene = new Scene(stackPane,400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Over");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void initializeMower(){
        lawnMowers.add(new LawnMower(325,75, 0, 0));
        lawnMowers.add(new LawnMower(325,195,1, 0));
        lawnMowers.add(new LawnMower(325,315,2, 0));
        lawnMowers.add(new LawnMower(325,435,3, 0));
        lawnMowers.add(new LawnMower(325,555,4, 0));
    }


    class TimerZombies extends TimerTask {
        public void run(){
            synchronized (bucketHeadZombies){
                bucketHeadZombies.add(new BucketHeadZombie());
            }
        }
    }
}
