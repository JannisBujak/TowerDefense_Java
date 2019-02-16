package Tower.Cannon;

import GUI.TowerDefense;
import Objects.Attacker;
import Pathfinding.Position;
import Tower.Base.Shot;
import Tower.Base.Tower;
import Tower.Tesla.Tesla;
import javafx.scene.paint.Color;

public class Laser extends Shot {

    private static double width = TowerDefense.X_UNIT;

    public Laser(Position position, Attacker attacker, Tower tower) {
        super(position, attacker, width, tower);
        super.setFill(Color.RED);

        update(position);
    }
}
