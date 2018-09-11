package alternateStart;

import alternateStart.Pathfinding.Position;

public class Field extends Position{

    private boolean towerHere;
    //private Tower t = null;

    Field(int x, int y){
        super(x, x);
        towerHere = false;
        //...
    }

    public boolean isTower(){
        return towerHere;
    }
}
