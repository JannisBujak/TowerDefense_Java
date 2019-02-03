package Pathfinding;



public class Waypoint extends Position{
    private double gCost, hCost, fCost;


    public Waypoint(Position pos, Position currentPos, Position aim){
        super(pos);
        if(currentPos == null){
            gCost = 0;
        }else{
            gCost = Math.sqrt(Math.pow(currentPos.getX() - pos.getX(), 2) + Math.pow(currentPos.getY() - pos.getY(), 2));
        }
        if(aim == null){
            hCost = 0;
        }else{
            hCost = Math.sqrt(Math.pow(aim.getX() - pos.getX(), 2) + Math.pow(aim.getY() - pos.getY(), 2));
        }
        fCost = gCost + hCost;
    }

    public double getFCost() {

        return fCost;
    }

    public double gethCost() {
        return hCost;
    }
}
