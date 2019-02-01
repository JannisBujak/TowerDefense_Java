package Objects;

import Pathfinding.*;
import GUI.TowerDefense;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    private Position pos;
    private Path path;
    private boolean reachedEnd;

    public Attacker(TowerDefense td, Position start, Position destination){
        super(TowerDefense.X_UNIT / 2, TowerDefense.Y_UNIT / 2);
        setTranslateX(getTranslateX() + TowerDefense.X_UNIT / 2);
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
        //return this.path.getAim().equals(new Position((int)getCenterX(), (int)getCenterY()));
        return reachedEnd;
    }

    public void update() {
        Position next = path.getFirst();
        next.print();
        double xMove = (next.getX() - pos.getX());
        double yMove = (next.getY() - pos.getY());
        Vector v = new Vector(xMove, yMove);

        if (pos.getX() + xMove >= next.getX() && pos.getY() + yMove >= next.getY()) {
            pos.setX(next.getX());
            pos.setY(next.getY());
            path.deleteFirst();
            if(path.empty()){
                this.reachedEnd = true;
            }
        } else {
            pos.moveInDirection(v);
        }
        updateTranslateVar();
    }

    public void updateTranslateVar(){
        this.setTranslateX((pos.getX() + 1) + TowerDefense.X_UNIT / 2);
        this.setTranslateX((pos.getY() + 1) + TowerDefense.Y_UNIT / 2);
    }
}
