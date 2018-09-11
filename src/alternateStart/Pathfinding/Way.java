package alternateStart.Pathfinding;
import alternateStart.*;

import alternateStart.Field;
import alternateStart.TowerDefense;
import javafx.geometry.Pos;

public class Way {
    private Field field;

    public Way(Field field){
        this.field = field;
    }

    public static Way getShortestWay(Position start, Position finisch) {
        Field fieldAtP = TowerDefense.getFieldAt(start.getX(), start.getY());
        if(fieldAtP.isTower()){
            return null;
        }
        if(fieldAtP.equals(finisch)){
            return new Way(fieldAtP);
        }

        return null;
    }
    /*
    vector<Field*> forbiddenFieldsCopy = Way::copyFieldVector(forbiddenFields);
	forbiddenFieldsCopy.push_back(l->getFieldAt(p));


	vector<Position*> testedPositions = Labyrinth::getOptimumFollowingPoints(p, l->getUniqueField('E'));

	vector<Way*> possibleWays;

	for(Position* pos : testedPositions){

		int sizeOfTheActualLine = l->getFieldAt(pos->getY()).size();

		if(pos->getX() < 0 || pos->getX() >= sizeOfTheActualLine || pos->getY() < 0 || pos->getY() >= l->getHeight()){
			delete(pos);
			continue;
		}
		if(Way::isInFieldVector(l->getFieldAt(pos), forbiddenFields)){
			delete(pos);
			continue;
		}

		Way* potentialWay = findShortestWay(pos, l, forbiddenFieldsCopy);
		if(potentialWay == nullptr){
			delete(pos);
			continue;
		}
		possibleWays.push_back(potentialWay);

		delete(pos);
	}

	int length = - 1;
	Way* returnWay = nullptr;
	for(Way* way : possibleWays){
		int lengthFromHere = way->getLengthFromHere();
		if(lengthFromHere < length || length == -1){
			length = lengthFromHere;
			delete(returnWay);
			returnWay = way;
		}else{
			delete(way);
		}
	}

	if(length == -1 || returnWay == nullptr){
		return nullptr;
	}
	Way* thisWay = new Way(fieldAtP);
	thisWay->appendWay(returnWay);
	return thisWay;
     */
}