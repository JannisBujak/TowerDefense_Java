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
        super((position.getX() + 0.5) * TowerDefense.X_UNIT + attacker.getxDistance(position)/2 * TowerDefense.X_UNIT,
                (position.getY() + 0.5) * TowerDefense.Y_UNIT + attacker.getyDistance(position)/2 * TowerDefense.Y_UNIT,
                - attacker.getxDistance(position) * TowerDefense.X_UNIT / 2, - attacker.getyDistance(position) * TowerDefense.Y_UNIT / 2);

        setRotate(attacker.getAngle(position));

        System.out.println("Shee");
        super.setFill(Color.AQUA);
        this.aim = attacker;
    }

    public Attacker getAim(){
        return aim;
    }
}
