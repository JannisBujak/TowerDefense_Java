package Objects;
import GUI.TowerDefense;
import Pathfinding.Position;
import Tower.Base.Tower;


public class Field extends Position{

    private Tower tower;
    private TDField tdField;

    public Field(int x, int y, Tower tower, TDField tdField, TowerDefense td){
        super(x, y);
        this.tdField = tdField;
        this.tower = tower;
    }

    public boolean isTower(){
        return tower != null;
    }

    public void update() {
        if(tower != null)   tower.update();
    }

    public void setTower(Tower tower) {
        this.tower = tower;
        tdField.setColor(tower.getColor());
    }
}
