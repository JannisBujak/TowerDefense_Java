package GUI;
import Attackers.Attacker;
import Attackers.Attacker1.Attacker1;
import Leveldesign.LevelReader;
import Pathfinding.Position;
import Tower.Base.Tower;
import Tower.Cannon.Cannon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
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

    Position spawn = new Position(4, 0);
    Position globalAim = new Position(4, 16);

    @Override
    public void start(Stage window) throws Exception {

        root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        this.coins = 100;


        initField(this, root, NUMBER_OF_X_FIELDS, NUMBER_OF_Y_FIELDS);


        LevelReader levelReader = new LevelReader(this);

        Position[] positions = {    new Position(5,5)  };

        if(addTower(5, 5, new Cannon(this))){
            System.out.println(coins);
        }

        Attacker a1 = new Attacker1(this, spawn, globalAim);
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

    public boolean inBounds(int x, int y) {
        return ((y >= 0 && y < allFields.size()) && (x >= 0 && x < allFields.get(y).size()));
    }

    public boolean isTower(int x, int y){
        if(y < 0 || y >= allFields.size() || x < 0 || x >= allFields.get(y).size())
            return true;
        else
            return allFields.get(y).get(x).isTower();
    }

    private boolean addTower(int x, int y, Tower t){
        Field field = allFields.get(y).get(x);
        if(field.isTower()) return false;
        if(t.getPrice() < this.coins){
            this.coins -= t.getPrice();
            field.setTower(t);
            System.out.println(coins);
            return true;
        }
        return false;
    }

    public ArrayList<Attacker> getAllAttackers(){
        return allAttackers;
    }

    public void addShot(Shape shape){
        root.getChildren().add(shape);
    }

    public void removeShape(Shape shape){
        root.getChildren().remove(shape);
    }

    public void Update(){
        for (int i = allAttackers.size() - 1; i >= 0; i--){
            Attacker a = allAttackers.get(i);
            a.update();
            if(a.reachedEnd()){
                allAttackers.remove(a);
                root.getChildren().remove(a);
            }
        }
        for (ArrayList<Field> row : allFields){
            for(Field field : row){
                field.update();
            }
        }

    }

    public static void main(String[] args){
        launch(args);
    }
}
