package Tower;

import GUI.TowerDefense;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Tesla extends Tower {
    public static int price = 25;
    public static Color color = Color.BLUE;

    public Tesla(TowerDefense td){
        super(5, 2, 0.1, price, color, td);
    }

    @Override
    public int getPrice(){
        return Tesla.getPriceOfTesla();
    }

    public static int getPriceOfTesla() {
        return price;
    }
}
