package Tower.Base;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Tower.Tesla.Thunderbolt;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Multitarget extends Tower {

    private ArrayList<Thunderbolt> allThunderbolts = new ArrayList<>();

    public Multitarget(double damage, double range, double cooldown, int price, Color color, double slow, TowerDefense td){
        super(damage, range, cooldown, price, color, slow, td);
    }

    @Override
    public void update(Field field, TowerDefense towerDefense) {
        ArrayList<Attacker> attackers =  super.td.getAllAttackers();
        for(int i = 0; i < attackers.size(); i++){
            Attacker a = attackers.get(i);

            if(Math.abs(a.getDistance(field)) < range){
                a.setColor(Color.GREY);
                if(!ListOperations.inList(a, allThunderbolts)){
                    Thunderbolt thunderbolt = new Thunderbolt(field, a, this);
                    allThunderbolts.add(thunderbolt);
                    td.addShot(thunderbolt);
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

    @Override
    public int getPrice() {
        return super.price;
    }
}
