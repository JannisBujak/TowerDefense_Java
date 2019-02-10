package Pathfinding;

import GUI.TowerDefense;
import Objects.Vector;
import javafx.geometry.Pos;

public class Position {

    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(Position p){
        this.x = p.getX();
        this.y = p.getY();
    }

    public boolean neighbour(Position cmp){
        return (cmp.getX() >= x - 1 && cmp.getX() <= x + 1 && cmp.getY() >= y - 1 && cmp.getY() <= y + 1);
    }

    public static boolean cuttingTower(Position p1, Position p2, TowerDefense td){
        if(p1.getX() == p2.getX() || p1.getY() == p2.getY())     return false;
        Position[] p = new Position[2];
        p[0] = new Position(p1.getX(), p2.getY());
        p[1] = new Position(p2.getX(), p1.getY());
        return  !( (td.getFieldAt(p[0]) == null || !td.getFieldAt(p[0]).isTower()) && (td.getFieldAt(p[1]) == null || !td.getFieldAt(p[1]).isTower()) );
    }

    public  static Position getCorner(Position p1, Position p2, Position center){
        if(p1 == null || p2 == null || center == null)  return null;
        if(!center.neighbour(p1) || !center.neighbour(p2) || !p1.neighbour(p2 ) || p1.getX() == p2.getX() || p1.getY() == p2.getY()){
            return  null;
        }else if(center.getX() != p1.getX()){
            return new Position(p1.getX(), p2.getY());
        }else{
            return new Position(p2.getX(), p1.getY());
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveInDirection(Vector v){
        this.x += v.getX();
        this.y += v.getY();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean equals(Position p){
        return (x == p.getX() && y == p.getY());
    }

    public boolean equals(int x, int y){
        return (this.x == x && this.y == y);
    }


    public static double getDistance(Position p1, Position p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public void print() {
        System.out.println("(" + x + " | " + y + ")");
    }
}
