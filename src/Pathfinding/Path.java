package Pathfinding;


import GUI.*;
import Objects.Field;

import java.util.ArrayList;

public class Path {
    Position currentPos;
    Position aim;
    TowerDefense board;

    ArrayList <Waypoint> fields;

    public Path(Position start, Position aim, TowerDefense board){
        this.currentPos = start;
        this.aim = aim;
        this.board = board;
        update();
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


    public static void printLists(ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded){
        System.out.println("P to be surr");
        for(Waypoint w : pointsToBeSurrounded){ w.print();  }
        System.out.println("Edge points");
        for(Waypoint w : edgePoints){   w.print();  }
        System.out.println();
    }


    private static void minFCost_Edg(ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded) {
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
                pointsToBeSurrounded.add(w);
                edgePoints.remove(w);
            }
        }
    }


    private Waypoint surroundW(Waypoint w, Position p, ArrayList<Waypoint> edgePoints, ArrayList<Waypoint> pointsToBeSurrounded){

        if(p.getX() == w.getX() && p.getY() == w.getY()   ||  !board.inBounds(p.getX(), p.getY())  ||  board.getFieldAt(p).isTower())
        {
            return null;
        }

        if(aim.equals(p))
        {
            System.out.println("Aim found");
            Waypoint aimW = new Waypoint(p, currentPos, null);
            pointsToBeSurrounded.add(aimW);
            //printLists(edgePoints, pointsToBeSurrounded);
            return aimW;
        }

        if(board.getFieldAt(p) != null)
        {
            boolean inside = false;
            for(Waypoint wp : edgePoints){  if(p.equals(wp)){   inside = true;  }}
            for(Waypoint wp : pointsToBeSurrounded){   if(p.equals(wp)){ inside = true;    }}

            if(!inside) {
                Waypoint nextWP = new Waypoint(p, currentPos, aim);
                edgePoints.add(nextWP);
                return null;
            }
        }
        return null;
    }


    public void update(){
        ArrayList<Waypoint> edgePoints = new ArrayList<>();
        ArrayList<Waypoint> pointsToBeSurrounded = new ArrayList<>();

        Waypoint start = new Waypoint(currentPos, null, aim);
        edgePoints.add(start);

        Waypoint aimW = null;


        while(true){

            /**printLists(edgePoints, pointsToBeSurrounded);
            */

            minFCost_Edg(edgePoints, pointsToBeSurrounded);

            for(Waypoint w : pointsToBeSurrounded){

                ArrayList<Position> positions = new ArrayList<>();

                for(int x = w.getX() - 1; x <= w.getX() + 1; x++){
                    for(int y = w.getY() - 1; y <= w.getY() + 1; y++) {
                        if((x == w.getX()) ^ (y == w.getY()))
                            positions.add(new Position(x, y));
                    }
                }
                for (int i = 0; i < 4; i++){
                    Field p1 = board.getFieldAt(positions.get(i));
                    Field p2 = board.getFieldAt(positions.get((i  + 1) % 4));
                    if((p1 == null || !p1.isTower()) && (p2 == null || !p2.isTower())){
                        Position corner = Position.getCorner(positions.get(i), positions.get((i + 1) % 4), w);
                        if(corner != null) positions.add(corner);
                    }
                }

                for (Position p : positions){
                    //p.print();
                    aimW = this.surroundW(w, p, edgePoints, pointsToBeSurrounded);
                    if (aimW != null)   break;
                }

                if(aimW != null)	break;
            }
            if(aimW != null)	break;
            if(edgePoints.isEmpty() && pointsToBeSurrounded.isEmpty()) {
                System.exit(33);
            }
        }


        for(Waypoint w : pointsToBeSurrounded){
            w.print();
        }
        System.out.println();

        ArrayList<Waypoint> way = new ArrayList<>();
        way.add(aimW);
        while(true) {
            Waypoint latest = way.get(way.size() - 1);
            System.out.println(" H");latest.print();
            if(latest.neighbour(start)){
                way.add(start);
                fields = new ArrayList<>();
                for(int i = way.size() - 2; i >= 0; i--){
                    fields.add(way.get(i));
                }
                break;
            }

            Waypoint nextPos = null;
            for(Waypoint potentialNext : pointsToBeSurrounded){
                if(latest.neighbour(potentialNext) && !Position.cuttingTower(latest, potentialNext, board)){
                    if(nextPos == null || nextPos.getFCost() > potentialNext.getFCost() || (nextPos.getFCost() == potentialNext.getFCost() && nextPos.gethCost() > potentialNext.gethCost())){
                        nextPos = potentialNext;

                    }
                }
            }

            if(nextPos != null){
                way.add(nextPos);
                pointsToBeSurrounded.remove(nextPos);
                nextPos.print();

            }else{
                way.remove(way.size() - 1);
            }
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
}
