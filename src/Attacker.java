import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    Way way;
    private boolean reachedFinisch;

    Attacker(double x, double y, double radiusX, double radiusY, Color color, Way way){
        super(x, y, radiusX, radiusY);
        this.way = way;
        setFill(color.darker());
    }

    public void print(){
        System.out.println("        (" + getTranslateX() + "|" + getTranslateY() + ")");
    }

    public void update(){
        if(way != null){
            way.print();
        }else {
            return;
        }
        print();
        double xDir = way.getX() - getTranslateX();
        double yDir = way.getY() - getTranslateY();
        double lengthSqr = xDir * xDir + yDir * yDir;
        double length = Math.sqrt(lengthSqr);
        double additionalX = (xDir / length) * Math.sqrt(TowerDefense.MOVEMENT_SPEED);
        double additionalY = (yDir / length) * Math.sqrt(TowerDefense.MOVEMENT_SPEED);

        if (way == null){
            System.out.println("Hallal");
            reachedFinisch = true;
            return;
        } else {
            if(Math.abs((way.getX() - getTranslateX())) > additionalX || Math.abs((way.getY() - getTranslateY())) > additionalY){
                setTranslateX(getTranslateX() + additionalX);
                setTranslateY(getTranslateY() + additionalY);
            }else {
                System.out.println("Yes");
                setTranslateX(way.getX());
                setTranslateY(way.getY());
                Way w = way.getNextWay();
                way = w;
            }
        }
    }

    public boolean reachedEnd() {
        return reachedFinisch;
    }
}
