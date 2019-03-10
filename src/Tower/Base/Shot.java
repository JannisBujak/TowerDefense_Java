package Tower.Base;

import GUI.TowerDefense;
import Attackers.Attacker;
import Pathfinding.Position;
import javafx.scene.shape.Ellipse;

public abstract class Shot extends Ellipse {

    private Attacker aim;
    private double  timestamp;
    private Tower tower;
    private double width;

    public Shot(Position position, Attacker attacker, double width, Tower tower) {
        super();
        this.tower = tower;
        this.aim = attacker;
        this.width = width;

        update(position);
    }

    public void update(Position position){
        setCenterX((position.getX() + 0.5) * TowerDefense.X_UNIT + aim.getxDistance(position)/2 * TowerDefense.X_UNIT);
        setCenterY((position.getY() + 0.5) * TowerDefense.Y_UNIT + aim.getyDistance(position)/2 * TowerDefense.Y_UNIT);
        setRadiusX(width);
        setRadiusY(Math.abs(aim.getDistance(position) * TowerDefense.Y_UNIT / 2));
        setRotate(aim.getAngle(position));

        if(timestamp < System.currentTimeMillis()){
            timestamp = System.currentTimeMillis() + tower.getCooldown();
            aim.damage(tower.getDamageValue());
            //System.out.println(aim.getHealtPoints());
        }
    }

    public Attacker getAim(){
        return aim;
    }

    public void resetAim(Attacker aim){
        this.aim = aim;
    }
}
