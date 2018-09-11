package alternateStart.Pathfinding;
import alternateStart.*;

import alternateStart.Field;
import alternateStart.TowerDefense;
import javafx.geometry.Pos;

public class Way {
    private Field field;

    public Way(Field field){
        this.field = field;
    }

    public static Way getShortestWay(Position start, Position finisch) {
        Field fieldAtP = TowerDefense.getFieldAt(start.getX(), start.getY());
        if(fieldAtP.isTower()){
            return null;
        }
        if(fieldAtP.equals(finisch)){
            return new Way(fieldAtP);
        }

        return null;
    }
}
