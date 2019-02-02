package Tower;

import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Cannon extends Tower {

    public static int price;
    public static Color color = Color.RED;

    public Cannon(){
        super(10, 2, 0.5, price, color);
    }

    public static int getPrice() {
        return price;
    }
}
