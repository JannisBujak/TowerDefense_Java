package alternateStart;
import alternateStart.Pathfinding.*;

public class Attacker {
    private Way way;

    Attacker(Position start, Position finisch){
        this.way = Way.getShortestWay(start, finisch);
    }
}
