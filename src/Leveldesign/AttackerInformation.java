package Leveldesign;

import Attackers.Attacker;

public class AttackerInformation {


    private Attacker attacker;
    private int cooldown;
    private int number;

    public AttackerInformation(Attacker attacker, int cooldown, int number){
        this.attacker = attacker;
        this.cooldown = cooldown;
    }
}
