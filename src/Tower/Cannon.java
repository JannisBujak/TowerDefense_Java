package Tower;

import GUI.TowerDefense;
import Objects.Field;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Cannon extends Tower {

    public static int price = 15;
    public static Color color = Color.RED;

    public Cannon(TowerDefense td){
        super(10, 2, 0.5, price, color, td);
    }

    @Override
    public int getPrice(){
        return Cannon.getPriceOfCannon();
    }

    public static int getPriceOfCannon() {
        return price;
    }

    public void update(Field field){

    }
}
