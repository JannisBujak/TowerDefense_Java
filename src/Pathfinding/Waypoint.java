package Pathfinding;


import GUI.TowerDefense;

import java.util.ArrayList;

public class Waypoint extends Position{

    private double distanceTraveled, hCost, fCost;
    private Waypoint source;


    public Waypoint(Position pos, Position aim, double distanceTraveled, Waypoint source, ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, TowerDefense td){
        super(pos);
        if(aim == null){
            hCost = 0;
        }else{
            hCost = Math.sqrt(Math.pow(aim.getX() - pos.getX(), 2) + Math.pow(aim.getY() - pos.getY(), 2));
        }
        fCost = distanceTraveled + hCost;
        this.distanceTraveled = distanceTraveled;
        this.source = source;
        changeSourceOfSurroundings(openList, closedList, td);
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
            if(td.inBounds(p.getX(), p.getY()) && (source == null || !source.equals(p)) && !this.equals(p))
            {
                Waypoint w1 = Path.getWPbyPos(p, openList);
                Waypoint w2 = Path.getWPbyPos(p, closedList);
                if(w1 != null)  w1.changeSource(this, openList, closedList, td);
                else if(w2 != null) w2.changeSource(this, openList, closedList, td);
                else            continue;
            }
        }
    }
}
