package Leveldesign;

import Objects.Attacker;

public class AttackerInformation {


    private Attacker attacker;
    private int cooldown;

    public AttackerInformation(Attacker attacker, int cooldown){
        this.attacker = attacker;
        this.cooldown = cooldown;
    }
}
