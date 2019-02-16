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
        Attacker potAttacker = null;
        for(int i = 0; i < attackers.size(); i++){
            Attacker a = attackers.get(i);

            if(Math.abs(a.getDistance(field)) < range){
                if(potAttacker == null || a.getRestLength() < potAttacker.getRestLength()){
                    potAttacker = a;
                }
            }
        }
        if(laser != null){
            if((potAttacker == null || laser.getAim().getHealtPoints() <= 0)){
                td.getAllAttackers().remove(laser.getAim());
                td.removeShape(laser.getAim());
                td.removeShape(laser);
                laser = null;
            }else if(potAttacker != null)
            {
                laser.resetAim(potAttacker);
            }
        }else if(laser == null && potAttacker != null){
            laser = new Laser(field, potAttacker, this);
            td.addShot(laser);
        }

        if (laser != null){
            laser.update(field);
            System.out.println(laser.getAim().getHealtPoints());
        }



            //TODO
            // check if potAttacker==laser.aim changed
            //  call laser.update

    }

    @Override
    public int getPrice() {
        return super.price;
    }
}
