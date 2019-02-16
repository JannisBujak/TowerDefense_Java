package Tower.Tesla;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Pathfinding.Position;
import Tower.Base.Shot;
import Tower.Base.Tower;
import com.sun.javafx.geom.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import sun.util.resources.cldr.et.TimeZoneNames_et;

import java.sql.Time;
import java.sql.Timestamp;

public class Thunderbolt extends Shot {

    private static double width = TowerDefense.X_UNIT/4;

    public Thunderbolt(Position position, Attacker attacker, Tower tower) {
        super(position, attacker, width, tower);
        super.setFill(Color.BLUE);

        update(position);
    }

}
