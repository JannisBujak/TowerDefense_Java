package alternateStart;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class TowerDefense extends Application {

    Pane root;
    private static ArrayList<ArrayList<Field>> field = new ArrayList<>();

    //ArrayList<Attackers> attackers = new ArrayList<>();
    //ArrayList<Attackers> attackers = new ArrayList<>();
    //ArrayList<Attackers> attackers = new ArrayList<>();

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

        for(int y = 0; y < NUMBER_OF_Y_FIELDS; y++){
            ArrayList<Field> row = new ArrayList<>();
            for(int x = 0; x < NUMBER_OF_Y_FIELDS; x++){
                row.add(new Field(x, y));
            }
            field.add(row);
        }

        //initField(allFields, root, NUMBER_OF_X_FIELDS, NUMBER_OF_Y_FIELDS);


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


    public void Update(){


    }

     public static Field getFieldAt(int x, int y){
        return field.get(y).get(x);

    }

    public static void main(String[] args){
        launch(args);
    }
}
