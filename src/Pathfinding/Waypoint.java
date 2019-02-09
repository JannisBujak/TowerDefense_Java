package Pathfinding;


import GUI.TowerDefense;

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

    public void changeSource(Waypoint source, ArrayList<Waypoint> openList, TowerDefense td) {
        //Break
        if(this.source.equals(source) || distanceTraveled < source.getDistanceTraveled() + getDistance(this, source))   return;

        //Calculating fCost for this WP
        distanceTraveled = source.getDistanceTraveled() + Position.getDistance(source, this.source);
        fCost = distanceTraveled + hCost;
        this.source = source;
        //Call recursive for surroundings
        changeSourceOfSurroundings(openList, td);
    }

    public void changeSourceOfSurroundings(ArrayList<Waypoint> openList, TowerDefense td){
        for(int y = getY() - 1; y <= getY() + 1; y++) {
            for (int x = getX() - 1; x <= getX() + 1; x++) {
                if(!td.inBounds(x, y) || source.equals(x, y) || this.equals(x, y))  continue;
                Waypoint w = Path.getWPbyPos(new Position(x, y), openList);
                if(w == null) continue;
                else{
                    w.changeSource(this, openList, td);
                }
            }
        }
    }
}
