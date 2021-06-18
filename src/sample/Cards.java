package sample;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class Cards {
    public SunflowerCard sunflowerCard = new SunflowerCard();
    public ShovelCard shovelCard = new ShovelCard();
    public PeaShooterCard peaShooterCard = new PeaShooterCard();
    public Sun sun = new Sun();
    public GridPane pane = new GridPane();
    private static int value = 100;
    private final Text counter = new Text();
    private final Text wave = new Text();

    public GridPane drawCards(){

        pane.setAlignment(Pos.TOP_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);

        sunflowerCard.sunflowerButton.setPrefSize(105,67);
        sunflowerCard.sunflowerButton.setGraphic(new ImageView(sunflowerCard.image));
        pane.add(sunflowerCard.sunflowerButton, 0,10);

        shovelCard.shovelButton.setPrefSize(80,80);
        shovelCard.shovelButton.setGraphic(new ImageView(shovelCard.image));
        pane.add(shovelCard.shovelButton, 10,0);

        peaShooterCard.peashooterButton.setPrefSize(105,67);
        peaShooterCard.peashooterButton.setGraphic(new ImageView(peaShooterCard.image));
        pane.add(peaShooterCard.peashooterButton, 0,14);


        pane.add(new ImageView(sun.getSun()), 0,0);

        return pane;

    }

    public static void addToValue(){
        value+=5;
    }
    public static int getValue(){
        return value;
    }
    public static void subFromValue(int cost){
        value = value - cost;
    }


    public GridPane setSunCounterValue(){
        GridPane pane2 = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane2.setHgap(4);
        pane2.setVgap(4);
        counter.setText(String.valueOf(value));
        counter.setFont(Font.font("Courier", FontWeight.BOLD, 50));
        counter.setFill(Color.WHITE);
        pane2.add(counter,25,2);
        return pane2;


    }

    public GridPane setWaveValue(){
        GridPane pane3 = new GridPane();
        pane3.setAlignment(Pos.TOP_CENTER);
        pane3.setHgap(4);
        pane3.setVgap(4);
        wave.setText("Wave " + String.valueOf(BucketHeadZombie.numberOfZombiesMade / 20 + 1));
        wave.setFont(Font.font("Courier", FontWeight.BOLD, 50));
        wave.setFill(Color.WHITE);
        pane3.add(wave, 100,0);
        return pane3;
    }

}
