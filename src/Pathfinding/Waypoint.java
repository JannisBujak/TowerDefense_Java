package Pathfinding;



public class Waypoint extends Position{
    private double gCost, hCost, fCost;
    private double distanceTraveled;
    private Waypoint source;


    public Waypoint(Position pos, Position currentPos, Position aim, double distanceTraveled, Waypoint source){
        super(pos);
        gCost = distanceTraveled;
        if(aim == null){
            hCost = 0;
        }else{
            hCost = Math.sqrt(Math.pow(aim.getX() - pos.getX(), 2) + Math.pow(aim.getY() - pos.getY(), 2));
        }
        fCost = gCost + hCost;
        this.distanceTraveled = distanceTraveled;
        this.source = source;
    }

    public double getFCost() {

        return fCost;
    }

    public double gethCost() {
        return hCost;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public Waypoint getSource() {
        return source;
    }

    public void setSource(Waypoint source) {
        distanceTraveled = source.getDistanceTraveled() + Position.getDistance(source, this.source);
        gCost = distanceTraveled;
        fCost = gCost + hCost;
        this.source = source;
    }
}
