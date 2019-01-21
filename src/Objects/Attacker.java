import Pathfinding.*;

public class Attacker {

    private Path path;

    Attacker(TowerDefense td, Position start, Position destination){

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
