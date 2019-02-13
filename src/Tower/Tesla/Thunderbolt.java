package Tower.Tesla;

import GUI.TowerDefense;
import GeneralOperations.ListOperations;
import Objects.Attacker;
import Objects.Field;
import Pathfinding.Position;
import Tower.Base.Shot;
import com.sun.javafx.geom.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Thunderbolt extends Ellipse implements Shot {

    private Attacker aim;

    public Thunderbolt(Position position, Attacker attacker) {
        super();
        this.aim = attacker;
        update(position);
    }

    public void update(Position position){
        if(position == null) System.out.println("Reee");
        if(aim == null) System.out.println("Meee");
        setCenterX((position.getX() + 0.5) * TowerDefense.X_UNIT + aim.getxDistance(position)/2 * TowerDefense.X_UNIT);
        setCenterY((position.getY() + 0.5) * TowerDefense.Y_UNIT + aim.getyDistance(position)/2 * TowerDefense.Y_UNIT);
        setRadiusX(- aim.getxDistance(position) * TowerDefense.X_UNIT / 2);
        setRadiusY(- aim.getyDistance(position) * TowerDefense.Y_UNIT / 2);
        setRotate(aim.getAngle(position));

    }

    public Attacker getAim(){
        return aim;
    }
}
