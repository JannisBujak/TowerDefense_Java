package GeneralOperations;

import Objects.Attacker;
import Pathfinding.Position;
import Pathfinding.Waypoint;
import Tower.Tesla.Thunderbolt;

import java.util.ArrayList;

public class ListOperations {


    public static void printList(ArrayList<Waypoint> list){
        for(Waypoint w : list){
            if(w == null)   continue;
            w.print();
            System.out.println("FCost: " + w.getFCost());

            System.out.print("Source: ");
            if(w.getSource() != null)
                w.getSource().print();
            else
                System.out.println("null");
        }
        System.out.println();
    }

    public static void turnListArround(ArrayList<Waypoint> list){
        for(int i = 0; i < list.size() / 2; i++){
            Waypoint l = list.get(i);
            Waypoint r = list.get(list.size() - (i + 1));
            list.set(i, r);
            list.set(list.size() - (i + 1), l);
        }
    }

    public static Waypoint getWPbyPos(Position pos, ArrayList<Waypoint> list){
        for(Waypoint w : list){
            if(pos.equals(w))   return  w;
        }
        return null;
    }

    public static boolean inList(Position potNext, ArrayList<Waypoint> list) {
        for(Waypoint w : list){
            if(w == null)   continue;
            if (potNext.equals(w))
                return true;
        }
        return false;
    }

    public static boolean inList(Attacker attacker, ArrayList<Thunderbolt> list) {
        for(Thunderbolt t : list){
            if(t == null)   continue;
            if (t.getAim() == attacker)
                return true;
        }
        return false;
    }

    public static int getIndex(Attacker attacker, ArrayList<Thunderbolt> list){
        for(int i = 0; i < list.size(); i++){
            Thunderbolt t = list.get(i);
            if(t == null)   continue;
            if(attacker == t.getAim()){
                return i;
            }
        }
        return  -1;
    }
}
