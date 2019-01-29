package Objects;

import Objects.*;

import Pathfinding.*;
import GUI.TowerDefense;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    private Position pos;
    private Path path;

    public Attacker(TowerDefense td, Position start, Position destination){
        super(TowerDefense.X_UNIT / 2, TowerDefense.Y_UNIT / 2);
        setCenterX(getTranslateX() + TowerDefense.X_UNIT / 2);
        setTranslateY(getTranslateY() + TowerDefense.Y_UNIT / 2);

        pos = start;

        path = new Path(start, destination, td);

        path.update();

        System.out.println("Way up to date");
        if(this.path != null){
            this.path.print();
        }else{
            System.out.println("null");
        }
    }

    public boolean reachedEnd() {
        return this.path.getAim().equals(new Position((int)getCenterX(), (int)getCenterY()));
    }

    public void update() {
        Position next = path.getLast();

    }
}
