package Leveldesign;

import Attackers.Attacker1.Attacker1;
import GUI.TowerDefense;
import Attackers.Attacker;
import Pathfinding.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelReader {


    private long nxtActionTime;

    ArrayList<ArrayList<AttackerInformation>> allLevels = new ArrayList<>();

    private static Position spawn = new Position(4, 0);
    private static Position globalAim = new Position(4, 16);



    private static String levelName = "LevelDesign/lv_vers1.txt";

    public LevelReader(TowerDefense towerDefense)  throws IOException {
        nxtActionTime = System.currentTimeMillis();
        readLevels(towerDefense);
    }

    private static AttackerInformation evaluateWord(String word, TowerDefense towerDefense) throws IOException{
        Attacker a = null;
        ArrayList<String> first = new ArrayList<>();
        String part = "";
        for(int i = 0; i < word.length(); i++){
            if(!(word.charAt(i) == 'A' || word.charAt(i) == 'W' || word.charAt(i) == ';')){
                part += word.charAt(i);
            }else{
                first.add(part);
                part = "";
            }
        }
        if(first.size() != 3) System.exit(3);

        int[] values = new int[3];
        for(int i = 0; i < values.length; i++){
            String s = first.get(i);
            values[i] = Integer.parseInt(s);
        }

        AttackerInformation information = null;

        a = new Attacker1(towerDefense, spawn, globalAim);

        information = new AttackerInformation(a, values[2], values[0]);
        return information;
    }



    public void readLevels(TowerDefense towerDefense) throws IOException {

        FileReader fileReader = new FileReader(levelName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> level = new ArrayList<>();

        String line = "";
        while(true){
            line = bufferedReader.readLine();
            if(line == null)    break;
            level.add(line);
        }
        bufferedReader.close();

        for(String s : level){
            ArrayList<AttackerInformation> oneLevel = new ArrayList<>();
            String[] words = s.split(" ");
            for(String word : words){
                oneLevel.add(evaluateWord(word, towerDefense));
                System.out.println();
            }
            allLevels.add(oneLevel);
        }
    }


    public Attacker getNext(){
        return null;
    }
}
