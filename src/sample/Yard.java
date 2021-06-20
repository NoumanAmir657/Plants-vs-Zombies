package sample;

public class Yard {
    private static int yardHealth = 1000;

    public static void decreaseHealth(){
        Yard.yardHealth-=1;
    }

    public static int getYardHealth(){
        return yardHealth;
    }
}
