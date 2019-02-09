package Pathfinding;



public class Waypoint extends Position{
    private double distanceTraveled, hCost, fCost;
    private Waypoint source;


    public Waypoint(Position pos, Position aim, double distanceTraveled, Waypoint source){
        super(pos);
        if(aim == null){
            hCost = 0;
        }else{
            hCost = Math.sqrt(Math.pow(aim.getX() - pos.getX(), 2) + Math.pow(aim.getY() - pos.getY(), 2));
        }
        fCost = distanceTraveled + hCost;
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
        fCost = distanceTraveled + hCost;
        this.source = source;
    }
}
