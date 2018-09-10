import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.util.ArrayList;


public class TowerDefense extends Application {

    Pane root;
    ArrayList<Field> allFields = new ArrayList<>();
    ArrayList<Attacker> allAttackers = new ArrayList<>();

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


        initField(allFields, root, NUMBER_OF_X_FIELDS, NUMBER_OF_Y_FIELDS);

        Way asWay = new Way(0, 0);
        asWay.add(new Way(7 * X_UNIT, 0 * Y_UNIT));
        asWay.add(new Way(7 * X_UNIT, 5 * Y_UNIT));

        Attacker a = new Attacker(X_UNIT / 2, Y_UNIT / 2, X_UNIT / 2, Y_UNIT / 2, Color.RED, asWay);
        allAttackers.add(a);
        root.getChildren().add(a);

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

    public static void initField(ArrayList<Field> fields, Pane root, double numberOfXFields, double numberOfYFields){
        double xSize = SCENE_WIDTH / numberOfXFields;
        double ySize = SCENE_HEIGHT / numberOfYFields;
        for(int x = 0; x < numberOfXFields; x++) {
            for (int y = 0; y < numberOfYFields; y++) {

                Color color;
                if((x + y) % 2 == 0){
                    color = Color.WHITE.brighter();
                }else{
                    color = Color.WHITE.darker();
                }
                Field f = new Field(x * xSize, y * ySize, xSize, ySize, color);
                fields.add(f);
                root.getChildren().add(f);
            }
        }
    }

    public void Update(){
        for (Attacker a : allAttackers){
            if(!a.reachedEnd()){
                a.update();
            }
        }
        for (Field f : allFields){
            f.update();
        }


    }

    public static void main(String[] args){
        launch(args);
    }
}
