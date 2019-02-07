package Pathfinding;


import GUI.*;
import Objects.Field;
import Tower.Base.Tower;
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


    public static void printLists(ArrayList<Waypoint> list){
        for(Waypoint w : list){
            if(w == null)   continue;
            w.print();
            System.out.print("Source: ");
            if(w.getSource() != null)
                w.getSource().print();
            else
                System.out.println("null");
        }
        System.out.println();
    }


    private static void minFCost_Edg(ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded, Position aim, TowerDefense td) {
        double smallestFCost = -1;
        for(Waypoint w : edgePoints){
            if(w.getFCost() < smallestFCost || smallestFCost == -1) {
                smallestFCost = w.getFCost();
            }
        }

        if(smallestFCost == -1) java.lang.System.exit(1);
        //System.out.println(smallestFCost);
        for(int i = 0; i < edgePoints.size(); i++){
            Waypoint w = edgePoints.get(i);
            if(w.getFCost() == smallestFCost){

                for(int y = w.getY() - 1; y <= w.getY() + 1; y++){
                    for(int x = w.getX() - 1; x <= w.getX() + 1; x++){
                        if(td.inBounds(x, y) && !new Position(x, y).equals(w)){
                            //TODO: check, if shortest way
                            Waypoint knownPoint = null;
                            for(Waypoint p : pointsToBeSurrounded){
                                if(p.equals(new Position(x, y))){
                                    knownPoint = p;
                                    break;
                                }
                            }
                            if(knownPoint == null)  continue;
                            if(w.getDistanceTraveled() > knownPoint.getDistanceTraveled()){
                                w.setSource(knownPoint);
                            }
                        }
                    }
                }
                pointsToBeSurrounded.add(w);
                edgePoints.remove(w);
            }
        }
    }


    private static Waypoint addToEdgePoints(Waypoint w, Position p, ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded, Position currentPos, Position aim, TowerDefense td){

        if(p.getX() == w.getX() && p.getY() == w.getY()   ||  !td.inBounds(p.getX(), p.getY())  ||  td.getFieldAt(p).isTower())
        {
            return null;
        }
        if(aim.equals(p))
        {
            System.out.println("Aim found");
            Waypoint aimW = new Waypoint(p, currentPos, null, w.getDistanceTraveled() + Position.getDistance(p, w), w);
            pointsToBeSurrounded.add(aimW);
            td.getFieldAt(aimW).setColor(Color.GREEN);
            //printLists(edgePoints, pointsToBeSurrounded);
            return aimW;
        }

        if(td.getFieldAt(p) != null)
        {
            boolean inside = false;
            for(Waypoint wp : edgePoints){  if(p.equals(wp)){   inside = true;  }}
            for(Waypoint wp : pointsToBeSurrounded){   if(p.equals(wp)){ inside = true;    }}

            if(!inside) {
                Waypoint nextWP = new Waypoint(p, currentPos, aim, w.getDistanceTraveled() + Position.getDistance(p, w), w);
                edgePoints.add(nextWP);
                return null;
            }
        }
        return null;
    }

    private static ArrayList<Position> getOptimalPoints(Waypoint w, TowerDefense td){
        ArrayList<Position> positions = new ArrayList<>();
        for(int x = w.getX() - 1; x <= w.getX() + 1; x++){
            for(int y = w.getY() - 1; y <= w.getY() + 1; y++) {
                if((x == w.getX()) ^ (y == w.getY()))
                    positions.add(new Position(x, y));
            }
        }
        for (int i = 0; i < 4; i++){
            Field p1 = td.getFieldAt(positions.get(i));
            Field p2 = td.getFieldAt(positions.get((i  + 1) % 4));
            if((p1 == null || !p1.isTower()) && (p2 == null || !p2.isTower())){
                Position corner = Position.getCorner(positions.get(i), positions.get((i + 1) % 4), w);
                if(corner != null)   positions.add(corner);
            }
        }
        return positions;
    }

    public static void turnListArround(ArrayList<Waypoint> list){
        for(int i = 0; i < list.size() / 2; i++){
            Waypoint l = list.get(i);
            Waypoint r = list.get(list.size() - (i + 1));
            list.set(i, r);
            list.set(list.size() - (i + 1), l);
        }
    }

    private static ArrayList<Waypoint> constructWay(ArrayList<Waypoint> pointsToBeSurrounded){
        ArrayList<Waypoint> revWay = new ArrayList<>();
        for(Waypoint current = pointsToBeSurrounded.get(pointsToBeSurrounded.size() - 1); current.getSource() != null; current = current.getSource()){
            revWay.add(current);
        }
        Path.turnListArround(revWay);
        return revWay;
    }

    public static ArrayList<Waypoint> finish(ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded, TowerDefense td){
        ArrayList<Waypoint> way = Path.constructWay(pointsToBeSurrounded);
        setColors(edgePoints, pointsToBeSurrounded, way, td);
        return way;
    }

    public static ArrayList<Waypoint> PathFromCurrentToAim(Position currentPos, Position aim, TowerDefense td){
        ArrayList<Waypoint> edgePoints = new ArrayList<>();
        ArrayList<Waypoint> pointsToBeSurrounded = new ArrayList<>();

        Waypoint start = new Waypoint(currentPos, null, aim, 0, null);
        edgePoints.add(start);

        Waypoint aimW;


        while(true){

            System.out.println("edg");
            printLists(edgePoints);
            System.out.println("ptbS");
            printLists(pointsToBeSurrounded);

            minFCost_Edg(edgePoints, pointsToBeSurrounded, aim, td);


            for(Waypoint w : pointsToBeSurrounded){

                ArrayList<Position> positions = Path.getOptimalPoints(w, td);

               for (Position p : positions) {
                   aimW = Path.addToEdgePoints(w, p, edgePoints, pointsToBeSurrounded, currentPos, aim, td);
                   if (aimW != null) return finish(edgePoints, pointsToBeSurrounded, td);
               }
            }
            if(edgePoints.isEmpty() && pointsToBeSurrounded.isEmpty()) {
                System.exit(33);
            }
        }


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
