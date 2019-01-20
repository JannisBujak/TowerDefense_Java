package alternateStart;

import alternateStart.Pathfinding.Position;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class TowerDefense extends Application {

    Pane root;
    private static ArrayList<ArrayList<Field>> field = new ArrayList<>();

    //ArrayList<Attackers> attackers = new ArrayList<>();
    //ArrayList<Attackers> attackers = new ArrayList<>();
    //ArrayList<Attackers> attackers = new ArrayList<>();

    private static double SCENE_WIDTH = 1200;
    private static double SCENE_HEIGHT = 800;
    private static double NUMBER_OF_X_FIELDS = 24;
    private static double NUMBER_OF_Y_FIELDS = 16;
    public static double MOVEMENT_SPEED = 2;
    private static  double X_UNIT = SCENE_WIDTH / NUMBER_OF_X_FIELDS;
    private static  double Y_UNIT = SCENE_HEIGHT / NUMBER_OF_Y_FIELDS;

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

        Attacker a = new Attacker(this, new Position(0, 0), new Position(5, 2));

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

    public static List<Position> getOptimumFollowingPoints(Position start, Position destination) {

        //start.print();
        int xDistance = destination.getX() - start.getX();
        int yDistance = destination.getY() - start.getY();
        int xDirection, yDirection;
        if(xDistance == 0){
            xDirection = 0;
        }else{
            xDirection = xDistance/Math.abs(xDistance);
        }

        if(yDistance == 0){
            yDirection = 0;
        }else{
            yDirection = yDistance/Math.abs(yDistance);
        }

        List<Position> aims = new ArrayList<>();

        if(Math.abs(Math.sqrt(xDistance + yDistance)) > Math.abs(xDistance)
                && Math.abs(Math.sqrt(xDistance + yDistance)) > Math.abs(yDistance)){

            //Punkt diagonal vom Start
            aims.add(new Position(start.getX() + xDirection, start.getY() + yDirection));

            if(xDistance > yDistance){
                aims.add(new Position(start.getX() + xDirection, start.getY()));
                aims.add(new Position(start.getX(), start.getY() + yDirection));

                aims.add(new Position(start.getX() + xDirection, start.getY() - yDirection));
                aims.add(new Position(start.getX() - xDirection, start.getY() + yDirection));

                aims.add(new Position(start.getX(), start.getY() - yDirection));
                aims.add(new Position(start.getX() - xDirection, start.getY()));
            }else{
                aims.add(new Position(start.getX(), start.getY() + yDirection));
                aims.add(new Position(start.getX() + xDirection, start.getY()));

                aims.add(new Position(start.getX() + xDirection, start.getY() - yDirection));
                aims.add(new Position(start.getX() - xDirection, start.getY() + yDirection));

                aims.add(new Position(start.getX() - xDirection, start.getY()));
                aims.add(new Position(start.getX(), start.getY() - yDirection));
            }

            aims.add(new Position(start.getX() - xDirection, start.getY() - yDirection));


        }else if(xDistance > yDistance){

            //Punkt rechts/links vom Start

            aims.add(new Position(start.getX() + xDirection, start.getY()));

            aims.add(new Position(start.getX() + xDirection, start.getY() + yDirection));
            aims.add(new Position(start.getX() + xDirection, start.getY() - yDirection));

            aims.add(new Position(start.getX(), start.getY() + yDirection));
            aims.add(new Position(start.getX(), start.getY() - yDirection));

            aims.add(new Position(start.getX() - xDirection, start.getY() - yDirection));
            aims.add(new Position(start.getX() - xDirection, start.getY() + yDirection));

            aims.add(new Position(start.getX() - xDirection, start.getY()));


        }else{

            //Punkt ober-/unterhalb vom Start
            aims.add(new Position(start.getX(), start.getY() + yDirection));

            aims.add(new Position(start.getX() + xDirection, start.getY() + yDirection));
            aims.add(new Position(start.getX() - xDirection, start.getY() + yDirection));

            aims.add(new Position(start.getX() + xDirection, start.getY()));
            aims.add(new Position(start.getX() - xDirection, start.getY()));

            aims.add(new Position(start.getX() - xDirection, start.getY() - yDirection));
            aims.add(new Position(start.getX() + xDirection, start.getY() - yDirection));

            aims.add(new Position(start.getX(), start.getY() - yDirection));

        }
        for(int i = 0; i < aims.size(); i++){
            Position p = aims.get(i);
            if(p.equals(start)){
                aims.remove(p);
                i--;
            }
        }
        return aims;
    }



    public void Update(){


    }

     public Field getFieldAt(int x, int y){
        return field.get(y).get(x);

    }

    public Field getFieldAt(Position p){
        return field.get(p.getY()).get(p.getX());
    }

    public ArrayList<Field> getLine(int y){
        if(y < field.size()){
            return field.get(y);
        }else{
            return new ArrayList<>();
        }
    }

    public int getFieldHeight(){
        return field.size();
    }

    public static void main(String[] args){
        launch(args);
    }

    public boolean inBounds(int x, int y) {
        return y >= 0 && y <field.size() && x >= 0 && x < field.get(y).size();
    }
}
