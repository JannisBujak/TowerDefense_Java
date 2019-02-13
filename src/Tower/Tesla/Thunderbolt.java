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

public class Thunderbolt extends Ellipse implements Shot {

    private Attacker aim;
    private double  timestamp;
    private Tower tower;

    public Thunderbolt(Position position, Attacker attacker, Tower tower) {
        super();
        this.tower = tower;
        this.aim = attacker;
        update(position);
    }

    public void update(Position position){
        setCenterX((position.getX() + 0.5) * TowerDefense.X_UNIT + aim.getxDistance(position)/2 * TowerDefense.X_UNIT);
        setCenterY((position.getY() + 0.5) * TowerDefense.Y_UNIT + aim.getyDistance(position)/2 * TowerDefense.Y_UNIT);
        setRadiusX(TowerDefense.X_UNIT / 4);
        setRadiusY(Math.abs(aim.getyDistance(position) * TowerDefense.Y_UNIT / 2));
        setRotate(aim.getAngle(position));

        if(timestamp < System.currentTimeMillis()){
            timestamp = System.currentTimeMillis() + Tesla.getCooldown();
            aim.damage(tower.getDamageValue());
            //System.out.println(aim.getHealtPoints());
        }
    }

    public Attacker getAim(){
        return aim;
    }
}
