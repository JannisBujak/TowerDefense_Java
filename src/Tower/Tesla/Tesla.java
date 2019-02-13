package Tower.Tesla;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Tower.Base.Multitarget;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tesla extends Multitarget {

    public static int PRICE = 25;
    public static Color COLOR = Color.BLUE;
    private static int COOLDOWN = 100;
    private static int DAMAGE = 5;
    private static int RANGE = 2;
    private static double SLOW = 0.8;


    public Tesla(TowerDefense td){
        super(DAMAGE, RANGE, COOLDOWN, PRICE, COLOR, SLOW, td);
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


}
