package Leveldesign;

import Objects.Attacker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class LevelReader {


    private long nxtActionTime;
    private
    ArrayList<ArrayList<Attacker>> allLevels = new ArrayList<>();


    private static String levelName;

    public LevelReader(){
        nxtActionTime = System.currentTimeMillis();
        readLevels();
    }

    public void readLevels()
    {
        FileReader fr = null;
        try {
            fr = new FileReader("LevelDesign/lv_vers1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Ree");
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);


        for(ArrayList<Attacker> level : allLevels){
            //TODO
        }
    }


    public Attacker getNext(){
        return null;
    }
}
