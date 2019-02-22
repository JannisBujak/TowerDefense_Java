package Attackers.Attacker1;

import Attackers.Attacker;
import GUI.TowerDefense;
import Pathfinding.Position;

public class Attacker1 extends Attacker {

    private static double SPEED = 0.01;
    private static int HEALTH_POINTS = 3000;

    public Attacker1(TowerDefense td, Position start, Position destination){
        super(td, start, destination, SPEED, HEALTH_POINTS);
    }
}
