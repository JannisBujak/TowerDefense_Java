package Objects;
import GUI.TowerDefense;
import Pathfinding.Position;
import Tower.Base.Tower;
import javafx.scene.paint.Color;


public class Field extends Position{

    private  TowerDefense td;
    private Tower tower;
    private TDField tdField;
    private int towersNearby;

    public Field(int x, int y, TDField tdField, TowerDefense td){
        super(x, y);
        this.tdField = tdField;
        this.td = td;
        this.tower = null;
        towersNearby = 0;
    }

    public boolean isTower(){
        return tower != null;
    }

    public void update() {
        if(tower != null)   tower.update(this);
    }

    public void gotNeighbour(){
        towersNearby++;
    }

    public boolean isNeighbourOfTower(){
        return towersNearby != 0;
    }

    public void setTower(Tower tower) {
        this.tower = tower;

        for(int y = getY() - 1; y < getY() + 1; y++){
            for(int x = getX() - 1; x < getX() + 1; x++){
                if(td.inBounds(x, y)){
                    td.getFieldAt(x,y).gotNeighbour();
                }
            }
        }
        tdField.setColor(tower.getColor());
    }

    public void setColor(Color c){
        tdField.setColor(c);
    }
}
