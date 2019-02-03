package Pathfinding;

import GUI.TowerDefense;
import Objects.Vector;

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
        if(Math.abs(center.getX() - p1.getX()) == 1 && center.getY() == p1.getY() && Math.abs(center.getY() - p2.getY()) == 1 && center.getX() == p2.getX()){
            return new Position(p1.getX(), p2.getY());
        }else if(Math.abs(center.getY() - p1.getY()) == 1 && center.getX() == p1.getX() && Math.abs(center.getX() - p2.getX()) == 1 && center.getY() == p2.getY()){
            return new Position(p2.getX(), p1.getY());
        }else{
            //System.out.println("Mistake");
            return null;
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

    public void print() {
        System.out.println("(" + x + " | " + y + ")");
    }
}
