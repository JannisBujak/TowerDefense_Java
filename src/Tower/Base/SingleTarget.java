package Tower.Base;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Tower.Cannon.Laser;
import Tower.Tesla.Thunderbolt;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SingleTarget extends Tower {

    Laser laser;

    public SingleTarget(double damage, double range, double cooldown, int price, Color color, double slow, TowerDefense td){
        super(damage, range, cooldown, price, color, slow, td);
    }

    @Override
    public void update(Field field, TowerDefense towerDefense) {
        ArrayList<Attacker> attackers =  super.td.getAllAttackers();
        for(int i = 0; i < attackers.size(); i++){
            Attacker a = attackers.get(i);

            Attacker potAttacker = null;
            if(Math.abs(a.getDistance(field)) < range){
                if(potAttacker == null || a.getRestLength() < a.getRestLength()){
                    
                }
            }
            System.out.println(a.getHealtPoints());
        }
    }

    @Override
    public int getPrice() {
        return super.price;
    }
}
