package Pathfinding;


import GUI.*;

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

    public void update(){
        ArrayList<Waypoint> edgePoints = new ArrayList<>();
        ArrayList<Waypoint> pointsToBeSurrounded = new ArrayList<>();

        Waypoint start = new Waypoint(currentPos, null, aim);
        edgePoints.add(start);

        Position aimFound = null;
        Waypoint aimW = null;

        while(true){

            //System.out.println("P to be surr");
            for(Waypoint w : pointsToBeSurrounded){
                //w.print();
            }
            //System.out.println("Edge points");
            for(Waypoint w : edgePoints){
                //w.print();
            }

            double smallestFCost = -1;
            for(Waypoint w : edgePoints){
                if(w.getFCost() < smallestFCost || smallestFCost == -1) {
                    smallestFCost = w.getFCost();
                }
            }

            if(smallestFCost == -1) java.lang.System.exit(1);

            for(int i = 0; i < edgePoints.size(); i++){
                Waypoint w = edgePoints.get(i);
                if(w.getFCost() == smallestFCost){
                    pointsToBeSurrounded.add(w);
                    edgePoints.remove(w);
                }
            }


            for(Waypoint w : pointsToBeSurrounded){
                for(int y = ((int)w.getY()) - 1; y <= w.getY() + 1; y++){
                    for(int x = ((int)w.getX()) - 1; x <= w.getX() + 1; x++){
                        if(x == w.getX() && y == w.getY())  continue;

                        if(!board.inBounds(x, y))   {
                            //System.out.println("Mistake for (" + x + " | " + y + ")");
                            continue;
                        }
                        Position p = new Position(x, y);

                        if(aim.equals(p)){
                            //System.out.println("Found");
                            aimFound = p;
                            aimW = new Waypoint(aimFound, currentPos, null);
                            pointsToBeSurrounded.add(aimW);
                            //w.print();
                            break;
                        }

                        if(board.getFieldAt(x, y) != null && !board.getFieldAt(x, y).isTower()){
                            boolean inside = false;
                            for(Waypoint wp : edgePoints){  if(p.equals(wp)){   inside = true;  }}
                            for(Waypoint wp : pointsToBeSurrounded){   if(p.equals(wp)){ inside = true;    }}

                            if(!inside) {
                                edgePoints.add(new Waypoint(p, currentPos, aim));
                            }


                        }else{
                            //System.out.println("Null");
                        }

                    }
                    if(aimFound != null)	break;
                }
                if(aimFound != null)	break;
            }
            //if(pointsToBeSurrounded.isEmpty()) System.out.println("P to be surrounded");
            //if(edgePoints.isEmpty()) System.out.println("edge Points empty");
            if(aimFound != null)	break;
        }


        if(aimFound != null) {
            //aimFound.print();
        }else System.exit(2);

        ArrayList<Waypoint> way = new ArrayList<>();
        way.add(aimW);
        while(true){
            Waypoint latest = way.get(way.size() - 1);
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
                boolean inside = false;
                for(Waypoint w : way){
                    if(w.equals(potentialNext))     inside = true;
                }
                if(inside)      continue;

                if(latest.neighbour(potentialNext)){
                    if(nextPos == null || nextPos.getFCost() >= potentialNext.getFCost()){
                        nextPos = potentialNext;

                    }
                }
            }
            if(nextPos != null){
                way.add(nextPos);
                pointsToBeSurrounded.remove(nextPos);

            }else{
                break;
            }
        }
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
