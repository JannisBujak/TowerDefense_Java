package Pathfinding;



public class Waypoint extends Position{
    private double gCost, hCost, fCost;


    public Waypoint(Position pos, Position currentPos, Position aim){
        super(pos);
        if(currentPos == null){
            gCost = 0;
        }else{
            gCost = Math.sqrt(Math.abs(currentPos.getX() - pos.getX() + currentPos.getY() - pos.getY()));
        }
        if(aim == null){
            hCost = 0;
        }else{
            hCost = Math.sqrt(Math.abs(aim.getX() - pos.getX() + aim.getY() - pos.getY()));
        }
        fCost = gCost + hCost;
    }

    public double getFCost() {
        return fCost;
    }
}
