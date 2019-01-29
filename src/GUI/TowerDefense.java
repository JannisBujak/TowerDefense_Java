package GUI;
import Pathfinding.Position;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.util.ArrayList;

import Objects.*;

public class TowerDefense extends Application {

    Pane root;
    ArrayList< ArrayList<Field>> allFields = new ArrayList<>();
    ArrayList<TDField> allTDFields = new ArrayList<>();
    ArrayList<Attacker> allAttackers = new ArrayList<>();

    Position spawn = new Position(1, 1);
    Position globalAim = new Position(20, 15);



    static double SCENE_WIDTH = 1200;
    static double SCENE_HEIGHT = 800;
    static double NUMBER_OF_X_FIELDS = 24;
    static double NUMBER_OF_Y_FIELDS = 16;
    public static double MOVEMENT_SPEED = 2;
    public static  double X_UNIT = SCENE_WIDTH / NUMBER_OF_X_FIELDS;
    public static  double Y_UNIT = SCENE_HEIGHT / NUMBER_OF_Y_FIELDS;


    @Override
    public void start(Stage window) throws Exception {

        root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);


        initField(this, root, NUMBER_OF_X_FIELDS, NUMBER_OF_Y_FIELDS);

        Attacker a1 = new Attacker(this, spawn, globalAim);
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

        for(int y = 0; y < numberOfYFields; y++) {
            ArrayList<Field> row = new ArrayList<>();
            for (int x = 0; x < numberOfXFields; x++) {

                Color color;
                if((x + y) % 2 == 0){
                    color = Color.WHITE.brighter();
                }else{
                    color = Color.WHITE.darker();
                }
                TDField TDField  = new TDField(x * xSize, y * ySize, xSize, ySize, color);

                Field field = new Field(x, y);
                row.add(field);

                td.getAllTDFields().add(TDField);
                root.getChildren().add(TDField);
            }
            td.getAllFields().add(row);
        }
    }

    private ArrayList<TDField> getAllTDFields() {
        return allTDFields;
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

    public Position getSpawn() {
        return spawn;
    }

    public Position getGlobalAim() {
        return globalAim;
    }

    public boolean inBounds(int x, int y) {
        return ((y >= 0 && y < allFields.size()) && (x >= 0 && x < allFields.get(y).size()));
    }

    public void printBoard(){
        for(ArrayList<Field> list : allFields){
            for(Field f : list){
                System.out.print("1 ");
            }

            System.out.println();
        }
    }

    public void Update(){
        for (Attacker a : allAttackers){
            if(!a.reachedEnd()){
                a.update();
            }
        }
        for (ArrayList<Field> row : allFields){
            for(Field f : row){
                //TODO: write

                // f.update();
            }
        }

    }

    public static void main(String[] args){
        launch(args);
    }
}
