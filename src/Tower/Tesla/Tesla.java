package Tower.Tesla;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Pathfinding.Path;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tesla extends Tower {
    public static int price = 25;
    public static Color color = Color.BLUE;

    private ArrayList<Thunderbolt> allThunderbolts = new ArrayList<>();

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


    public void update(Field field, TowerDefense towerDefense){
        ArrayList<Attacker> attackers =  super.td.getAllAttackers();
        for(Attacker a : attackers){

            if(Math.abs(a.getDistance(field)) < range){
                a.setColor(Color.GREY);
                if(!ListOperations.inList(a, allThunderbolts)){
                    Thunderbolt thunderbolt = new Thunderbolt(field, a);
                    allThunderbolts.add(thunderbolt);
                    td.addShot(thunderbolt);
                }
            }else{
                int i = ListOperations.getIndex(a, allThunderbolts);
                a.setColor(Color.GREEN);
                if(i >= 0){
                    Thunderbolt t = allThunderbolts.get(i);
                    td.removeShot(t);
                    allThunderbolts.remove(i);
                }
            }
        }
    }
}
