package Pathfinding;


import GUI.*;
import GeneralOperations.ListOperations;
import Objects.Field;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private double length;
    Position currentPos;
    Position aim;
    TowerDefense td;

    ArrayList <Waypoint> fields;

    public Path(Position start, Position aim, TowerDefense td){
        this.currentPos = start;
        this.aim = aim;
        this.td = td;
        fields = PathFromCurrentToAim(start, aim, td);
        this.length = ListOperations.getWPbyPos(aim, fields).getDistanceTraveled();
        System.out.println(length);

    }


    public void print(){
        System.out.println("\n");
        for(Waypoint w : fields){
          w.print();
        }
    }

    public boolean empty(){
        return fields.size() == 0;
    }

    private static Waypoint getLowestFCost(ArrayList<Waypoint> openList){
        if (openList.isEmpty()) return null;
        Waypoint lowest = null;
        for(Waypoint w : openList){
            if(w != null && (lowest == null || w.getFCost() < lowest.getFCost() || (w.getFCost() == lowest.getFCost() &&  w.gethCost() < lowest.gethCost()))){
                lowest = w;
            }
        }
        return lowest;
    }

    public static ArrayList<Position> optimizedSurroundingsChosed(Waypoint w, TowerDefense td){
        ArrayList<Position> optimizedSurroundings = new ArrayList<>();

        optimizedSurroundings.add(new Position(w.getX(), w.getY() - 1));
        optimizedSurroundings.add(new Position(w.getX() + 1, w.getY()));
        optimizedSurroundings.add(new Position(w.getX(), w.getY() + 1));
        optimizedSurroundings.add(new Position(w.getX() - 1, w.getY()));

        for(int i = 0; i < 4; i++){
            Position p1 = optimizedSurroundings.get(i);
            Position p2 = optimizedSurroundings.get((i + 1) % 4);
            if((td.getFieldAt(p1) == null || !td.getFieldAt(p1).isTower()) && (td.getFieldAt(p2) == null || !td.getFieldAt(p2).isTower())) {
                Position p = Position.getCorner(p1, p2, w);
                if(p != null)   optimizedSurroundings.add(p);
            }

        }
        return optimizedSurroundings;
    }



    private static void fillLists(ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, Position aim, TowerDefense td){
        if(openList.isEmpty())  System.exit(7);

        for(Waypoint w = openList.get(0); !openList.isEmpty(); w = getLowestFCost(openList)){
            //Adding surroundings of w into openList

            if (w == null) System.exit(8);

            if(w.equals(aim)){
                closedList.add(w);  openList.remove(w);
                return;
            }
            //Surrounding and adding w into the closed List


            for(Position p : Path.optimizedSurroundingsChosed(w, td)){
                if(!td.inBounds(p.getX(), p.getY()) || w.equals(p) || td.isTower(p.getX(), p.getY()) || ListOperations.inList(p, openList) || ListOperations.inList(p, closedList))    continue;
                Waypoint wp = new Waypoint(p, aim, w.getDistanceTraveled() + Position.getDistance(p, w), w);
                wp.changeSourceOfSurroundings(openList, closedList, td);

                openList.add(wp);
            }

            closedList.add(w);  openList.remove(w);
        }
    }

    private static void constructWay(ArrayList<Waypoint> way, Position start, Position aim, ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, TowerDefense td) {
        Waypoint next = null;
        for(Waypoint wp = closedList.get(closedList.size() - 1); !wp.equals(start); wp = next, next = null){
            //wp.print();
            way.add(wp);
            ArrayList<Position> list = Path.optimizedSurroundingsChosed(wp, td);
            for(Position p : list){
                Waypoint WatP = ListOperations.getWPbyPos(p, openList);
                if(WatP == null)    WatP = ListOperations.getWPbyPos(p, closedList);
                if(WatP != null && !ListOperations.inList(WatP, way) && (next == null || WatP.getFCost() < next.getFCost() || (WatP.getFCost() == next.getFCost() && WatP.gethCost() < next.gethCost()))){
                    next = WatP;
                }
            }
            if(next == null)    System.exit(5);
        }

        ListOperations.turnListArround(way);
    }

    public static ArrayList<Waypoint> PathFromCurrentToAim(Position start, Position aim, TowerDefense td){
        ArrayList<Waypoint> way = new ArrayList<>();
        ArrayList<Waypoint> openList = new ArrayList<>();
        ArrayList<Waypoint> closedList = new ArrayList<>();
        Waypoint startWP = new Waypoint(start, aim, 0, null);
        openList.add(startWP);

        fillLists(openList, closedList, aim, td);

        Path.constructWay(way, start, aim, openList, closedList, td);

        //TODO Colorize
        //Path.setColors(openList, closedList, way, td);

        return way;
    }

    public static void setColors(ArrayList<Waypoint> openList, ArrayList<Waypoint> closedList, ArrayList<Waypoint> way, TowerDefense td){
        for(Waypoint w : openList){   td.getFieldAt(w).setColor(Color.GREEN);  }
        for(Waypoint w : closedList){
            Field f = td.getFieldAt(w);
            if(f != null)   f.setColor(Color.BLUE);
        }
        for(Waypoint w : way){
            Field f = td.getFieldAt(w);
            if(f != null)   f.setColor(Color.GREY);
        }
    }

    public Position getFirst() {
        return fields.get(0);
    }

    public void deleteFirst(){
        fields.remove(0);
    }

    public void update(){
        fields = PathFromCurrentToAim(currentPos, aim, td);
        Waypoint w = ListOperations.getWPbyPos(aim, fields);
        if(w != null)   this.length  = w.getDistanceTraveled();
        System.out.println(length);
    }

    public double getLength() {
        return length;
    }
}
