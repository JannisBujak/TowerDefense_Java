package alternateStart;
import alternateStart.Pathfinding.*;

import java.util.ArrayList;

public class Attacker {
    private Way way;

    Attacker(TowerDefense td, Position start, Position destination){
        this.way = Way.getShortestWay(td, start, destination, new ArrayList<>());
        System.out.println("Came out");
        if(this.way != null){
            this.way.print();
        }else{
            System.out.println("null");
        }
    }
}
