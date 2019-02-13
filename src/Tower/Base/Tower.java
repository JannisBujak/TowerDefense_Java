package Tower.Base;

import GUI.TowerDefense;
import Objects.Attacker;
import Objects.Field;
import Tower.*;
import javafx.scene.paint.Color;

public abstract class Tower {
    protected  TowerDefense td;
    protected double damage;
    protected double range;
    protected double cooldown;
    protected int price;
    protected Color color;
    protected double slow;
    protected int maxAims;

    public Tower(double damage, double range, double cooldown, int price, Color color, double slow, int maxAims, TowerDefense td){
        this.damage = damage;
        this.range = range;
        this.cooldown = cooldown;
        this.price = price;
        this.color = color;
        this.slow = slow;
        this.maxAims = maxAims;
        this.td = td;
    }

    public  abstract void update(Field field, TowerDefense towerDefense);

    public Color getColor(){
        return color;
    }

    public abstract int getPrice();

    public double getDamageValue(){
        return damage;
    }
}
