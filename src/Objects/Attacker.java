package Objects;

import Pathfinding.*;
import GUI.TowerDefense;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    private Position pos;
    private Path path;
    private boolean reachedEnd;
    private double speed;
    private int healthPoints;
    private TowerDefense td;

    public Attacker(TowerDefense td, Position start, Position destination, double speed, int healthPoints){
        super(TowerDefense.X_UNIT / 2, TowerDefense.Y_UNIT / 2);
        setFill(Color.GREEN);
        setCenterX((start.getX() + 0.5) * TowerDefense.X_UNIT);
        setCenterY((start.getY() + 0.5) * TowerDefense.Y_UNIT);

        this.speed = speed;
        this.healthPoints = healthPoints;
        this.td = td;
        pos = start;

        path = new Path(start, destination, td);

    }

    public boolean reachedEnd() {
        //return this.path.getAim().equals(new Position((int)getCenterX(), (int)getCenterY()));
        return reachedEnd;
    }

    public void update() {
        //path.print();
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

    public void setColor(Color color){
        super.setFill(color);
    }

    public double getxDistance(Position position){
        return getCenterX()/TowerDefense.X_UNIT - (position.getX() + 0.5);
    }

    public double getyDistance(Position position){
        return getCenterY()/TowerDefense.Y_UNIT - (position.getY() + 0.5);
    }

    public double getDistance(Position position){
        double xDist = getxDistance(position);
        double yDist = getyDistance(position);
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    }

    public double getAngle(Position position){
        double xDist = getxDistance(position);
        double yDist = getyDistance(position);
        double d = Math.acos((yDist) / (Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2))));
        if(xDist < 0)   return Math.abs(Math.toDegrees(d));
        else            return -Math.abs(Math.toDegrees(d));
    }

    public boolean pathEmpty(){
        return this.path == null;
    }

    public void damage(double damageValue) {
        this.healthPoints -= damageValue;
    }

    public int getHealtPoints() {
        return healthPoints;
    }
}
