package Tower;

import GUI.TowerDefense;
import Objects.Attacker;
import Objects.Field;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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


    public void update(Field field){
        ArrayList<Attacker> attackers =  super.td.getAllAttackers();
        for(Attacker a : attackers){
            if(Math.abs(a.getDistance(field)) < range){
                a.setColor(Color.GREY);
            }else{
                a.setColor(Color.GREEN);
            }
        }
    }
}
