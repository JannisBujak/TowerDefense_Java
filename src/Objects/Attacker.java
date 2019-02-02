package Objects;

import Pathfinding.*;
import GUI.TowerDefense;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    private Position pos;
    private Path path;
    private boolean reachedEnd;
    private static double speed = 0.1;

    public Attacker(TowerDefense td, Position start, Position destination){
        super(TowerDefense.X_UNIT / 2, TowerDefense.Y_UNIT / 2);
        setCenterX((start.getX() + 0.5) * TowerDefense.X_UNIT);
        setCenterY((start.getY() + 0.5) * TowerDefense.Y_UNIT);

        pos = start;

        path = new Path(start, destination, td);

        if(path != null)    System.out.println("Way up to date");
        else                System.out.println("path == null");
        //this.path.print();
        if(path.wayFound()){
            System.out.println("Ye");
        }
    }

    public boolean reachedEnd() {
        //return this.path.getAim().equals(new Position((int)getCenterX(), (int)getCenterY()));
        return reachedEnd;
    }

    public void update() {
        path.print();
        Position next = path.getFirst();
        double xMove = (next.getX() - pos.getX());
        double yMove = (next.getY() - pos.getY());
        Vector v = new Vector(xMove, yMove);

        if (Math.abs(v.getX() * TowerDefense.X_UNIT * speed) >= Math.abs(((next.getX() + 0.5) * TowerDefense.X_UNIT) - getCenterX())
        && Math.abs(v.getY() * TowerDefense.Y_UNIT * speed) >= Math.abs(((next.getY() + 0.5) * TowerDefense.Y_UNIT) - getCenterY()) )
        {
            pos.setX(next.getX());
            pos.setY(next.getY());
            path.deleteFirst();
            if(path.empty()){
                this.reachedEnd = true;
            }
            updateTranslateVar();
        } else {
            setCenterX(getCenterX() + v.getX() * TowerDefense.X_UNIT * speed);
            setCenterY(getCenterY() + v.getY() * TowerDefense.Y_UNIT * speed);
        }
        //pos.print();
    }

    public void updateTranslateVar(){
        this.setCenterX((pos.getX() + 0.5) * TowerDefense.X_UNIT);
        this.setCenterY((pos.getY() + 0.5) * TowerDefense.Y_UNIT);
        //System.out.println(getCenterX() + " " + getCenterY());
    }
}
