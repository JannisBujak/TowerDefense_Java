package Pathfinding;

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
