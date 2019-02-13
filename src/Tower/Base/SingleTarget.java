package Tower.Base;

import GUI.TowerDefense;
import Objects.Field;
import javafx.scene.paint.Color;

public class SingleTarget extends Tower {

    public SingleTarget(double damage, double range, double cooldown, int price, Color color, double slow, TowerDefense td){
        super(damage, range, cooldown, price, color, slow, td);
    }

    @Override
    public void update(Field field, TowerDefense towerDefense) {

    }

    @Override
    public int getPrice() {
        return super.price;
    }
}
