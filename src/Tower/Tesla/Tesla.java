package Tower.Tesla;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tesla extends Tower {

    public static int PRICE = 25;
    public static Color COLOR = Color.BLUE;
    private static int COOLDOWN = 100;
    private static int DAMAGE = 5;
    private static int RANGE = 2;
    private static double SLOW = 0.8;
    private static int MAX_AIMS = 1;



    private ArrayList<Thunderbolt> allThunderbolts = new ArrayList<>();

    public Tesla(TowerDefense td){
        super(DAMAGE, RANGE, COOLDOWN, PRICE, COLOR, SLOW, MAX_AIMS, td);
    }

    @Override
    public int getPrice(){
        return Tesla.getPriceOfTesla();
    }

    public static double getCooldown() {
        return COOLDOWN;
    }

    public static int getPriceOfTesla() {
        return PRICE;
    }


    public void update(Field field, TowerDefense towerDefense){
        ArrayList<Attacker> attackers =  super.td.getAllAttackers();
        for(int i = 0; i < attackers.size(); i++){
            Attacker a = attackers.get(i);

            if(Math.abs(a.getDistance(field)) < range){
                a.setColor(Color.GREY);
                if(!ListOperations.inList(a, allThunderbolts)){
                    if(allThunderbolts.size() < MAX_AIMS){
                        Thunderbolt thunderbolt = new Thunderbolt(field, a, this);
                        allThunderbolts.add(thunderbolt);
                        td.addShot(thunderbolt);
                    }
                }else{

                    Thunderbolt t = allThunderbolts.get(ListOperations.getIndex(a, allThunderbolts));
                    t.update(field);
                    if(a.getHealtPoints() <= 0){
                        td.removeShape(t);
                        allThunderbolts.remove(t);
                        td.removeShape(a);
                        td.getAllAttackers().remove(i);
                        continue;
                    }
                }
            }else{
                int listIndex = ListOperations.getIndex(a, allThunderbolts);
                a.setColor(Color.GREEN);
                if(listIndex >= 0){
                    Thunderbolt t = allThunderbolts.get(listIndex);
                    td.removeShape(t);
                    allThunderbolts.remove(listIndex);
                }
            }
        }
    }
}
