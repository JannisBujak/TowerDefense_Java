package Tower.Base;

import GUI.TowerDefense;
import Tower.*;
import javafx.scene.paint.Color;

public abstract class Tower {
    private double damage;
    private double range;
    private double cooldown;
    private int price;
    private Color color;

    public Tower(double damage, double range, double cooldown, int price, Color color, TowerDefense td){
        this.damage = damage;
        this.range = range;
        this.cooldown = cooldown;
        this.price = price;
        this.color = color;
    }

    public void update(){
        if(this instanceof Cannon){
            //TODO
        }else if(this instanceof Tesla){
            //TODO
        }

    }

    public Color getColor(){
        return color;
    }

    public abstract int getPrice();
}
