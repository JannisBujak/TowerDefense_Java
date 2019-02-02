package Tower.Base;

import javafx.scene.paint.Color;

public class Tower {
    private double damage;
    private double range;
    private double cooldown;
    private int price;
    private Color color;

    public  Tower(double damage, double range, double cooldown, int price, Color color){
        this.damage = damage;
        this.range = range;
        this.cooldown = cooldown;
        this.price = price;
        this.color = color;
    }

    public void update(){


    }

    public Color getColor(){
        return color;
    }
}
