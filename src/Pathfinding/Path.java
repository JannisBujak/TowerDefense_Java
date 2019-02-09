package Pathfinding;


import GUI.*;
import Objects.Field;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Path {
    Position currentPos;
    Position aim;
    TowerDefense td;

    ArrayList <Waypoint> fields;

    public Path(Position start, Position aim, TowerDefense td){
        this.currentPos = start;
        this.aim = aim;
        this.td = td;
        fields = PathFromCurrentToAim(start, aim, td);
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

    private static boolean inList(Waypoint potNext, ArrayList<Waypoint> revWay) {
        for(Waypoint w : revWay){
            if(w == null)
                continue;
            if (potNext.equals(w))
                return true;
        }
        return false;
    }

    public static Waypoint getWPbyPos(Position pos, ArrayList<Waypoint> list){
        for(Waypoint w : list){
            if(pos.equals(w))   return  w;
        }
        return null;
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

    public static void turnListArround(ArrayList<Waypoint> list){
        for(int i = 0; i < list.size() / 2; i++){
            Waypoint l = list.get(i);
            Waypoint r = list.get(list.size() - (i + 1));
            list.set(i, r);
            list.set(list.size() - (i + 1), l);
        }
    }

    public static void printLists(ArrayList<Waypoint> list){
        for(Waypoint w : list){
            if(w == null)   continue;
            w.print();
            System.out.println("FCost: " + w.getFCost());

            System.out.print("Source: ");
            if(w.getSource() != null)
                w.getSource().print();
            else
                System.out.println("null");
        }
        System.out.println();
    }

    /*
    private static ArrayList<Position> optimizedSurroundersChosed(Waypoint w, TowerDefense td){
        ArrayList<Position> optimizedSurrounders = new ArrayList<>();

        for(int y = w.getY() - 1; y <= w.getY() + 1; y++){
            for(int x = w.getX() - 1; x <= w.getX() + 1; x++) {
                if(!(x == w.getY() && y == w.getY())){

                }
            }
        }

        return optimizedSurrounders;
    }
    */


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


            for(int y = w.getY() - 1; y <= w.getY() + 1; y++){
                for(int x = w.getX() - 1; x <= w.getX() + 1; x++){
                    Position pos = new Position(x, y);
                    if(!td.inBounds(x, y) || w.equals(pos) || td.isTower(x, y))    continue;
                    Waypoint wp = new Waypoint(pos, aim, w.getDistanceTraveled() + Position.getDistance(pos, w), w);
                    wp.changeSourceOfSurroundings(openList, td);

                    openList.add(wp);
                }
            }

            closedList.add(w);  openList.remove(w);
        }
    }

    private static void constructWay(ArrayList<Waypoint> way, ArrayList<Waypoint> closedList) {
        for(Waypoint wp = closedList.get(closedList.size() - 1); !wp.equals(closedList.get(0)); wp = wp.getSource()){
            way.add(wp);
        }
        Path.turnListArround(way);
    }

    public static ArrayList<Waypoint> PathFromCurrentToAim(Position start, Position aim, TowerDefense td){
        ArrayList<Waypoint> way = new ArrayList<>();
        ArrayList<Waypoint> openList = new ArrayList<>();
        ArrayList<Waypoint> closedList = new ArrayList<>();
        Waypoint startWP = new Waypoint(start, aim, 0, null);
        openList.add(startWP);

        fillLists(openList, closedList, aim, td);

        Path.constructWay(way, closedList);

        return way;
    }

    public static void setColors(ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded, ArrayList<Waypoint> way, TowerDefense td){
        for(Waypoint w : edgePoints){   td.getFieldAt(w).setColor(Color.GREEN);  }
        for(Waypoint w : pointsToBeSurrounded){
            Field f = td.getFieldAt(w);
            if(f != null)   f.setColor(Color.BLUE);
        }
        for(Waypoint w : way){
            Field f = td.getFieldAt(w);
            if(f != null)   f.setColor(Color.GREY);
        }
    }

    public boolean wayFound(){
        return fields != null;
    }

    public Position getAim() {
        return aim;
    }

    public Position getFirst() {
        return fields.get(0);
    }

    public void deleteFirst(){
        fields.remove(0);
    }

    public double pathLength(){
        if(fields.size() == 0)
            return -1;
        double l = 0;
        for(int i = 0; i <= fields.size() - 2; i++){
            Waypoint w1 = fields.get(i);
            Waypoint w2 = fields.get(i + 1);
            l += Position.getDistance(w2, w1);
        }
        return l;
    }
}
