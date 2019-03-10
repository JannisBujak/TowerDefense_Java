package Tower.Tesla;

import GUI.TowerDefense;
import Attackers.Attacker;
import Pathfinding.Position;
import Tower.Base.Shot;
import Tower.Base.Tower;
import javafx.scene.paint.Color;

public class Thunderbolt extends Shot {

    private static double width = TowerDefense.X_UNIT/4;
    private static Color color = Color.GREEN;


    public Thunderbolt(Position position, Attacker attacker, Tower tower) {
        super(position, attacker, width, tower);
        super.setFill(color);

        update(position);
    }

}
