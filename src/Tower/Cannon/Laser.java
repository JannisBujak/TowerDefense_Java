package Tower.Cannon;

import GUI.TowerDefense;
import Attackers.Attacker;
import Pathfinding.Position;
import Tower.Base.Shot;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Laser extends Shot {

    private static double width = TowerDefense.X_UNIT/4;
    private static Color color = Color.RED;

    public Laser(Position position, Attacker attacker, Tower tower) {
        super(position, attacker, width, tower);
        super.setFill(color);

        update(position);
    }
}
