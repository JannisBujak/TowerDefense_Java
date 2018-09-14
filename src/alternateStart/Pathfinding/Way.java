package alternateStart.Pathfinding;
import alternateStart.*;

import alternateStart.Field;
import alternateStart.TowerDefense;
import java.util.*;

import static alternateStart.TowerDefense.getOptimumFollowingPoints;

public class Way {
    private Field field;
    Way next;

    public Way(Field field){

        this.field = field;
    }

    private void appendWay(Way appendedWay) {
        if(next == null){
            next = appendedWay;
            return;
        }
        next.appendWay(appendedWay);
    }

    private int getLengthFromHere() {
        if(next == null){
            return 1;
        }
        return 1 + next.getLengthFromHere();
    }

    public void print(){
        if(this == null){
            return;
        }
        field.print();
        next.print();
    }


    private static List<Field> copyFieldList(List<Field> fieldListOriginal){
        return new ArrayList<Field>(fieldListOriginal);
    }

    private static boolean alreadySteppedOn(Position p, List<Field> forbiddenFields) {
        for(Field f : forbiddenFields){
            if(p.equals(f)){
                p.print();
                f.print();
                return true;
            }
        }
        return false;
    }

    public static Way getShortestWay(TowerDefense td, Position start, Position destination, List<Field> forbiddenFields) {
        Field fieldAtP = td.getFieldAt(start);
        if(fieldAtP.isTower()){
            return null;
        }
        /*
        start.print();
        destination.print();
        System.out.println();
        */
        if(start.equals(destination)){
            return new Way(fieldAtP);
        }

        List<Field> forbiddenFieldsCopy = copyFieldList(forbiddenFields);
        forbiddenFieldsCopy.add(td.getFieldAt(start.getX(), start.getY()));

        List<Position> testedPositions = getOptimumFollowingPoints(start, destination);

        List<Way> possibleWays = new ArrayList<>();
        for(Position p : testedPositions){
            int sizeOfTheActualLine = td.getLine(p.getY()).size();
            if(p.getX() < 0 || p.getX() >= sizeOfTheActualLine || p.getY() < 0 || p.getY() >= td.getFieldHeight()){
                continue;
            }
            if(alreadySteppedOn(p, forbiddenFields)){
                System.out.println("Yep");
                continue;
            }
            Way potentialWay = getShortestWay(td, p, destination, forbiddenFieldsCopy);
            if(potentialWay == null){
                continue;
            }
            possibleWays.add(potentialWay);
        }


        int length = - 1;
        Way returnWay = null;

        for(Way way : possibleWays){

            int lengthFromHere = way.getLengthFromHere();
            if(lengthFromHere < length || length == -1){
                length = lengthFromHere;
                returnWay = way;
            }
        }

        if(length == - 1){
            return null;
        }
        Way thisWay = new Way(fieldAtP);
        thisWay.appendWay(returnWay);
        return  thisWay;
    }
}