package GUI;
import Pathfinding.Position;
import Tower.Cannon;
import Tower.Base.Tower;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.util.ArrayList;

import Objects.*;

public class TowerDefense extends Application {

    Pane root;
    ArrayList< ArrayList<Field>> allFields = new ArrayList<>();
    ArrayList<Attacker> allAttackers = new ArrayList<>();

    private int coins;


    static double SCENE_WIDTH = 1200;
    static double SCENE_HEIGHT = 800;
    static double NUMBER_OF_X_FIELDS = 24;
    static double NUMBER_OF_Y_FIELDS = 16;
    public static double MOVEMENT_SPEED = 2;
    public static  double X_UNIT = SCENE_WIDTH / NUMBER_OF_X_FIELDS;
    public static  double Y_UNIT = SCENE_HEIGHT / NUMBER_OF_Y_FIELDS;

    Position spawn = new Position(-1, -1);
    Position globalAim = new Position(3, 16);

    @Override
    public void start(Stage window) throws Exception {

        root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);


        initField(this, root, NUMBER_OF_X_FIELDS, NUMBER_OF_Y_FIELDS);

        getFieldAt(0, 2).setTower(new Cannon());
        getFieldAt(1, 2).setTower(new Cannon());
        getFieldAt(2, 2).setTower(new Cannon());
        getFieldAt(3, 2).setTower(new Cannon());


        Attacker a1 = new Attacker(this, spawn, globalAim);

        if(a1.pathEmpty())
            return;

        allAttackers.add(a1);
        root.getChildren().add(a1);



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Update();
            }
        };
        window.setScene(scene);
        root.setVisible(true);
        timer.start();
        window.show();

    }

    public static void initField(TowerDefense td, Pane root, double numberOfXFields, double numberOfYFields){

        double xSize = SCENE_WIDTH / numberOfXFields;
        double ySize = SCENE_HEIGHT / numberOfYFields;

        for(int y = 0; y < numberOfYFields + 1; y++) {
            ArrayList<Field> row = new ArrayList<>();
            for (int x = 0; x < numberOfXFields + 1; x++) {

                Color color;
                if((x + y) % 2 == 0){
                    color = Color.WHITE.brighter();
                }else{
                    color = Color.WHITE.darker();
                }
                TDField TDField  = new TDField(x * xSize, y * ySize, xSize, ySize, color);

                Field field = new Field(x, y, TDField, td);
                row.add(field);

                root.getChildren().add(TDField);
            }
            td.getAllFields().add(row);
        }
    }

    private ArrayList<ArrayList<Field>> getAllFields() {
        return allFields;
    }

    public Field getFieldAt(int x, int y) {
        if(y < 0 || y >= allFields.size() || x < 0 || x >= allFields.get(y).size())
            return null;
        else
            return allFields.get(y).get(x);
    }

    public Field getFieldAt(Position p) {
        if(p.getY() < 0 || p.getY() >= allFields.size() || p.getX() < 0 || p.getX() >= allFields.get(p.getY()).size())
            return null;
        else
            return allFields.get(p.getY()).get(p.getX());
    }

    public Position getSpawn() {
        return spawn;
    }

    public Position getGlobalAim() {
        return globalAim;
    }

    public boolean inBounds(int x, int y) {
        return ((y >= 0 && y < allFields.size()) && (x >= 0 && x < allFields.get(y).size()));
    }

    public boolean isTower(int x, int y){
        if(y < 0 || y >= allFields.size() || x < 0 || x >= allFields.get(y).size())
            return true;
        else
            return allFields.get(y).get(x).isTower();
    }

    public void printBoard(){
        for(ArrayList<Field> list : allFields){
            for(Field f : list){
                System.out.print("1 ");
            }

            System.out.println();
        }
    }

    public void addTower(int x, int y, TowerDefense td){
        Field field = td.getAllFields().get(y).get(x);
        if(!field.isTower())
            return;
        Tower t = new Cannon();
        if(Cannon.getPrice() > this.coins){
            field.setTower(t);
        }

    }

    public void Update(){
        for (int i = allAttackers.size() - 1; i >= 0; i--){
            Attacker a = allAttackers.get(i);
            if(!a.reachedEnd()){
                a.update();
            }else{
                allAttackers.remove(a);
                root.getChildren().remove(a);
            }
        }
        for (ArrayList<Field> row : allFields){
            for(Field f : row){
                f.update();
            }
        }

    }

    public static void main(String[] args){
        launch(args);
    }
}
