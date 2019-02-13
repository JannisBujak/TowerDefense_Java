package Tower.Cannon;

import GUI.TowerDefense;
import Objects.Field;
import Tower.Base.SingleTarget;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Cannon extends SingleTarget {

    public static int PRICE = 15;
    public static Color COLOR = Color.RED;

    private static int COOLDOWN = 500;
    private static int DAMAGE = 50;
    private static int RANGE = 4;
    private static double SLOW = 1;

    public Cannon(TowerDefense td){
        super(DAMAGE, RANGE, COOLDOWN, PRICE, COLOR, SLOW, td);
    }

    @Override
    public int getPrice(){
        return Cannon.getPriceOfCannon();
    }

    public static int getPriceOfCannon() {
        return PRICE;
    }
}
