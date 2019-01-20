package alternateStart.Pathfinding;
import alternateStart.Field;
import alternateStart.TowerDefense;

import java.util.ArrayList;

public class Path {
    Position currentPos;
    Position aim;
    TowerDefense board;

    ArrayList <Waypoint> fields;

    public Path(Position start, Position aim, TowerDefense board){
        this.aim = aim;
        this.currentPos = start;
        this.board = board;
    }

    public void print(){
        System.out.println("\n");
        for(Waypoint w : fields){
            w.print();
        }
    }

    public void update(){
        ArrayList<Waypoint> edgePoints = new ArrayList<>();
        ArrayList<Waypoint> pointsToBeSurrouinded = new ArrayList<>();;

        Waypoint start = new Waypoint(currentPos, null, aim);
        edgePoints.add(start);

        Position aimFound = null;
        Waypoint aimW = null;

        while(true){

            double smallestFCost = -1;
            for(Waypoint w : edgePoints){
                if(w.getfCost() < smallestFCost || smallestFCost == -1)
                    smallestFCost = w.getfCost();
            }
            for(int i = 0; i < edgePoints.size(); i++){
                Waypoint w = edgePoints.get(i);
                if(w.getfCost() == smallestFCost){
                    pointsToBeSurrouinded.add(w);
                    edgePoints.remove(w);
                }
            }

            for(Waypoint w : pointsToBeSurrouinded){
                for(int y = w.getY() - 1; y <= w.getY() + 1; y++){
                    for(int x = w.getX() - 1; x <= w.getX() + 1; x++){

                        Position p = new Position(x, y);
                        if(aim.equals(p)){
                            System.out.println("Found");
                            aimFound = p;
                            aimW = new Waypoint(aimFound, currentPos, null);
                            pointsToBeSurrouinded.add(aimW);
                            w.print();
                            break;
                        }

                        if(x == w.getX() && y == w.getY())  continue;
                        if(!board.inBounds(x, y))   continue;
                        if(board.getFieldAt(x, y) != null && !board.getFieldAt(x, y).isTower()){
                            boolean inside = false;
                            for(Waypoint wp : edgePoints){  if(p.equals(wp)){   inside = true;  }}
                            for(Waypoint wp : pointsToBeSurrouinded){   if(p.equals(wp)){ inside = true;    }}

                            if(!inside) edgePoints.add(new Waypoint(p, currentPos, aim));
                        }

                    }
                    if(aimFound != null)	break;
                }
                if(aimFound != null)	break;
            }
            if(aimFound != null)	break;
        }
        if(aimFound != null) {    aimFound.print();
        }else return;

        ArrayList<Waypoint> way = new ArrayList<>();
        way.add(aimW);
        while(true){
            Waypoint latest = way.get(way.size() - 1);
            if(latest.neighbour(start)){
                way.add(start);
                fields = way;
                break;
            }

            Waypoint nextPos = null;
            for(Waypoint potentialNext : pointsToBeSurrouinded){
                boolean inside = false;
                for(Waypoint w : way){
                    if(w.equals(potentialNext))     inside = true;
                }
                if(inside)      continue;

                if(latest.neighbour(potentialNext)){
                    if(nextPos == null || nextPos.getfCost() >= potentialNext.getfCost()){
                        nextPos = potentialNext;

                    }
                }
            }
            if(nextPos != null){
                way.add(nextPos);
                pointsToBeSurrouinded.remove(nextPos);

            }else{
                break;
            }
        }
    }
}
