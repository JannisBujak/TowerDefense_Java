package Objects;
import Pathfinding.Position;


public class Field extends Position{

    private boolean towerHere;
    //private Tower t = null;

    public Field(int x, int y){
        super(x, y);
        towerHere = false;
        //...
    }

    public boolean isTower(){
        return towerHere;
    }
}
