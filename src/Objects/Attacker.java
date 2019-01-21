package Objects;

import Objects.*;

import Pathfinding.*;
import GUI.TowerDefense;
import javafx.scene.shape.Ellipse;

public class Attacker extends Ellipse {

    private Path path;

    public Attacker(TowerDefense td, Position start, Position destination){

        path = new Path(start, destination, td);

        path.update();

        System.out.println("Way up to date");
        if(this.path != null){
            this.path.print();
        }else{
            System.out.println("null");
        }
    }
}
