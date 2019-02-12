package Pathfinding;


import GUI.TowerDefense;
import GeneralOperations.ListOperations;

import java.util.ArrayList;

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

    public void changeSource(Waypoint source, ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, TowerDefense td) {
        //Break
        if(this.source.equals(source) || distanceTraveled < source.getDistanceTraveled() + getDistance(this, source))   return;

        //Calculating fCost for this WP
        distanceTraveled = source.getDistanceTraveled() + Position.getDistance(source, this.source);
        fCost = distanceTraveled + hCost;
        this.source = source;
        //Call recursive for surroundings
        changeSourceOfSurroundings(openList, closedList, td);
    }

    public void changeSourceOfSurroundings(ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, TowerDefense td){
        ArrayList<Position> list = Path.optimizedSurroundingsChosed(this, td);
        for(Position p : list){
            if(!td.inBounds(p.getX(), p.getY()) || source.equals(p) || this.equals(p))  continue;
            Waypoint w = ListOperations.getWPbyPos(new Position(p), openList);
            if(w != null) {
                w.changeSource(this, openList, closedList, td);
            }else{
                w = ListOperations.getWPbyPos(new Position(p), closedList);
                if(w != null){
                    w.changeSource(this, openList, closedList, td);
                }
            }
        }
    }
}
